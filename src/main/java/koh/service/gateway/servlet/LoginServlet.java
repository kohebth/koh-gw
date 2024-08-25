package koh.service.gateway.servlet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoginServlet extends AbstractGatewayServlet {
    public LoginServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        try (InputStream is = LoginServlet.class.getResourceAsStream("/webapp/login.html")) {
            if (is != null) {
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                resp.getOutputStream().write(buffer);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

    }
}
