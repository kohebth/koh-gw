package koh.server.config;

import koh.server.core.ConfigLoader;
import lombok.Data;

import java.nio.file.Path;
import java.util.Properties;

@Data
public class ApplicationProperties {
    final Path serverConfig;
    final Path routeConfig;
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
