package koh.core.base;

import koh.core.base.Controller;
import koh.core.base.Middleware;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRoute {
    private final Map<String, Class<? extends Controller>> controllerMap = new HashMap<>();

    abstract void addRoute(String path, Class<? extends Controller> controllerClass, Middleware... middlewares);
    abstract void removeRoute(String path);

    public Map<String, Class<? extends Controller>> getControllerMap() {
        return this.controllerMap;
    }
}
