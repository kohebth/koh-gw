package koh.service.auth.handler;

import koh.service.auth.UserRepository;
import koh.service.auth.model.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class RegisterService {
    UserRepository userDAO = new UserRepository();

    public Optional<User> register(String email, String password) {
        Optional<User>
                existedUser = userDAO.findAll()
                .stream()
                .filter(o -> Objects.equals(o.getEmail(), email))
                .findFirst()
                .stream()
                .findAny();
        if (existedUser.isPresent()) {
            return Optional.empty();
        }

        User newUser = userDAO.save(new User(null, email, password, LocalDateTime.now()));
        return Optional.of(newUser);
    }
}
