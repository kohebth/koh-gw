package koh.core;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.core.config.ServerProperties;
import koh.core.helper.JsonMapper;
import koh.core.helper.Responder;
import koh.core.helper.ServiceLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j class HttpRoute extends GenericServlet {

    final transient ServerProperties serverProperties;
    final transient Map<String, Controller> routeMap;

    HttpRoute(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
        this.routeMap = new HashMap<>();
    }

    @Override public void init(ServletConfig config) {
        try {
            Set<Class<? extends Controller>>
                    controllerClasses =
                    ServiceLoader.getServiceSubclasses(serverProperties.getRoutePackage());
            // Do something with the controllerClasses set
            for (Class<? extends Controller> controllerClass : controllerClasses) {
                log.info("Found route: " + controllerClass.getName());
                Optional<? extends Controller>
                        controller =
                        Optional.of((Controller) controllerClass.getDeclaredConstructor().newInstance());
                if (routeMap.containsKey(controller.get().getPath())) {
                    throw new IllegalStateException("Path [{}] is already mapped to a service");
                }
                routeMap.put(controller.get().getPath(), controller.get());
            }
        } catch (Exception e) {
            log.error("Fail to initialize Routes", e);
            throw new RuntimeException(e);
        }
        log.info("Routes have been initialized!");
    }

    @Override public void service(ServletRequest req, ServletResponse res)
            throws IOException {
        String path = ((HttpServletRequest) req).getPathInfo();

        Optional<? extends Controller> service = Optional.ofNullable(routeMap.get(path));
        if (service.isPresent()) {
            SerializableRequest requestObject;
            SerializableResponse responseObject;
            try {
                requestObject = JsonMapper.deserialize(req.getReader(), service.get().getRequestClass());
                responseObject = service.get().handle(requestObject);
            } catch (Exception e) {
                responseObject = Responder.emptySerializableResponse();
            }
            Responder.responseJson((HttpServletResponse) res, responseObject);
        }
    }
}
