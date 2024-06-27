package koh.api.core.base;

public interface Configurable<T> {
    T getConfig(String name);

    T getDefaultConfig();
}
