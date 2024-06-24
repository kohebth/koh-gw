package koh.core;

import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.SerializableResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class Response {
    public static void responseJson(HttpServletResponse resp, SerializableResponse content)
            throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write(content.toJson());
        out.flush();
    }

    public static void responseToNonExistedPath(HttpServletResponse resp)
            throws IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.getWriter().write("{ \"status\": \"Not Found\"}");
        resp.getWriter().flush();
    }

    private Response() {
    }
}
