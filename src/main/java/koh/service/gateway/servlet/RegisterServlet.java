package koh.service.gateway.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;
import koh.service.gateway.kafka.KafkaFactory;
import koh.service.gateway.kafka.KafkaTopic;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class RegisterServlet extends AbstractGatewayServlet {
    public RegisterServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        JsonNode body = new ObjectMapper().readTree(req.getReader());

        String email = body.get("email").asText();
        String password = body.get("password").asText();

        // Send email and password to Kafka and wait for a response message
        String requestId = kafkaEventController.requestEvent(
                KafkaTopic.TOPIC_AUTH_REGISTER_REQUEST,
                KafkaFactory.createRegisterMessage(email, password)
        );

        try {
            // Wait for up to 30 seconds
            String response = kafkaEventController.responseEvent(requestId, 30);
            resp.getWriter().write(response);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new ServletException(e);
        }
    }
}
