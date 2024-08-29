package koh.service.gateway.kafka.message;

import lombok.Data;

@Data
public class NewVolumeMessage {
    String name;
    String host;
    String virtual;
    int sizeInMb;
}
