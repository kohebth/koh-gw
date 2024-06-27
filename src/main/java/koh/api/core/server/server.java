package koh.api.core.server;

import koh.register.PathRegister;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

public class server {
    ServletContextHandler createContextHandler()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");

        // Register servlets
        for (PathRegister register : PathRegister.values()) {
            BaseController h = register.getHandlerClass().getConstructor().newInstance();
            String path = register.getPath();
            contextHandler.addServlet(new ServletHolder(h), path);
        }
        return contextHandler;
    }

    ServerConnector createServerConnector(Server server, HttpConfiguration configuration) {
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(config.getPort());
        return null;
    }

    SslConnectionFactory createSslConnectionFactory(HttpConnectionFactory httpConnectionFactory) {
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(config.getSslKeyStorePath());
        sslContextFactory.setKeyStorePassword(config.getSslKeyStorePassword());

        return new SslConnectionFactory(sslContextFactory, httpConnectionFactory.getProtocol());
    }

    HttpConnectionFactory createConnectionFactory(HttpConfiguration httpConfiguration) {
        return new HttpConnectionFactory(httpConfiguration);
    }

    HttpConfiguration createHttpConfiguration() {
        // The HTTP configuration object.
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecurePort(config.getSecurePort());
        httpConfig.setRequestHeaderSize(config.getRequestHeaderSize());
        httpConfig.setResponseHeaderSize(config.getResponseHeaderSize());
        httpConfig.setHeaderCacheSize(config.getRequestHeaderSize());
        httpConfig.setIdleTimeout(10000);
        httpConfig.setLocalAddress(new InetSocketAddress("0.0.0.0", 9311));
        return null;
    }
}
