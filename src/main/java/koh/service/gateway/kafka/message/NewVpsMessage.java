package koh.service.gateway.kafka.message;

import lombok.Data;

@Data
public class NewVpsMessage {
    long userId;
    String email;
    String os;
    String networkId;
    int volumeSizeInMb;
}
