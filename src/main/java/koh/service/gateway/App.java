package koh.service.gateway;

import koh.service.gateway.https.HttpsServer;
import koh.service.gateway.https.filter.JwtAuthorizer;
import koh.service.gateway.https.servlet.*;
import koh.service.gateway.kafka.KafkaEventController;
import koh.service.gateway.secure.Jwt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static koh.service.gateway.kafka.KafkaReqTopic.*;

public class App {
    final Jwt jwt;
    final KafkaEventController kafkaEventController;
    final HttpsServer https;

    App()
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        jwt = new Jwt(AppConfig.APP_PUBLIC_KEY_PATH);
        // Make a Kafka Provider/Consumer into request/response handler
        kafkaEventController = new KafkaEventController(AppConfig.KAFKA_NODE, AppConfig.KAFKA_GROUP);

        // Make an SslContextFactory with PKCS12 keystore configuration
        https = new HttpsServer(
                443,
                AppConfig.HTTPS_KEYSTORE_TYPE,
                AppConfig.HTTPS_KEYSTORE_PATH,
                AppConfig.HTTPS_KEYSTORE_PASSWORD
        );
    }

    void configure() {
        JwtAuthorizer authorizer = new JwtAuthorizer(jwt);
        https.path("/request/*", new RequestServlet(kafkaEventController));
        https.path("/register", new ForwardServlet(TOPIC_AUTH_REGISTER_REQUEST, kafkaEventController));
        https.path("/login", new ForwardServlet(TOPIC_AUTH_LOGIN_REQUEST, kafkaEventController));
        https.path("/refresh", new ForwardServlet(TOPIC_AUTH_REFRESH_REQUEST, kafkaEventController), authorizer);
        https.path("/vps", new VpsServlet(kafkaEventController), authorizer);
        https.path("/mysql", new MysqlServlet(kafkaEventController), authorizer);
    }

    void start()
            throws Exception {
        https.start();
    }

    public static void main(String[] args)
            throws Exception {
        App app = new App();
        app.configure();
        app.start();
    }
}
