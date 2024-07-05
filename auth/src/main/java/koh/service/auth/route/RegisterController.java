package koh.service.auth.route;

import koh.core.base.Controller;
import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController implements Controller {
    Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@sis\\.hust\\.edu\\.vn$");

    @Override
    public SerializableResponse handle(SerializableRequest request) {
        RegisterRequest payload = (RegisterRequest) request;
        RegisterResponse response = new RegisterResponse();
        Matcher matcher = emailPattern.matcher(payload.email);
        if (matcher.matches()) {
            response.message = "Good, wait for you email!";
        } else {
            response.message = "Nah, input another email, please!";
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

    static class RegisterRequest implements SerializableRequest {
        public String email;
        public String password;
    }

    static class RegisterResponse implements SerializableResponse {
        public String message;
    }


}
