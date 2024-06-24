package koh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.core.Response;
import koh.core.base.BaseController;
import koh.service.loopback.LoopBackRequest;
import koh.service.loopback.LoopBackService;

import java.io.BufferedReader;
import java.io.IOException;

public class LoopBackController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();
        ObjectMapper objectMapper = new ObjectMapper();

        LoopBackService s = new LoopBackService(objectMapper.readValue(body, LoopBackRequest.class));
        Response.responseJson(resp, s.serve());
    }
}
