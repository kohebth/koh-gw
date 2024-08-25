package koh.service.gateway.secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Password extends AbstractPassword {
    public Password(String raw) {
        super(raw);
    }

    static byte[] sha256(byte[] bytes) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            return m.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordToolsException(e);
        }
    }

    static byte[] base64(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    @Override
    protected String secure(String rawPassword) {
        return new String(base64(sha256(rawPassword.getBytes())));
    }

    static class PasswordToolsException extends RuntimeException {
        PasswordToolsException(Exception e) {
            super(e);
        }
    }
}
