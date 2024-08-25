package koh.service.gateway.servlet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;

import java.io.IOException;
import java.io.InputStream;

public class ResourceServlet extends AbstractGatewayServlet {

    protected ResourceServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        try (InputStream is = ResourceServlet.class.getResourceAsStream("/webapp/resource.html")) {
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
            throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        String secretKey = req.getHeader("X-Secret-Key");

        if (token != null && token.startsWith("Bearer ") && secretKey != null) {
            String jwtToken = token.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(jwtToken)
                        .getBody();

                String email = claims.get("email", String.class);
                String resource = req.getParameter("resource");

                req.setAttribute("email", email);

                switch (resource) {
                    case "vps":
                        RequestDispatcher vpsDispatcher = req.getRequestDispatcher("/vps");
                        vpsDispatcher.forward(req, resp);
                        break;
                    case "mysql":
                        RequestDispatcher mysqlDispatcher = req.getRequestDispatcher("/mysql");
                        mysqlDispatcher.forward(req, resp);
                        break;
                    default:
                        resp.getWriter().println("Invalid resource type");
                }

            } catch (SignatureException e) {
                resp.getWriter().println("Invalid token or secret key");
            }
        } else {
            resp.getWriter().println("Authorization or secret key header missing or invalid");
        }
    }

    private String generateRequestId() {
        return Long.toString(System.currentTimeMillis());
    }
}
