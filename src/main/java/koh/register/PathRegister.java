package koh.register;

import koh.controller.GoodbyeController;
import koh.controller.HelloController;
import koh.controller.LoopBackController;
import koh.api.core.base.BaseController;
import koh.api.core.base.Register;
import lombok.Getter;

@Getter
public enum PathRegister implements Register<BaseController> {
    HELLO("/hello", HelloController.class),
    GOODBYE("/goodbye", GoodbyeController.class),
    LOOPBACK("/loopback", LoopBackController.class);

    final String path;
    final Class<? extends BaseController> handlerClass;

    PathRegister(String path, Class<? extends BaseController> handlerClass) {
        this.path = path;
        this.handlerClass = handlerClass;
    }
}
