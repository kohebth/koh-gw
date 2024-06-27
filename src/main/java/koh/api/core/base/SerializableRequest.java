package koh.api.core.base;

public interface SerializableRequest {
    Object fromJson();

    Object fromProto();
}
