package koh.api.core.config;

import koh.api.core.ConfigLoader;
import lombok.Data;

import java.io.InputStream;
import java.util.Properties;

@Data
public class JettyConfig {
    final String host;
    final int port;
    final int securePort;
    final int maxBodySize;
    final int requestHeaderSize;
    final int responseHeaderSize;
    final int outputBufferSize;
    final int idleTimeout;
    final String sslKeyStorePath;
    final String sslKeyStorePassword;

    JettyConfig getDefaultConfig() {
        return new JettyConfig(
                "0.0.0.0",
                8080,
                8081,
                24 * 1024 * 1024,
                8 * 1024,
                8 * 1024,
                32 * 1024,
                30 * 1000,
                "/etc/ssl/key",
                "/etc/ssl/pw"
        );
    }

    JettyConfig getConfig(String file) {
        Properties properties = ConfigLoader.load(file);
        return new JettyConfig(
                properties.getProperty("server.host"),
                Integer.parseInt(properties.getProperty("server.port")),
                Integer.parseInt(properties.getProperty("server.securePort")),
                Integer.parseInt(properties.getProperty("server.maxBodySize")),
                Integer.parseInt(properties.getProperty("server.requestHeaderSize")),
                Integer.parseInt(properties.getProperty("server.responseHeaderSize")),
                Integer.parseInt(properties.getProperty("server.outputBufferSize")),
                Integer.parseInt(properties.getProperty("server.idleTimeout")),
                properties.getProperty("server.sslKeyStorePath"),
                properties.getProperty("server.sslKeyStorePassword")
        );
    }
}
