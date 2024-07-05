package koh.core.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static Properties load(String configName) {
        Properties properties = new Properties();
        try (InputStream is = ConfigLoader.class.getResourceAsStream(configName)) {
            properties.load(is);
        } catch (IOException e) {
            throw new ApplicationPropertiesException();
        }
        return properties;
    }

    private static final class ApplicationPropertiesException extends RuntimeException {
        ApplicationPropertiesException() {
            super("Application.properties {} is unable to access or not found!");
        }
    }

    private ConfigLoader() {
    }
}
