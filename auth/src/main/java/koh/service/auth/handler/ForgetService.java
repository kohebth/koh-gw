package koh.service.auth.handler;

import koh.db.hub.tables.daos.UserDao;

public class ForgetService {
    UserDao userDAO = new UserDao();

    public Object forget(String email) {
        return userDAO.fetchByEmail(email).stream().findFirst();
    }
}
