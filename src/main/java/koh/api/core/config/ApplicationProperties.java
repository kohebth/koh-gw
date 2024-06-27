package koh.api.core.config;

import koh.api.core.ConfigLoader;

import java.nio.file.Path;
import java.util.Properties;

public record ApplicationProperties(Path serverConfig, Path routeConfig) {
    public static ApplicationProperties getDefaultConfig() {
        return getConfig("application.properties");
    }

    public static ApplicationProperties getConfig(String configName) {

        Properties properties = ConfigLoader.load(configName);

        Path serverConfig = Path.of(properties.getProperty("configuration.server"));
        Path routeConfig = Path.of(properties.getProperty("configuration.route"));

        return new ApplicationProperties(serverConfig, routeConfig);
    }
}
