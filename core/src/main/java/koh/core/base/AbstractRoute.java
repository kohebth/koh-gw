package koh.core.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractRoute {

    public AbstractRoute() {
    }

    private final Map<String, Class<? extends Controller>> controllerMap = new HashMap<>();

    protected void addRoute(
            String path,
            Class<? extends Controller> controllerClass,
            Middleware... middlewares
    ) {
        if (this.controllerMap.containsKey(path)) {
            throw new RuntimeException(String.format("Route at %s existed", path));
        }
        this.controllerMap.put(path, controllerClass);
    }
    protected void removeRoute(String path) {
        if (!this.controllerMap.containsKey(path)) {
            throw new RuntimeException(String.format("Path %s does not exist", path));
        }
        this.controllerMap.remove(path);
    }
}
