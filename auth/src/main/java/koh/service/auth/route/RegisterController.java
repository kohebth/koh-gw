package koh.service.auth.route;

import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.db.hub.tables.pojos.User;
import koh.service.auth.ResponseCode;
import koh.service.auth.handler.RegisterService;
import koh.service.auth.handler.SendEmailService;
import koh.service.auth.helper.Validators;

import java.util.Optional;

public class RegisterController implements Controller {
    RegisterService registerService = new RegisterService();
    SendEmailService sendEmailService = new SendEmailService();
    Validators validators = new Validators();

    @Override
    public SerializableResponse handle(SerializableRequest request) {
        RegisterRequest payload = (RegisterRequest) request;
        RegisterResponse response = new RegisterResponse();

        if (!validators.isSchoolEmail(payload.email)) {
            response.message = "Please, enter your school email!`";
            response.code = ResponseCode.INVALID_EMAIL.code;
            return response;
        }

        Optional<User> user = registerService.register(payload.email, payload.password);

        if (user.isPresent()) {
            sendEmailService.sendAccessTokenEmail(user.get());

            response.code = ResponseCode.CREATED_USER.code;
            response.message = "User is registered successfully";
        }
        return response;
    }

    @Override
    public String getPath() {
        return "/register";
    }

    @Override
    public Class<? extends SerializableRequest> getRequestClass() {
        return RegisterRequest.class;
    }

    @Override
    public Class<? extends SerializableResponse> getResponseClass() {
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
