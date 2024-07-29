package koh.service.vps.docker;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Builder(builderClassName = "Builder")
public class DockerCompose {
    private String version;
    private Map<String, Service> services;
    private Map<String, Network> networks;

    @Data
    public static class Service {
        private Build build;
        private String image;
        private String envFile;
        private String hostname;
        private String domainname;
        private List<String> ports;
        private List<String> volumes;
        private List<String> capAdd;
        private String restart;
        private List<String> networks;
        private List<String> expose;
    }

    @Data
    public static class Build {
        private String dockerfile;
        private String context;
    }

    @Data
    public static class Network {
        private boolean external;
        private String name;
    }
}

