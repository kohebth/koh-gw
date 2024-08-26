package koh.service.gateway.https.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.AppConfig;
import koh.service.gateway.kafka.KafkaEventController;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public abstract class AbstractGatewayServlet extends HttpServlet {
    protected final KafkaEventController kafkaEventController;

    protected AbstractGatewayServlet(KafkaEventController kafkaEventController) {
        this.kafkaEventController = kafkaEventController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Request failure");
            if (Boolean.TRUE.equals(AppConfig.HTTPS_INTERNAL_RESPONSE)) {
                resp.getWriter().write("Error log: ");
                resp.getWriter().write(Arrays.toString(e.getStackTrace()));
            }
            resp.flushBuffer();

            log.error("Caught exception while handling request {}", req);
        } finally {
            req.getReader().close();
            resp.getWriter().close();
        }
    }
}
