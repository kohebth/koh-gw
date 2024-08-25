package koh.service.gateway;

import koh.service.gateway.https.HttpsServe;
import koh.service.gateway.kafka.KafkaEventController;
import koh.service.gateway.servlet.*;

public class App {
    final KafkaEventController kafkaEventController;
    final HttpsServe httpsServe;

    App() {
        // Make a Kafka Provider/Consumer into request/response handler
        kafkaEventController = new KafkaEventController(AppConfig.KAFKA_NODE, AppConfig.KAFKA_GROUP);

        // Make an SslContextFactory with PKCS12 keystore configuration
        httpsServe = new HttpsServe(
                443,
                AppConfig.HTTPS_KEYSTORE_TYPE,
                AppConfig.HTTPS_KEYSTORE_PATH,
                AppConfig.HTTPS_KEYSTORE_PASSWORD
        );
    }

    void configure() {
//        httpsServe.path("/login", new LoginServlet(kafkaEventController));
        httpsServe.path("/register", new RegisterServlet(kafkaEventController));
//        httpsServe.path("/resource", new ResourceServlet(kafkaEventController));
//        httpsServe.path("/vps", new VpsServlet(kafkaEventController));
//        httpsServe.path("/mysql", new MysqlServlet(kafkaEventController));
    }

    void start()
            throws Exception {
        httpsServe.start();
    }

    public static void main(String[] args)
            throws Exception {
        App app = new App();
        app.configure();
        app.start();
    }
}
