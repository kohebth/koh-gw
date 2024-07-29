package koh.service.vps;

import koh.core.base.AbstractRoute;
import koh.service.vps.route.MigrationController;

public class VpsRoute extends AbstractRoute {
    public VpsRoute() {
        addRoute("/auth", MigrationController.class);
    }
}
