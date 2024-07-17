package koh.service.auth;

import koh.core.server.JsonServer;
import koh.core.config.ServerConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        ServerConfiguration serverConfiguration = new ServerConfiguration();
        JsonServer httpServer = new JsonServer(serverConfiguration);

        try {
            httpServer.start();
        } catch (Exception e) {
            log.error("Failed to start HTTP Server", e);
        }
    }
}