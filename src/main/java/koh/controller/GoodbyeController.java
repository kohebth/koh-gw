package koh.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.api.core.Response;
import koh.api.core.base.BaseController;
import koh.api.core.base.SerializableResponse;


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
