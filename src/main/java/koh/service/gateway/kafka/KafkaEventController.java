package koh.service.gateway.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static koh.service.gateway.kafka.KafkaJson.toJson;

public class KafkaEventController extends KafkaConnection {
    private final ConcurrentMap<String, CompletableFuture<String>> futuresMap;

    public KafkaEventController(String bootstrapServers, String group) {
        super(bootstrapServers, group);
        this.futuresMap = new ConcurrentHashMap<>();
        this.consumer.subscribe(Arrays.stream(KafkaRespTopic.values())
                .map(KafkaRespTopic::name)
                .collect(Collectors.toList()));
        Executors.newSingleThreadExecutor().submit(this::startListening);
    }

    public String requestEvent(KafkaReqTopic topic, String value) {
        CompletableFuture<String> future = new CompletableFuture<>();
        ProducerRecord<String, String> message = new ProducerRecord<>(
                topic.name(),
                generateRequestId(),
                value
        );
        futuresMap.put(message.key(), future);
        this.producer.send(message);
        return message.key();
    }

    public String requestEvent(KafkaReqTopic topic, Object value)
            throws IOException {
        return requestEvent(topic, toJson(value));
    }

    public String responseEvent(String key, int timeoutInSeconds)
            throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = futuresMap.remove(key);
        return future == null ? "Unknown request" : future.get(timeoutInSeconds, TimeUnit.SECONDS);
    }

    void startListening() {
        while (true) {
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(100));
            records.forEach(r -> {
                CompletableFuture<String> future = futuresMap.get(r.key());
                if (future != null) {
                    future.complete(r.value());
                }
            });
        }
    }

    public String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
