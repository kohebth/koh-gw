package koh.service.vps.handler;

import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import koh.service.vps.docker.DockerRemote;

import java.util.List;

public class ListContainerHandler {
    public List<Container> handle() {
        DockerRemote dockerRemote = new DockerRemote();
        try (ListContainersCmd cmd = dockerRemote.getDockerClient().listContainersCmd()) {
            return cmd.withShowAll(true).exec();
        }
    }
}
