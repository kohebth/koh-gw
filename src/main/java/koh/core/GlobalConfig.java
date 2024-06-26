package koh.core;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public final class GlobalConfig {
    private static final String configName = "application.properties";
    String port;
    String serializer;

    GlobalConfig() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream(configName)) {
            properties.load(is);
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("{} is not found!", configName);
        } catch (IOException ioException) {
            log.error("{} is unable to access!", configName);
        }

        this.serializer = properties.getProperty("serializer");
        this.port = properties.getProperty("port");
    }
}
