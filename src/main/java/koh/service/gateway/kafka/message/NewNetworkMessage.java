package koh.service.gateway.kafka.message;

import lombok.Data;

@Data
public class NewNetworkMessage {
    String name;
    String subnet;
    String gateway;
    String ipRange;
}
