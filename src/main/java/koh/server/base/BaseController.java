package koh.server.base;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static koh.server.core.Response.responseToNonExistedPath;

@Slf4j
public abstract class BaseController extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }

    @Override protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        responseToNonExistedPath(resp);
    }
}
