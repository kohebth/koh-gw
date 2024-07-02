package koh.register;

import koh.server.base.SerializableRequest;
import koh.server.base.SerializableResponse;
import koh.server.base.Service;
import koh.service.loopback.LoopBackService;

public enum ServiceRegister {
    LOOPBACK_SERVICE(LoopBackService.class);

    final Class<? extends Service<? extends SerializableRequest, ? extends SerializableResponse>> serviceClass;

    ServiceRegister(Class<? extends Service<? extends SerializableRequest, ? extends SerializableResponse>> serviceClass) {
        this.serviceClass = serviceClass;
    }
}
