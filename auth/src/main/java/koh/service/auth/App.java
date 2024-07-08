package koh.service.auth;

import koh.core.HttpServer;
import koh.core.config.ServerProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        ServerProperties serverProperties = ServerProperties.getConfig("/server.properties");
        HttpServer httpServer = new HttpServer(serverProperties);

        try {
            httpServer.start();
        } catch (Exception e) {
            log.error("Failed to start HTTP Server", e);
        }
    }
}