package koh.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.server.core.Response;
import koh.server.base.BaseController;
import koh.server.base.SerializableResponse;


import java.io.IOException;

public class GoodbyeController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Response.responseJson(resp, new SerializableResponse() {
            @Override
            public String toJson() {
                return "{ \"content\": \"Goodbye, world!\" }";
            }
        });
    }
}
