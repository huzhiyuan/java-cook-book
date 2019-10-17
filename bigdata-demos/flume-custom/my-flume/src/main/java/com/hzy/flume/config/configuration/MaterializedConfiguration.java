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

package com.hzy.flume.config.configuration;

import com.google.common.collect.ImmutableMap;
import org.apache.flume.Channel;
import org.apache.flume.SinkRunner;
import org.apache.flume.SourceRunner;

/**
 * 实体化配置, 将flume配置文件实体化, 包括里面的source sink和channels
 */
public interface MaterializedConfiguration {

  public void addSourceRunner(String name, SourceRunner sourceRunner);

  public void addSinkRunner(String name, SinkRunner sinkRunner);

  public void addChannel(String name, Channel channel);

  public ImmutableMap<String, SourceRunner> getSourceRunners();

  public ImmutableMap<String, SinkRunner> getSinkRunners();

  public ImmutableMap<String, Channel> getChannels();

}
