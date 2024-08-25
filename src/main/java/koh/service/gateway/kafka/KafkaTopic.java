package koh.service.gateway.kafka;

import java.util.regex.Pattern;

public enum KafkaTopic {
    TOPIC_AUTH_LOGIN_REQUEST,
    TOPIC_AUTH_REGISTER_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_VOLUME,
    TOPIC_VPS_PROVIDER_CREATE_IMAGE_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_NETWORK_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_CONTAINER_REQUEST,
    TOPIC_VPS_PROVIDER_START_CONTAINER_REQUEST,
    TOPIC_VPS_PROVIDER_STOP_CONTAINER_REQUEST;

    static final Pattern NAME_CONVENTION = Pattern.compile("TOPIC_.*_REQUEST");

    KafkaTopic() {
        requireConvention(this);
    }

    private static void requireConvention(KafkaTopic requestTopic) {
        if (!NAME_CONVENTION.matcher(requestTopic.name()).matches()) {
            throw new IllegalArgumentException("Kafka topic for request is not named as the convention");
        }
    }
}
