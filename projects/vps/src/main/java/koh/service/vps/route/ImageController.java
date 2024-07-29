package koh.service.vps.route;

import koh.core.base.SimpleController;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;
import koh.service.vps.handler.ImageListHandler;
import lombok.Builder;
import lombok.Data;

public class ImageController implements SimpleController {

    @Override
    public SimpleResponse handle(SimpleRequest request)
            throws Exception {
        ImageRequest req = (ImageRequest) request;
        ImageResponse.ImageResponseBuilder responseBuilder = ImageResponse.builder();
        switch (req.command) {
            case LIST_IMAGE:
                responseBuilder.imageList(new ImageListHandler().handle(new ImageListHandler.ImageListHandlerPayload()));
            case REMOVE_IMAGE:
            case CREATE_IMAGE:
        }
        return responseBuilder.build();
    }

    @Override
    public String getPath() {
        return "/vps/container";
    }

    @Override
    public Class<? extends SimpleRequest> getRequestClass() {
        return ContainerController.ContainerRequest.class;
    }

    @Override
    public Class<? extends SimpleResponse> getResponseClass() {
        return ContainerController.ContainerResponse.class;
    }

    @Data
    public static class ImageRequest implements SimpleRequest {
        ImageCommand command;
    }

    @Data
    @Builder
    public static class ImageResponse implements SimpleResponse {
        Object imageList;
    }

    public enum ImageCommand {
        LIST_IMAGE,
        REMOVE_IMAGE,
        CREATE_IMAGE
    }
}
