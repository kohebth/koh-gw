package koh.service.auth.handler;

import koh.service.auth.UserRepository;

import java.util.Objects;

public class ForgetService {
    UserRepository userDAO = new UserRepository();

    public Object forget(String email) {
        return userDAO.findAll().stream().filter(o -> Objects.equals(o.getEmail(), email)).findFirst();
    }
}
