package koh.service.gateway.https.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.secure.Jwt;

import java.io.IOException;
import java.util.Date;

public class JwtAuthorizer extends HttpFilter {

    final transient Jwt jwt;

    public JwtAuthorizer(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Date now = new Date();
        String authorizationHeader = req.getHeader("Authorization");
        String path = req.getServletPath();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extract the token

            // Claims the JWT token
            Claims claims = jwt.verify(token);

            // Validate the token (you would need to implement the validateToken method)
            boolean isValid = claims.getSubject().contains("@sis.hust.edu.vn");

            if (isValid) {
                // Proceed with the request if valid
                chain.doFilter(req, res);
            } else {
                // Respond with an error if the token is invalid
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Invalid or expired token");
            }
        } else {
            // Respond with an error if the Authorization header is missing or malformed
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Authorization header missing or malformed");
        }
    }
}
