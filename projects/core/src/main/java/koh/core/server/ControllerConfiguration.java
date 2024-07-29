package koh.core.server;

import koh.core.base.AbstractRoute;
import koh.core.base.SimpleController;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Slf4j
class ControllerConfiguration {

    Map<String, Class<? extends SimpleController>> routes;

    public ServletContextHandler loadAsContextHandler(String routeClassName) {
        Class<? extends AbstractRoute> abstractRouteClass = requireExistedRouteClass(routeClassName);
        AbstractRoute route = requireConstructableInstant(abstractRouteClass);

        return loadAsContextHandler(route);
    }

    public ServletContextHandler loadAsContextHandler(AbstractRoute route) {
        ServletContextHandler contextHandler = new ServletContextHandler();

        for (Map.Entry<String, Class<? extends SimpleController>> entry : route.getControllerMap().entrySet()) {
            ServletHolder contextHolder = new ServletHolder(new ControllerHolder(entry.getValue()));
            contextHandler.addServlet(contextHolder, entry.getKey());
        }

        return contextHandler;
    }

    public Class<? extends AbstractRoute> requireExistedRouteClass(String cls) {
        try {
            return Class.forName(cls).asSubclass(AbstractRoute.class);
        } catch (ClassNotFoundException e) {
            log.error("Class {} is not found.", cls);
            log.debug("", e);
            throw new RouteLoaderException(e);
        }
    }

    public AbstractRoute requireConstructableInstant(Class<? extends AbstractRoute> c) {
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            log.error("Class {} does not required constructor.", c);
            log.debug("", e);
            throw new RouteLoaderException(e);
        }
    }

    public static class RouteLoaderException extends RuntimeException {
        public RouteLoaderException(Exception e) {
            super(e);
        }
    }
}
