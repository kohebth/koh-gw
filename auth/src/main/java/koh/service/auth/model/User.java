package koh.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    Long id;
    String email;
    String password;
    LocalDateTime lastLoginTime;
}
