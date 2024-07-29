package koh.core.config;

import koh.core.base.SystemConfigurable;

import java.util.Optional;

public enum ServerConfigurations implements SystemConfigurable {
    HOST,
    PORT,
    SECURE_PORT,
    MAX_BODY_SIZE,
    REQUEST_HEADER_SIZE,
    RESPONSE_HEADER_SIZE,
    OUTPUT_BUFFER_SIZE,
    IDLE_TIMEOUT,
    MAX_THREAD_NUM,
    MIN_THREAD_NUM,
    SSL_KEY_STORE_PATH,
    SSL_KEY_STORE_PASSWORD,
    ROUTE_CLASS;

    private final String value;

    ServerConfigurations() {
        this.value = this.fromSystemEnv();
        boolean isDefault = this.value.equals(DefaultServerConfiguration.valueOf(this.name()).value());

        System.getLogger(ServerConfigurations.class.getName()).log(
                System.Logger.Level.INFO,
                "Server Configuration {} = {}{}",
                this.name(),
                this.value,
                isDefault ? "<default>" : ""
        );
    }

    @Override public String fromSystemEnv() {
        String defaultValue = DefaultServerConfiguration.valueOf(this.name()).value();
        return Optional.ofNullable(System.getenv(this.name())).orElse(defaultValue);
    }

    @Override public String fromSystemProperties() {
        throw new UnsupportedOperationException();
    }

    @Override public String value() {
        return this.value;
    }
}
