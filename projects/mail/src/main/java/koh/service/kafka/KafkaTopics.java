package koh.service.kafka;

public enum KafkaTopics {
    EVENT_CONFIRMATION_EMAIL, EVENT_TOKEN_ACCESS;

    @Override public String toString() {
        return this.name().toLowerCase().replace('_', '-');
    }
}
