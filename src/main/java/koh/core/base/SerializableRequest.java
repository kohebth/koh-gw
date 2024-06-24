package koh.core.base;

public interface SerializableRequest {
    Object fromJson();

    Object fromProto();
}
