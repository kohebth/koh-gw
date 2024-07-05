package koh.service.auth.model;

import java.time.LocalDateTime;

public class Session {
    User user;
    String publicSessionId;
    LocalDateTime expireTime;
}
