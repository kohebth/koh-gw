package koh.service.loopback;

import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import lombok.Setter;

@Setter
public class LoopBackRequest implements SerializableRequest {
    String name;
    String status;

    LoopBackRequest() {}

    @Override
    public Object fromJson() {
        return null;
    }

    @Override
    public Object fromProto() {
        return null;
    }
}
