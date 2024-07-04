package koh.app;

import koh.server.config.ApplicationProperties;
import koh.server.config.ServerProperties;
import koh.server.core.HttpServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Main Application")
public class App {

    ApplicationProperties applicationProperties;
    ServerProperties serverProperties;

    private App() {
        applicationProperties = ApplicationProperties.getDefaultConfig();
        serverProperties = ServerProperties.getDefaultConfig();
    }

    public static void main(String[] args) {
        try {
            App app = new App();
            HttpServer httpServer = new HttpServer(app.serverProperties);
            httpServer.start();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
