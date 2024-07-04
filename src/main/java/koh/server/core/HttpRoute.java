package koh.server.core;

import jakarta.servlet.*;
import koh.server.base.BaseController;
import sun.reflect.ReflectionFactory;

import java.io.IOException;

public class HttpRoute implements Servlet {
    @Override public void init(ServletConfig config)
            throws ServletException {

    }

    @Override public ServletConfig getServletConfig() {
        return null;
    }

    @Override public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

    }

    @Override public String getServletInfo() {
        return null;
    }

    @Override public void destroy() {

    }
}
