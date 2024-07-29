package koh.service.vps.route;

import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;

public class MigrationController implements Controller {
    @Override
    public SerializableResponse handle(SerializableRequest request) {
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
    public Class<? extends SerializableRequest> getRequestClass() {
        return AuthRequest.class;
    }

    @Override
    public Class<? extends SerializableResponse> getResponseClass() {
        return AuthResponse.class;
    }

    private static class AuthRequest implements SerializableRequest {
        public String transientToken;
    }

    private static class AuthResponse implements SerializableResponse {
        public String message;
        public String publicSessionId;
    }
}
