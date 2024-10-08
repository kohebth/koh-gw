package koh.service.gateway.kafka;

public enum KafkaReqTopic {
    TOPIC_AUTH_GUEST_REQUEST,
    TOPIC_AUTH_LOGIN_REQUEST,
    TOPIC_AUTH_REFRESH_REQUEST,
    TOPIC_AUTH_REGISTER_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_VOLUME_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_IMAGE_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_NETWORK_REQUEST,
    TOPIC_VPS_PROVIDER_CREATE_CONTAINER_REQUEST,
    TOPIC_VPS_PROVIDER_START_CONTAINER_REQUEST,
    TOPIC_VPS_PROVIDER_STOP_CONTAINER_REQUEST
}
