package koh.server.base;

public interface Configurable<T> {
    T getConfig(String name);

    T getDefaultConfig();
}
