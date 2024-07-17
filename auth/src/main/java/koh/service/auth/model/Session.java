package koh.service.auth.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {
    final User user;
    final String publicSessionId;
    final LocalDateTime expireTime;
}
