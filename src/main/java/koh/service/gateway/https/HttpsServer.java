package koh.service.gateway.https;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.EnumSet;

public class HttpsServer {
    final Server server;
    final SslConnector sslConnector;
    final HttpsContext context;

    public HttpsServer(int port, String keyStoreType, String keyStorePath, String keyStorePassword) {
        this.server = new Server();
        this.sslConnector = new SslConnector(this.server, port, keyStoreType, keyStorePath, keyStorePassword);
        this.context = new HttpsContext();
    }

    public void path(String path, Servlet servlet) {
        this.context.addServlet(new ServletHolder(servlet), path);
    }

    public void path(String path, Servlet servlet, Filter... filters) {
        this.context.addServlet(new ServletHolder(servlet), path);
        for (Filter filter : filters) {
            this.context.addFilter(new FilterHolder(filter), path, EnumSet.of(DispatcherType.REQUEST));
        }
    }

    public void start()
            throws Exception {
        this.server.addConnector(sslConnector);
        this.server.setHandler(this.context);
        this.server.start();
    }
}
