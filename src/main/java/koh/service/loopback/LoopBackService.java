package koh.service.loopback;

import koh.core.base.Service;

public class LoopBackService implements Service<LoopBackInput, LoopBackOutput> {
    @Override
    public LoopBackOutput serve(LoopBackInput request) {
        LoopBackOutput resp = new LoopBackOutput();
        resp.name = request.name;
        resp.status = request.status;
        return resp;
    }
}
