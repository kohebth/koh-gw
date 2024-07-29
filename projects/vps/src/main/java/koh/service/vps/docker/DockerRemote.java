package koh.service.vps.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.Getter;

import java.io.IOException;

@Getter
public class DockerRemote {
    final DockerClient dockerClient;

    public DockerRemote() {
        DockerClientConfig config = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock")
                .build();

        dockerClient = DockerClientBuilder.getInstance(config).build();
    }

    static DockerHttpClient dummyDockerHttpClient() {
        return new DockerHttpClient() {
            @Override
            public void close()
                    throws IOException {
            }

            @Override
            public Response execute(Request request) {
                return null;
            }
        };
    }
}
