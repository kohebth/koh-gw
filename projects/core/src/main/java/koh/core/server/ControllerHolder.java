package koh.core.server;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.SimpleController;
import koh.core.base.SimpleMiddleware;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;
import koh.core.helper.JsonTools;
import koh.core.helper.ResponseTools;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
class ControllerHolder extends GenericServlet {
    private final Class<? extends SimpleController> controllerClass;
    private final List<Class<? extends SimpleMiddleware>> middlewareClasses;
    private transient SimpleController controller;
    private transient List<SimpleMiddleware> middlewares;

    public ControllerHolder(Class<? extends SimpleController> controllerClass) {
        this.controllerClass = controllerClass;
        this.middlewareClasses = Collections.emptyList();
    }

    public ControllerHolder(Class<? extends SimpleController> controllerClass, Class<? extends SimpleMiddleware>... middleware) {
        this.controllerClass = controllerClass;
        this.middlewareClasses = List.of(middleware);
    }

    @Override
    public void init(ServletConfig config) {
        try {
            this.controller = controllerClass.getDeclaredConstructor().newInstance();
            this.middlewares = new ArrayList<>();

            for (Class<? extends SimpleMiddleware> c : middlewareClasses) {
                SimpleMiddleware middleware = c.getDeclaredConstructor().newInstance();
                this.middlewares.add(middleware);
            }
        } catch (Exception e) {
            throw new ControllerServletException(e);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws IOException {

        SimpleRequest requestObject;
        SimpleResponse responseObject;

        try {

            requestObject = JsonTools.deserialize(req.getReader(), this.controller.getRequestClass());

            for (SimpleMiddleware middleware : this.middlewares) {
                Objects.requireNonNull(middleware);
                middleware.handle(requestObject);
            }

            Objects.requireNonNull(this.controller);
            responseObject = this.controller.handle(requestObject);

            ResponseTools.responseJson((HttpServletResponse) res, this.controller.getResponseClass().cast(responseObject));
        } catch (Exception e) {
            ResponseTools.responseEmpty((HttpServletResponse) res);
        } finally {
            res.flushBuffer();
            res.getWriter().close();
            req.getReader().close();
        }
    }

    public static class ControllerServletException extends RuntimeException {
        public ControllerServletException(Exception e) {
            super(e);
        }
    }
}
