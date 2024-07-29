package koh.service.auth.handler;

import koh.service.auth.helper.Initiators;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SessionService {
    final KeyGenerator keyGenerator;

    public SessionService() {
        keyGenerator = Initiators.getAESKeyGenerator();
    }

    public Object open() {
        keyGenerator.init(128);
        SecretKey sessionKey = keyGenerator.generateKey();
//        new Session()
//        SessionDao();
        return null;
    }

    public Object close() {

        return null;
    }
}
