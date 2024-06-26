package koh.core.base;

import koh.service.loopback.LoopBackInput;
import koh.service.loopback.LoopBackOutput;

public interface Service<V extends SerializableRequest, T extends SerializableResponse> {
     T serve(V request);
}
