package koh.core.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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

    public static ServerConfigurationBuilder builder() {
        return new ServerConfigurationBuilder().host(DefaultServerConfiguration.HOST.value())
                .port(DefaultServerConfiguration.PORT.value())
                .securePort(Integer.parseInt(DefaultServerConfiguration.SECURE_PORT.value()))
                .maxBodySize(Integer.parseInt(DefaultServerConfiguration.MAX_BODY_SIZE.value()))
                .requestHeaderSize(Integer.parseInt(DefaultServerConfiguration.REQUEST_HEADER_SIZE.value()))
                .responseHeaderSize(Integer.parseInt(DefaultServerConfiguration.RESPONSE_HEADER_SIZE.value()))
                .outputBufferSize(Integer.parseInt(DefaultServerConfiguration.OUTPUT_BUFFER_SIZE.value()))
                .idleTimeout(Integer.parseInt(DefaultServerConfiguration.IDLE_TIMEOUT.value()))
                .maxThreadNum(Integer.parseInt(DefaultServerConfiguration.MAX_THREAD_NUM.value()))
                .minThreadNum(Integer.parseInt(DefaultServerConfiguration.MIN_THREAD_NUM.value()))
                .sslKeyStorePath(DefaultServerConfiguration.SSL_KEY_STORE_PATH.value())
                .sslKeyStorePassword(DefaultServerConfiguration.SSL_KEY_STORE_PASSWORD.value())
                .routeClass(DefaultServerConfiguration.ROUTE_CLASS.value());
    }
}
