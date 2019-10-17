package com.hzy.flume;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hzy.flume.constants.Constants;
import com.hzy.flume.lifecycle.LifecycleAware;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class Application {
    private static final Logger logger = LoggerFactory
            .getLogger(Application.class);

    public static void main(String[] args) {

        try {



            Options options = initOptions();

            CommandLineParser parser = new GnuParser();
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption('h')) {
                new HelpFormatter().printHelp("flume-ng agent", options, true);
                return;
            }

            String agentName = commandLine.getOptionValue('n');
            boolean reload = !commandLine.hasOption("no-reload-conf");

            Application application = null;

            boolean isZkConfigured = false;
            if (commandLine.hasOption('z') || commandLine.hasOption("zkConnString")) {
                isZkConfigured = true;
            }
            if (isZkConfigured) {
                //TODO hzy: zk之后研究
                // get options
                logger.warn("zk not support yet!");
                return;
//                String zkConnectionStr = commandLine.getOptionValue('z');
//                String baseZkPath = commandLine.getOptionValue('p');
//
//                if (reload) {
//                    EventBus eventBus = new EventBus(agentName + "-event-bus");
//                    List<LifecycleAware> components = Lists.newArrayList();
//                    PollingZooKeeperConfigurationProvider zookeeperConfigurationProvider =
//                            new PollingZooKeeperConfigurationProvider(
//                                    agentName, zkConnectionStr, baseZkPath, eventBus);
//                    components.add(zookeeperConfigurationProvider);
//                    application = new Application(components);
//                    eventBus.register(application);
//                } else {
//                    StaticZooKeeperConfigurationProvider zookeeperConfigurationProvider =
//                            new StaticZooKeeperConfigurationProvider(
//                                    agentName, zkConnectionStr, baseZkPath);
//                    application = new Application();
//                    application.handleConfigurationEvent(zookeeperConfigurationProvider.getConfiguration());
//                }
            } else {
                File configurationFile = new File(commandLine.getOptionValue('f'));

                /*
                 * The following is to ensure that by default the agent will fail on
                 * startup if the file does not exist.
                 */
                if (!configurationFile.exists()) {
                    // If command line invocation, then need to fail fast
                    if (System.getProperty(Constants.SYSPROP_CALLED_FROM_SERVICE) ==
                            null) {
                        String path = configurationFile.getPath();
                        try {
                            path = configurationFile.getCanonicalPath();
                        } catch (IOException ex) {
                            logger.error("Failed to read canonical path for file: " + path,
                                    ex);
                        }
                        throw new ParseException(
                                "The specified configuration file does not exist: " + path);
                    }
                }
                List<LifecycleAware> components = Lists.newArrayList();

                if (reload) {
                    EventBus eventBus = new EventBus(agentName + "-event-bus");
                    PollingPropertiesFileConfigurationProvider configurationProvider =
                            new PollingPropertiesFileConfigurationProvider(
                                    agentName, configurationFile, eventBus, 30);
                    components.add(configurationProvider);
                    application = new Application(components);
                    eventBus.register(application);
                } else {
                    PropertiesFileConfigurationProvider configurationProvider =
                            new PropertiesFileConfigurationProvider(agentName, configurationFile);
                    application = new Application();
                    application.handleConfigurationEvent(configurationProvider.getConfiguration());
                }
            }
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

    private static Options initOptions() {
        Options options = new Options();

        Option option = new Option("n", "name", true, "the name of this agent");
        option.setRequired(true);
        options.addOption(option);

        option = new Option("f", "conf-file", true,
                "specify a config file (required if -z missing)");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, "no-reload-conf", false,
                "do not reload config file if changed");
        options.addOption(option);

        // Options for Zookeeper
        option = new Option("z", "zkConnString", true,
                "specify the ZooKeeper connection to use (required if -f missing)");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("p", "zkBasePath", true,
                "specify the base path in ZooKeeper for agent configs");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("h", "help", false, "display help text");
        options.addOption(option);
        return options;
    }
}
