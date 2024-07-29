package koh.core.helper;

import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Slf4j
public class ResponseTools {

    private static final SimpleResponse EMPTY_RESPONSE = new SimpleResponse() {};

    public static void responseJson(HttpServletResponse resp, SimpleResponse content)
            throws IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(JsonTools.serialize(content));
    }

    public static void responseToNonExistedPath(HttpServletResponse resp)
            throws IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.getWriter().write("{ \"status\": \"Not Found\"}");
    }

    public static void responseEmpty(HttpServletResponse resp)
            throws IOException {
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.getWriter().write(JsonTools.serialize(EMPTY_RESPONSE));
    }

    private ResponseTools() {
    }
}
