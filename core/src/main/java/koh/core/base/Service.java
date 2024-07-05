package koh.core.base;

public interface Service<V extends SerializableRequest, T extends SerializableResponse> {
     T serve(V request);
}
