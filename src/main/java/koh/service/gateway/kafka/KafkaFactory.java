package koh.service.gateway.kafka;

import koh.service.gateway.kafka.message.RegisterMessage;

public class KafkaFactory {
    public static RegisterMessage createRegisterMessage(String email, String password) {
        RegisterMessage m = new RegisterMessage();
        m.email = email;
        m.password = password;
        return m;
    }
}
