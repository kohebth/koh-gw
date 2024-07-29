package koh.service.auth.helper;

import javax.crypto.KeyGenerator;

public class Initiators {
    public static KeyGenerator getAESKeyGenerator() {
        try {
            return KeyGenerator.getInstance("AES");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
