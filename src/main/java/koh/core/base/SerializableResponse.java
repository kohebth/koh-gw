package koh.core.base;

import javax.ws.rs.NotSupportedException;

public interface SerializableResponse {
    default String toJson() {
        throw new NotSupportedException();
    }
    default String toProto() {
        throw new NotSupportedException();
    }
}
