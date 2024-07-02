package koh.server.core;

import koh.server.config.ServerProperties;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

public class HttpServer {
    final ServerProperties config;
    Server server;

    public HttpServer(ServerProperties config) {
        this.config = config;
    }

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
            serverConnector.setPort(config.getPort());
            serverConnector.setIdleTimeout(config.getIdleTimeout());
            serverConnector.setHost(config.getHost());
            return new Connector[]{serverConnector};
        }
    }

    Handler initializeHandlers() {
        ServletContextHandler handlers = new ServletContextHandler();
        handlers.addServlet(HttpRoute.class, "/");
        return new ServletContextHandler();
    }

    ThreadPool initializeThreadPool() {
        return new QueuedThreadPool(config.getMaxThreadNum(), config.getMinThreadNum(), 30 * 60 * 1000);
    }
}
