package koh.core.helper;

import jakarta.servlet.http.HttpServletResponse;
import koh.core.base.SerializableResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.HttpCookieStore;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class Responder {

    private static final SerializableResponse EMPTY_RESPONSE = new SerializableResponse() {};

    public static void responseJson(HttpServletResponse resp, SerializableResponse content)
            throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_OK);
        out.write(JsonMapper.serialize(content));
        out.flush();
    }

    public static void responseToNonExistedPath(HttpServletResponse resp)
            throws IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.getWriter().write("{ \"status\": \"Not Found\"}");
        resp.getWriter().flush();
    }

    public static SerializableResponse emptySerializableResponse() {
        return EMPTY_RESPONSE;
    }

    private Responder() {
    }
}
