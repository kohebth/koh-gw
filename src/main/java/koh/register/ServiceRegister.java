package koh.register;

import koh.core.base.SerializableRequest;
import koh.core.base.SerializableResponse;
import koh.core.base.Service;
import koh.service.loopback.LoopBackService;

public enum ServiceRegister {
    LOOPBACK_SERVICE(LoopBackService.class);

    final Class<? extends Service<? extends SerializableRequest, ? extends SerializableResponse>> serviceClass;

    ServiceRegister(Class<? extends Service<? extends SerializableRequest, ? extends SerializableResponse>> serviceClass) {
        this.serviceClass = serviceClass;
    }
}
