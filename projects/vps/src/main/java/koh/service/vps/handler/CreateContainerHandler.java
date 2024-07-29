package koh.service.vps.handler;

import koh.core.base.AbstractDto;
import koh.core.base.SimpleHandler;
import koh.service.vps.docker.DockerRemote;

public class CreateContainerHandler implements SimpleHandler {
    DockerRemote dockerRemote = new DockerRemote();

    @Override
    public <T extends AbstractDto> Object handle(T wrappedObject) {
//        CreateContainerDto dto = (CreateContainerDto) wrappedObject;
//        try (CreateContainerCmd cmd = dockerRemote.getDockerClient().createContainerCmd()) {
//            return cmd.exec();
//        }

    }

    static class CreateContainerDto extends AbstractDto {
        String vpsName;
    }
}
