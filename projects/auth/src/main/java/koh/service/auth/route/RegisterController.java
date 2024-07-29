package koh.service.auth.route;

import koh.core.base.SimpleController;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;
import koh.db.hub.vps_management.tables.pojos.User;
import koh.service.auth.ResponseCode;
import koh.service.auth.handler.RegisterService;
import koh.service.auth.handler.SendEmailService;
import koh.service.auth.tools.Validators;

import java.util.Optional;

public class RegisterController implements SimpleController {
    RegisterService registerService = new RegisterService();
    SendEmailService sendEmailService = new SendEmailService();
    Validators validators = new Validators();

    @Override
    public SimpleResponse handle(SimpleRequest request) {
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
    public Class<? extends SimpleRequest> getRequestClass() {
        return RegisterRequest.class;
    }

    @Override
    public Class<? extends SimpleResponse> getResponseClass() {
        return RegisterResponse.class;
    }

    public static class RegisterRequest implements SimpleRequest {
        public String email;
        public String password;
    }

    public static class RegisterResponse implements SimpleResponse {
        public int code = ResponseCode.BAD_REQUEST.code;
        public String message = ResponseCode.BAD_REQUEST.name();
    }
}
