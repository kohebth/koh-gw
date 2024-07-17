package koh.core.config;

import koh.core.base.SystemConfigurable;

public enum DefaultServerConfiguration implements SystemConfigurable {
    HOST("0.0.0.0"),
    PORT("8080"),
    SECURE_PORT("8081"),
    MAX_BODY_SIZE(String.valueOf(24 * 1024 * 1024)),
    REQUEST_HEADER_SIZE(String.valueOf(8 * 1024)),
    RESPONSE_HEADER_SIZE(String.valueOf(8 * 1024)),
    OUTPUT_BUFFER_SIZE(String.valueOf(32 * 1024)),
    IDLE_TIMEOUT("30000"),
    MAX_THREAD_NUM("4"),
    MIN_THREAD_NUM("0"),
    SSL_KEY_STORE_PATH("/etc/ssl/key"),
    SSL_KEY_STORE_PASSWORD("/etc/ssl/key/pw"),
    ROUTE_CLASS("koh.server.route.HttpRouteImpl");

    private final String defaultValue;
    DefaultServerConfiguration(String defaultValue) {
        this.defaultValue = defaultValue;
        System.getLogger(DefaultServerConfiguration.class.getName()).log(
                System.Logger.Level.INFO,
                "Server Configuration {} = {} <default>",
                this.name(),
                this.value()
        );
    }

    @Override
    public String fromSystemEnv() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String fromSystemProperties() {
        throw new UnsupportedOperationException();
    }

    @Override public String value() {
        return this.defaultValue;
    }
}
