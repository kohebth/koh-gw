package koh.api.core.config;

import koh.api.core.base.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GlobalConfigFactory {

    public static synchronized GlobalConfig getGlobalConfig(GlobalConfigOptions option) {
        return switch (option) {
            case SYSTEM_ENV -> null;// SystemVariable.getInstance();
            case PROPERTIES -> null;// ApplicationProperties.getInstance();
            default -> throw new UnsupportedOperationException();
        };
    }

    public synchronized void renewGlobalConfig(Configuration configuration) {

    }

    private GlobalConfigFactory() {
    }
}
