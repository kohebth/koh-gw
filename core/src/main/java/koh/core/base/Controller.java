package koh.core.base;

public interface Controller {
    SerializableResponse handle(SerializableRequest request) throws Exception;

    String getPath();

    Class<? extends SerializableRequest> getRequestClass();

    Class<? extends SerializableResponse> getResponseClass();
}
