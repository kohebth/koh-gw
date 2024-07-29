package koh.service.vps;

import koh.core.config.ServerConfiguration;
import koh.core.server.SimpleJsonServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        ServerConfiguration serverConfiguration = ServerConfiguration
                .builder()
                .routeClass(VpsRoute.class.getName())
                .build();
        SimpleJsonServer simpleJsonServer = new SimpleJsonServer(serverConfiguration);

        try {
            simpleJsonServer.start();
        } catch (Exception e) {
            log.error("Failed to start HTTP Server", e);
        }
    }
}