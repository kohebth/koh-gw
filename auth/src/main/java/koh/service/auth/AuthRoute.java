package koh.service.auth;

import koh.core.base.AbstractRoute;
import koh.service.auth.route.AuthController;
import koh.service.auth.route.ForgetController;
import koh.service.auth.route.RegisterController;

public class AuthRoute extends AbstractRoute {
    public AuthRoute() {
        addRoute("/auth", AuthController.class);
        addRoute("/forget", ForgetController.class);
        addRoute("/register", RegisterController.class);
    }
}
