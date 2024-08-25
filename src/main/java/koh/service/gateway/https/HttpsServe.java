package koh.service.gateway.https;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpsServe {
    final Server server;
    final SslConnector sslConnector;
    final HttpsContext context;
    public HttpsServe(int port, String keyStoreType, String keyStorePath, String keyStorePassword) {
        this.server = new Server();
        this.sslConnector = new SslConnector(this.server, port, keyStoreType, keyStorePath, keyStorePassword);
        this.context = new HttpsContext();
    }

    public void path(String path, Servlet servlet) {
        this.context.addServlet(new ServletHolder(servlet), path);
    }

    public void start()
            throws Exception {
        this.server.addConnector(sslConnector);
        this.server.setHandler(this.context);
        this.server.start();
    }
}
