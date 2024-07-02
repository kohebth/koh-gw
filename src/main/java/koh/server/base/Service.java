package koh.server.base;

public interface Service<V extends SerializableRequest, T extends SerializableResponse> {
     T serve(V request);
}
