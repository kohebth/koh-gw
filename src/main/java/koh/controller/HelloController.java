package koh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.server.core.Response;
import koh.server.base.BaseController;
import koh.server.base.SerializableResponse;

import javax.ws.rs.Path;
import java.io.IOException;

public class HelloController extends BaseController {
    @Path("hello")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Response.responseJson(resp, new SerializableResponse() {
            @Override
            public String toJson() {
                return "{ \"content\": \"Hello, world!\" }";
            }
        });
    }
}
