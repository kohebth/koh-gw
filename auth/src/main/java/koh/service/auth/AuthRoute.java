package koh.service.auth;

import koh.core.base.AbstractRoute;
import koh.core.base.Controller;
import koh.core.base.Middleware;
import koh.service.auth.route.AuthController;
import koh.service.auth.route.ForgetController;
import koh.service.auth.route.RegisterController;

public class AuthRoute extends AbstractRoute {
    public AuthRoute() {
        addRoute("/auth", AuthController.class);
        addRoute("/forget", ForgetController.class);
        addRoute("/register", RegisterController.class);
    }

    @Override
    protected void addRoute(String path, Class<? extends Controller> controllerClass, Middleware... middlewares) {
        this.getControllerMap().put(path, controllerClass);
    }

    @Override
    protected void removeRoute(String path) {
        this.getControllerMap().remove(path);
    }
}
