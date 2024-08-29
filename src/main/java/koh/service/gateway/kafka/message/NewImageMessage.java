package koh.service.gateway.kafka.message;

import lombok.Data;

@Data
public class NewImageMessage {
    String name;
    String repo;
    String version;
}
