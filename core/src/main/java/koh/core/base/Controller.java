package koh.core.base;

public interface Controller {
    SerializableResponse handle(SerializableRequest request);

    String getPath();

    Class<? extends SerializableRequest> getRequestClass();

    Class<? extends SerializableResponse> getResponseClass();
}
