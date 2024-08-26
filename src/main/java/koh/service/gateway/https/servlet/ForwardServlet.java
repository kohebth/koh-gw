package koh.service.gateway.https.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;
import koh.service.gateway.kafka.KafkaReqTopic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ForwardServlet extends AbstractGatewayServlet {

    final KafkaReqTopic topic;

    public ForwardServlet(KafkaReqTopic topic, KafkaEventController kafkaEventController) {
        super(kafkaEventController);
        this.topic = topic;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String messageBody;
        try (BufferedReader reader = req.getReader()) {
            messageBody = reader.lines().collect(Collectors.joining("\n"));
        }
        String requestId = kafkaEventController.requestEvent(topic, messageBody);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(requestId);
        }
    }
}
