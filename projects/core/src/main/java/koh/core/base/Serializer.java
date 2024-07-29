package koh.core.base;

public interface Serializer {
    default String serialize() {
        return null;
    }

    default Object deserialize() {
        return null;
    }
}
