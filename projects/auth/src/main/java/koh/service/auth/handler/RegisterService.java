package koh.service.auth.handler;

import koh.db.hub.DSLContextFactory;
import koh.db.hub.vps_management.tables.daos.UserDao;
import koh.db.hub.vps_management.tables.pojos.User;

import java.util.Optional;

public class RegisterService {
    public Optional<User> register(String email, String password) {
        UserDao userDao = DSLContextFactory.useDao(UserDao.class);

        Optional<User> existedUser = userDao.fetchByEmail(email).stream().findFirst();
        if (existedUser.isPresent()) {
            return Optional.empty();
        }
        User newUser = new User(null, email, password);
        userDao.insert(newUser);
        return Optional.of(newUser);
    }
}
