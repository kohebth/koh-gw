package koh.service.auth.route;

import koh.core.base.SimpleController;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;
import koh.db.hub.vps_management.tables.pojos.User;
import koh.service.auth.handler.ForgetService;

public class ForgetController implements SimpleController {
    @Override
    public SimpleResponse handle(SimpleRequest request) {
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
    public Class<? extends SimpleRequest> getRequestClass() {
        return ForgetUserRequest.class;
    }

    @Override
    public Class<? extends SimpleResponse> getResponseClass() {
        return ForgetUserResponse.class;
    }

    private static class ForgetUserRequest implements SimpleRequest {
        public String email;
    }

    private static class ForgetUserResponse implements SimpleResponse {
        public int code;
        public String message;
    }
}
