package koh.core.base;

public interface SimpleHandler {
    <T extends AbstractDto> Object handle(T wrappedObject);
}
