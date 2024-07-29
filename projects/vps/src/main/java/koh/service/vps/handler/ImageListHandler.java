package koh.service.vps.handler;

import koh.core.base.AbstractDto;
import koh.core.base.SimpleHandler;
import koh.service.vps.docker.DockerRemote;

public class ImageListHandler implements SimpleHandler {
    final DockerRemote dockerRemote = new DockerRemote();

    @Override
    public <T extends AbstractDto> Object handle(T wrappedObject) {
        ImageListHandlerPayload payload = (ImageListHandlerPayload) wrappedObject;
        return dockerRemote.getDockerClient().listImagesCmd().exec();
    }

    public static class ImageListHandlerPayload extends AbstractDto {
        String name;
        String user;
        String permission;
    }
}
