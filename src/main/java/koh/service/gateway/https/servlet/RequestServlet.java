package koh.service.gateway.https.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.AppConfig;
import koh.service.gateway.kafka.KafkaEventController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class RequestServlet extends AbstractGatewayServlet {

    public RequestServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestId = req.getPathInfo().substring(1);
        String timeoutRaw = req.getParameter("timeout");

        int timeout;

        if (timeoutRaw != null) {
            timeout = Integer.parseInt(timeoutRaw);
            timeout = Math.min(timeout, AppConfig.HTTPS_MAX_TIMEOUT_RESPONSE_SECONDS);
        } else {
            timeout = AppConfig.HTTPS_MAX_TIMEOUT_RESPONSE_SECONDS;
        }

        String message = responseContinue(requestId, timeout);

        resp.getWriter().write(message);
    }

    String responseContinue(String requestId, int timeout)
            throws ServletException {
        try {
            return kafkaEventController.responseEvent(requestId, timeout);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            Thread.currentThread().interrupt();
            throw new ServletException(e);
        }
    }
}
