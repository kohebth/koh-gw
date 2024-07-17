package koh.core.server;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.core.helper.JsonMapper;
import koh.core.helper.Responder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
class ControllerHolder extends GenericServlet {
    private final Class<? extends Controller> controllerClass;
    private transient Controller controller;

    public ControllerHolder(Class<? extends Controller> controllerClass) {
        this.controllerClass = controllerClass;
    }

    @Override
    public void init(ServletConfig config) {
        try {
            this.controller = controllerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new ControllerServletException(e);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws IOException {

        SerializableRequest requestObject;
        SerializableResponse responseObject;
        try {
            Objects.requireNonNull(this.controller);

            requestObject = JsonMapper.deserialize(req.getReader(), this.controller.getRequestClass());
            responseObject = this.controller.handle(requestObject);

            Responder.responseJson((HttpServletResponse) res, this.controller.getResponseClass().cast(responseObject));
        } catch (Exception e) {
            Responder.responseEmpty((HttpServletResponse) res);
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
