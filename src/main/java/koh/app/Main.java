package koh.app;

import koh.controller.HelloController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args)
            throws Exception {
        Server server = new Server();
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(HelloController.class, "/hello");

        server.setHandler(contextHandler);
        server.start();
        server.join();
    }
}
