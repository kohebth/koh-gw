package koh.service.auth.route;

import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.db.hub.tables.pojos.User;
import koh.service.auth.handler.ForgetService;

public class ForgetController implements Controller {
    @Override
    public SerializableResponse handle(SerializableRequest request) {
        ForgetUserRequest payload = (ForgetUserRequest) request;
        ForgetUserResponse response = new ForgetUserResponse();

        User user = (User) new ForgetService().forget(payload.email);
        if (user != null) {
            user.getEmail();
        }

        return null;
    }

    @Override
    public String getPath() {
        return "/forgot";
    }

    @Override
    public Class<? extends SerializableRequest> getRequestClass() {
        return ForgetUserRequest.class;
    }

    @Override
    public Class<? extends SerializableResponse> getResponseClass() {
        return ForgetUserResponse.class;
    }

    private static class ForgetUserRequest implements SerializableRequest {
        public String email;
    }

    private static class ForgetUserResponse implements SerializableResponse {
        public int code;
        public String message;
    }
}
