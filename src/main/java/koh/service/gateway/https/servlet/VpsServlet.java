package koh.service.gateway.https.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;

import java.io.IOException;

public class VpsServlet extends AbstractGatewayServlet {

    public VpsServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
