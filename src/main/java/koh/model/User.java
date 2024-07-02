package koh.model;

import koh.server.base.Entity;

import java.time.LocalDateTime;

public class User extends Entity {
    String email;
    String password;
    LocalDateTime lastLoginTime;
}
