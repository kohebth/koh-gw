package koh.model;

import koh.server.base.Entity;

import java.time.LocalDateTime;

public class Session extends Entity {
    User user;
    String publicSessionId;
    LocalDateTime expireTime;
}
