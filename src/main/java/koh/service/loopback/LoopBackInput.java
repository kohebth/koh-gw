package koh.service.loopback;

import koh.core.base.SerializableRequest;
import lombok.Setter;

@Setter
public class LoopBackInput implements SerializableRequest {
    String name;
    String status;

    LoopBackInput() {}

    @Override
    public Object fromJson() {
        return null;
    }

    @Override
    public Object fromProto() {
        return null;
    }
}
