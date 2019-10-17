/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.hzy.flume;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.hzy.flume.channel.Channel;
import com.hzy.flume.common.constant.Constants;
import com.hzy.flume.config.Context;
import com.hzy.flume.lifecycle.LifecycleAware;
import com.hzy.flume.lifecycle.LifecycleState;
import com.hzy.flume.lifecycle.LifecycleSupervisor;
import com.hzy.flume.metric.MonitorService;
import com.hzy.flume.metric.MonitoringType;
import com.hzy.flume.node.MaterializedConfiguration;
import com.hzy.flume.sink.SinkRunner;
import com.hzy.flume.source.SourceRunner;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Application {

  private static final Logger logger = LoggerFactory
      .getLogger(Application.class);

  public static final String CONF_MONITOR_CLASS = "flume.monitoring.type";
  public static final String CONF_MONITOR_PREFIX = "flume.monitoring.";

  private final List<LifecycleAware> components;
  private final LifecycleSupervisor supervisor;//生命周期监督员
  private MaterializedConfiguration materializedConfiguration;
  private MonitorService monitorServer;

  public Application() {
    this(new ArrayList<LifecycleAware>(0));
  }

  public Application(List<LifecycleAware> components) {
    this.components = components;
    supervisor = new LifecycleSupervisor();
  }

  public synchronized void start() {
    for (LifecycleAware component : components) {
      supervisor.supervise(component,
          new LifecycleSupervisor.SupervisorPolicy.AlwaysRestartPolicy(), LifecycleState.START);
    }
  }

  @Subscribe
  public synchronized void handleConfigurationEvent(MaterializedConfiguration conf) {
    stopAllComponents();
    startAllComponents(conf);
  }

  public synchronized void stop() {
    supervisor.stop();
    if (monitorServer != null) {
      monitorServer.stop();
    }
  }

  private void stopAllComponents() {
    if (this.materializedConfiguration != null) {
      logger.info("Shutting down configuration: {}", this.materializedConfiguration);
      for (Entry<String, SourceRunner> entry :
           this.materializedConfiguration.getSourceRunners().entrySet()) {
        try {
          logger.info("Stopping Source " + entry.getKey());
          supervisor.unsupervise(entry.getValue());
        } catch (Exception e) {
          logger.error("Error while stopping {}", entry.getValue(), e);
        }
      }

      for (Entry<String, SinkRunner> entry :
           this.materializedConfiguration.getSinkRunners().entrySet()) {
        try {
          logger.info("Stopping Sink " + entry.getKey());
          supervisor.unsupervise(entry.getValue());
        } catch (Exception e) {
          logger.error("Error while stopping {}", entry.getValue(), e);
        }
      }

      for (Entry<String, Channel> entry :
           this.materializedConfiguration.getChannels().entrySet()) {
        try {
          logger.info("Stopping Channel " + entry.getKey());
          supervisor.unsupervise(entry.getValue());
        } catch (Exception e) {
          logger.error("Error while stopping {}", entry.getValue(), e);
        }
      }
    }
    if (monitorServer != null) {
      monitorServer.stop();
    }
  }

  private void startAllComponents(MaterializedConfiguration materializedConfiguration) {
    logger.info("Starting new configuration:{}", materializedConfiguration);

    this.materializedConfiguration = materializedConfiguration;

    for (Entry<String, Channel> entry :
        materializedConfiguration.getChannels().entrySet()) {
      try {
        logger.info("Starting Channel " + entry.getKey());
        supervisor.supervise(entry.getValue(),
            new LifecycleSupervisor.SupervisorPolicy.AlwaysRestartPolicy(), LifecycleState.START);
      } catch (Exception e) {
        logger.error("Error while starting {}", entry.getValue(), e);
      }
    }

    /*
     * Wait for all channels to start.
     */
    for (Channel ch : materializedConfiguration.getChannels().values()) {
      while (ch.getLifecycleState() != LifecycleState.START
          && !supervisor.isComponentInErrorState(ch)) {
        try {
          logger.info("Waiting for channel: " + ch.getName() +
              " to start. Sleeping for 500 ms");
          Thread.sleep(500);
        } catch (InterruptedException e) {
          logger.error("Interrupted while waiting for channel to start.", e);
          Throwables.propagate(e);
        }
      }
    }

    for (Entry<String, SinkRunner> entry : materializedConfiguration.getSinkRunners().entrySet()) {
      try {
        logger.info("Starting Sink " + entry.getKey());
        supervisor.supervise(entry.getValue(),
            new LifecycleSupervisor.SupervisorPolicy.AlwaysRestartPolicy(), LifecycleState.START);
      } catch (Exception e) {
        logger.error("Error while starting {}", entry.getValue(), e);
      }
    }

    for (Entry<String, SourceRunner> entry :
         materializedConfiguration.getSourceRunners().entrySet()) {
      try {
        logger.info("Starting Source " + entry.getKey());
        supervisor.supervise(entry.getValue(),
            new LifecycleSupervisor.SupervisorPolicy.AlwaysRestartPolicy(), LifecycleState.START);
      } catch (Exception e) {
        logger.error("Error while starting {}", entry.getValue(), e);
      }
    }

    this.loadMonitoring();
  }

  @SuppressWarnings("unchecked")
  private void loadMonitoring() {
    Properties systemProps = System.getProperties();
    Set<String> keys = systemProps.stringPropertyNames();
    try {
      if (keys.contains(CONF_MONITOR_CLASS)) {
        String monitorType = systemProps.getProperty(CONF_MONITOR_CLASS);
        Class<? extends MonitorService> klass;
        try {
          //Is it a known type?
          klass = MonitoringType.valueOf(
              monitorType.toUpperCase(Locale.ENGLISH)).getMonitorClass();
        } catch (Exception e) {
          //Not a known type, use FQCN
          klass = (Class<? extends MonitorService>) Class.forName(monitorType);
        }
        this.monitorServer = klass.newInstance();
        Context context = new Context();
        for (String key : keys) {
          if (key.startsWith(CONF_MONITOR_PREFIX)) {
            context.put(key.substring(CONF_MONITOR_PREFIX.length()),
                systemProps.getProperty(key));
          }
        }
        monitorServer.configure(context);
        monitorServer.start();
      }
    } catch (Exception e) {
      logger.warn("Error starting monitoring. "
          + "Monitoring might not be available.", e);
    }

  }

  public static void main(String[] args) {

    try {


      Application application = null;
      File configurationFile = new File("C:\\server\\flume\\apache-flume-1.8.0-bin\\conf\\netcat.properties");

      PropertiesFileConfigurationProvider configurationProvider =
              new PropertiesFileConfigurationProvider("a1", configurationFile);
      application = new Application();
      application.handleConfigurationEvent(configurationProvider.getConfiguration());

      application.start();

      final Application appReference = application;
      Runtime.getRuntime().addShutdownHook(new Thread("agent-shutdown-hook") {
        @Override
        public void run() {
          appReference.stop();
        }
      });

    } catch (Exception e) {
      logger.error("A fatal error occurred while running. Exception follows.", e);
    }
  }
}