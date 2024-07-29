package koh.core.base;

public interface SimpleController {
    SimpleResponse handle(SimpleRequest request) throws Exception;

    String getPath();

    Class<? extends SimpleRequest> getRequestClass();

    Class<? extends SimpleResponse> getResponseClass();
}
