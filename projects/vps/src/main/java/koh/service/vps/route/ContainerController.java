package koh.service.vps.route;

import com.github.dockerjava.api.model.Container;
import koh.core.base.SimpleController;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;
import koh.service.vps.handler.ListContainerHandler;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ContainerController implements SimpleController {

    @Override
    public SimpleResponse handle(SimpleRequest request)
            throws Exception {
        ContainerRequest req = (ContainerRequest) request;
        ContainerResponse.ContainerResponseBuilder responseBuilder = ContainerResponse.builder();
        switch (req.command) {
            case LIST_CONTAINER:
                responseBuilder.containerList(new ListContainerHandler().handle());
            case KILL_CONTAINER:
            case UP_CONTAINER:
            case DOWN_CONTAINER:
        }
        return responseBuilder.build();
    }

    @Override
    public String getPath() {
        return "/vps/container";
    }

    @Override
    public Class<? extends SimpleRequest> getRequestClass() {
        return ContainerRequest.class;
    }

    @Override
    public Class<? extends SimpleResponse> getResponseClass() {
        return ContainerResponse.class;
    }

    @Data
    public static class ContainerRequest implements SimpleRequest {
        ContainerCommand command;
    }

    @Data
    @Builder
    public static class ContainerResponse implements SimpleResponse {
        List<Container> containerList;
    }

    public enum ContainerCommand {
        LIST_CONTAINER,
        KILL_CONTAINER,
        UP_CONTAINER,
        DOWN_CONTAINER
    }
}
