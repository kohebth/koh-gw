package koh.service.loopback;

import koh.core.Response;

public class LoopBackService {
    LoopBackRequest request;
    LoopBackResponse response;
    public LoopBackService(LoopBackRequest request) {
        this.request = request;
    }

    public LoopBackResponse serve() {
        LoopBackResponse resp = new LoopBackResponse();
        resp.name = request.name;
        resp.status = request.status;
        response = resp;
        return resp;
    }
}
