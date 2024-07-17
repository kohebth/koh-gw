package koh.core.base;

public interface SystemConfigurable {
    String fromSystemEnv();

    String fromSystemProperties();

    <T> T value();
}
