package koh.core.server;

import koh.core.config.ServerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

@Slf4j
abstract class ServerHolder {
    ControllerConfiguration controllerConfiguration;
    ServerConfiguration serverConfiguration;
    Server serverInstant;

    public void start()
            throws Exception {
        this.serverInstant = new Server(initializeThreadPool());
        this.serverInstant.setHandler(initializeHandlers());
        this.serverInstant.setConnectors(initializeConnectors());
        try {
            serverInstant.start();
            serverInstant.join();
        } finally {
            serverInstant.destroy();
        }
    }

    Connector[] initializeConnectors() {
        try (ServerConnector serverConnector = new ServerConnector(this.serverInstant)) {
            serverConnector.setPort(Integer.parseInt(serverConfiguration.getPort()));
            serverConnector.setIdleTimeout(serverConfiguration.getIdleTimeout());
            serverConnector.setHost(serverConfiguration.getHost());
            log.info("Server connectors have been initialized");
            return new Connector[]{serverConnector};
        }
    }

    Handler initializeHandlers() {
        String routeClass = serverConfiguration.getRouteClass();
        ServletContextHandler handlers = this.controllerConfiguration.loadAsContextHandler(routeClass);
        log.info("Server core handlers have been initialized");
        return handlers;
    }

    ThreadPool initializeThreadPool() {
        return new QueuedThreadPool(serverConfiguration.getMaxThreadNum(),
                serverConfiguration.getMinThreadNum(),
                30 * 60 * 1000
        );
    }
}
