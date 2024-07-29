package koh.service.auth.handler;

import koh.db.hub.DSLContextFactory;
import koh.db.hub.tables.daos.UserDao;
import koh.db.hub.tables.pojos.User;

import java.sql.SQLException;
import java.util.Optional;

public class RegisterService {
    public Optional<User> register(String email, String password) {
        UserDao userDao = null;
        try {
            userDao = new UserDao(DSLContextFactory.getContext().configuration());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Optional<User> existedUser = userDao.fetchByEmail(email).stream().findFirst();
        if (existedUser.isPresent()) {
            return Optional.empty();
        }
        User newUser = new User(null, email, password);
        userDao.insert(newUser);
        return Optional.of(newUser);
    }
}
