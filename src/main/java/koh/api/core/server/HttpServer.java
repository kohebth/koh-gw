package koh.api.core.server;

import koh.api.core.config.GlobalConfig;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

public class HttpServer {
    final GlobalConfig config;
    final Server server;

    public HttpServer(GlobalConfig config) {
        this.config = config;
        this.server = new Server();
    }

    public void start()
            throws Exception {
        final Connector[] connectors = initializeConnectors();
        final Handler handlers = initializeHandlers();
        final ThreadPool threadPool = initializeThreadPool();

        this.server.setHandler(handlers);
        this.server.setConnectors(connectors);
        this.server.
        server.start();
        server.join();
    }

    Connector[] initializeConnectors() {
        ServerConnector serverConnector = new ServerConnector(this.server);
        serverConnector.setPort(config.getPort());
        serverConnector.setIdleTimeout(config.getIdleTimeout());
        serverConnector.setHost(config.getHost());
        return null;
    }

    Handler initializeHandlers() {
        return new HandlerCollection();
    }

    ThreadPool initializeThreadPool() {
        return new QueuedThreadPool(config.thread, 2, 30 * 60 * 1000);
    }
}
