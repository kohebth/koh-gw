package koh;

import koh.core.base.BaseController;
import koh.register.PathRegister;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpServer {
    public static void main(String[] args)
            throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Register servlets
        for (PathRegister register: PathRegister.values()) {
            BaseController h = register.getHandlerClass().getConstructor().newInstance();
            String path = register.getPath();
            handler.addServlet(new ServletHolder(h), path);
        }

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
