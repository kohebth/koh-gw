package koh.service.auth.route;

import com.sun.jna.platform.win32.COM.Unknown;
import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.service.auth.ResponseCode;
import koh.service.auth.handler.RegisterService;
import koh.service.auth.handler.SendEmailService;
import koh.service.auth.helper.Helper;
import koh.service.auth.model.User;

import java.util.Optional;

public class RegisterController implements Controller {
    RegisterService registerService = new RegisterService();
    SendEmailService sendEmailService = new SendEmailService();

    @Override public SerializableResponse handle(SerializableRequest request) {
        RegisterRequest payload = (RegisterRequest) request;
        RegisterResponse response = new RegisterResponse();

        if (!Helper.isSisEmailValid(payload.email)) {
            response.message = "Your email is not at `sis.hust.edu.vn`";
            response.code = ResponseCode.INVALID_EMAIL.code;
        }

        RegisterService registerHandler = new RegisterService();
        Optional<User> user = registerHandler.register(payload.email, payload.password);

        if (user.isPresent()) {
            sendEmailService.sendAccessTokenEmail(user.get());

            response.code = ResponseCode.CREATED_USER.code;
            response.message = "New User is created";
        }
        return response;
    }

    @Override public String getPath() {
        return "/register";
    }

    @Override public Class<? extends SerializableRequest> getRequestClass() {
        return RegisterRequest.class;
    }

    @Override public Class<? extends SerializableResponse> getResponseClass() {
        return RegisterResponse.class;
    }

    private static class RegisterRequest implements SerializableRequest {
        public String email;
        public String password;
    }

    private static class RegisterResponse implements SerializableResponse {
        public int code = ResponseCode.BAD_REQUEST.code;
        public String message = ResponseCode.BAD_REQUEST.name();
    }
}
