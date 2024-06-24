package koh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.core.Response;
import koh.core.base.BaseController;
import koh.core.base.SerializableResponse;

import java.io.IOException;

public class HelloController extends BaseController {
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
