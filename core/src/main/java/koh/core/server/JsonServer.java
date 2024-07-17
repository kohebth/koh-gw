package koh.core.server;

import koh.core.config.ServerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;

@Slf4j
public class JsonServer extends ServerLoader {

    public JsonServer(ServerConfiguration config) {
        this.config = config;
        this.server = new Server();
    }
}
