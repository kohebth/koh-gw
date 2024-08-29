package koh.service.gateway.https.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koh.service.gateway.kafka.KafkaEventController;
import koh.service.gateway.kafka.KafkaJson;
import koh.service.gateway.kafka.KafkaReqTopic;
import koh.service.gateway.kafka.message.*;

import java.io.IOException;

public class VpsServlet extends AbstractGatewayServlet {

    public VpsServlet(KafkaEventController kafkaEventController) {
        super(kafkaEventController);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        NewVpsMessage vpsMessage = KafkaJson.fromJson(req.getReader(), NewVpsMessage.class);

        long userId = vpsMessage.getUserId();
        String nameOnly = vpsMessage.getEmail().replaceAll(".|@.*", "") + "_vps";
        int volumeSize = vpsMessage.getVolumeSizeInMb();
        String network = vpsMessage.getNetworkId();

        NewVolumeMessage volumeMessage = new NewVolumeMessage();
        volumeMessage.setHost("");
        volumeMessage.setHost("/home");
        volumeMessage.setSizeInMb(volumeSize);
        kafkaEventController.requestEvent(KafkaReqTopic.TOPIC_VPS_PROVIDER_CREATE_VOLUME_REQUEST, volumeMessage);

        NewNetworkMessage networkMessage = new NewNetworkMessage();
        networkMessage.setName(nameOnly);
        networkMessage.setGateway("");
        networkMessage.setSubnet("");
        networkMessage.setIpRange("");
        kafkaEventController.requestEvent(KafkaReqTopic.TOPIC_VPS_PROVIDER_CREATE_NETWORK_REQUEST, new NewVolumeMessage());

        NewImageMessage imageMessage = new NewImageMessage();
        imageMessage.setName("ubuntu");
        imageMessage.setRepo("koh");
        imageMessage.setVersion("22.04");
        kafkaEventController.requestEvent(KafkaReqTopic.TOPIC_VPS_PROVIDER_CREATE_IMAGE_REQUEST, new NewVolumeMessage());

        NewContainerMessage containerMessage = new NewContainerMessage();
        containerMessage.setName(nameOnly);
        containerMessage.setUserId(userId);
        imageMessage.setVersion("22.04");
        kafkaEventController.requestEvent(KafkaReqTopic.TOPIC_VPS_PROVIDER_CREATE_CONTAINER_REQUEST, new NewVolumeMessage());
    }
}
