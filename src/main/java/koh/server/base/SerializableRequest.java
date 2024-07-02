package koh.server.base;

public interface SerializableRequest {
    Object fromJson();

    Object fromProto();
}
