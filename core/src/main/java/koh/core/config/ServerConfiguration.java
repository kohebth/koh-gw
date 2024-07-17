package koh.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerConfiguration {
    final String host;
    final String port;
    final Integer securePort;
    final Integer maxBodySize;
    final Integer requestHeaderSize;
    final Integer responseHeaderSize;
    final Integer outputBufferSize;
    final Integer idleTimeout;
    final Integer maxThreadNum;
    final Integer minThreadNum;
    final String sslKeyStorePath;
    final String sslKeyStorePassword;
    final String routeClass;

    public ServerConfiguration() {
        this(
                DefaultServerConfiguration.HOST.value(),
                DefaultServerConfiguration.PORT.value(),
                Integer.parseInt(DefaultServerConfiguration.SECURE_PORT.value()),
                Integer.parseInt(DefaultServerConfiguration.MAX_BODY_SIZE.value()),
                Integer.parseInt(DefaultServerConfiguration.REQUEST_HEADER_SIZE.value()),
                Integer.parseInt(DefaultServerConfiguration.RESPONSE_HEADER_SIZE.value()),
                Integer.parseInt(DefaultServerConfiguration.OUTPUT_BUFFER_SIZE.value()),
                Integer.parseInt(DefaultServerConfiguration.IDLE_TIMEOUT.value()),
                Integer.parseInt(DefaultServerConfiguration.MAX_THREAD_NUM.value()),
                Integer.parseInt(DefaultServerConfiguration.MIN_THREAD_NUM.value()),
                DefaultServerConfiguration.SSL_KEY_STORE_PATH.value(),
                DefaultServerConfiguration.SSL_KEY_STORE_PASSWORD.value(),
                DefaultServerConfiguration.ROUTE_CLASS.value()
        );
    }
}
