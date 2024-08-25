package koh.service.gateway.https;

import org.eclipse.jetty.servlet.ServletContextHandler;

public class HttpsContext extends ServletContextHandler {
    HttpsContext() {
        super(SESSIONS);
        this.setContextPath("/");
    }
}
