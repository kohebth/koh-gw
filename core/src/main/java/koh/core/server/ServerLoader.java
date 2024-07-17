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
abstract class ServerLoader {
    ServerConfiguration config;
    Server server;

    public void start()
            throws Exception {
        this.server = new Server(initializeThreadPool());
        this.server.setHandler(initializeHandlers());
        this.server.setConnectors(initializeConnectors());
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    Connector[] initializeConnectors() {
        try (ServerConnector serverConnector = new ServerConnector(this.server)) {
            serverConnector.setPort(Integer.parseInt(config.getPort()));
            serverConnector.setIdleTimeout(config.getIdleTimeout());
            serverConnector.setHost(config.getHost());
            log.info("Server connectors have been initialized");
            return new Connector[]{serverConnector};
        }
    }

    Handler initializeHandlers() {
        ServletContextHandler handlers = new ControllerLoader().loadAsContextHandler(config.getRouteClass());
        log.info("Server core handlers have been initialized");
        return handlers;
    }

    ThreadPool initializeThreadPool() {
        return new QueuedThreadPool(config.getMaxThreadNum(), config.getMinThreadNum(), 30 * 60 * 1000);
    }
}
