package koh.service.auth.route;

import koh.core.base.SimpleController;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;

public class AuthController implements SimpleController {
    @Override
    public SimpleResponse handle(SimpleRequest request) {
        AuthRequest payload = (AuthRequest) request;
        AuthResponse response = new AuthResponse();
        if (payload.transientToken.equals("joker")) {
            response.message = "Success";
            response.publicSessionId = "ace";
        }
        return response;
    }

    @Override
    public String getPath() {
        return "/auth";
    }

    @Override
    public Class<? extends SimpleRequest> getRequestClass() {
        return AuthRequest.class;
    }

    @Override
    public Class<? extends SimpleResponse> getResponseClass() {
        return AuthResponse.class;
    }

    private static class AuthRequest implements SimpleRequest {
        public String transientToken;
    }

    private static class AuthResponse implements SimpleResponse {
        public String message;
        public String publicSessionId;
    }
}
