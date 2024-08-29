package koh.service.gateway.kafka.message;

import lombok.Data;

@Data
public class NewContainerMessage {
    long userId;
    String name;
}
