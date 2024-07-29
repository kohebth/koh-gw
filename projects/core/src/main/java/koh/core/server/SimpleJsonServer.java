package koh.core.server;

import koh.core.config.ServerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;

@Slf4j
public class SimpleJsonServer extends ServerHolder {
    public SimpleJsonServer(ServerConfiguration config) {
        this.controllerConfiguration = new ControllerConfiguration();
        this.serverConfiguration = config;
        this.serverInstant = new Server();
    }
}
