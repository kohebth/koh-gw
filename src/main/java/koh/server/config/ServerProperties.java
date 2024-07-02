package koh.server.config;

import koh.server.core.ConfigLoader;
import lombok.Data;

import java.util.Properties;

@Data
public class ServerProperties {
    final String host;
    final int port;
    final int securePort;
    final int maxBodySize;
    final int requestHeaderSize;
    final int responseHeaderSize;
    final int outputBufferSize;
    final int idleTimeout;
    final int maxThreadNum;
    final int minThreadNum;
    final String sslKeyStorePath;
    final String sslKeyStorePassword;

    public static ServerProperties getDefaultConfig() {
        return new ServerProperties(
                "0.0.0.0",
                8080,
                8081,
                24 * 1024 * 1024,
                8 * 1024,
                8 * 1024,
                32 * 1024,
                30 * 1000,
                4,
                0,
                "/etc/ssl/key",
                "/etc/ssl/pw"
        );
    }

    ServerProperties getConfig(String file) {
        Properties properties = ConfigLoader.load(file);
        return new ServerProperties(
                properties.getProperty("server.host"),
                Integer.parseInt(properties.getProperty("server.port")),
                Integer.parseInt(properties.getProperty("server.securePort")),
                Integer.parseInt(properties.getProperty("server.maxBodySize")),
                Integer.parseInt(properties.getProperty("server.requestHeaderSize")),
                Integer.parseInt(properties.getProperty("server.responseHeaderSize")),
                Integer.parseInt(properties.getProperty("server.outputBufferSize")),
                Integer.parseInt(properties.getProperty("server.idleTimeout")),
                Integer.parseInt(properties.getProperty("server.maxThreadNum")),
                Integer.parseInt(properties.getProperty("server.minThreadNum")),
                properties.getProperty("server.sslKeyStorePath"),
                properties.getProperty("server.sslKeyStorePassword")
        );
    }
}
