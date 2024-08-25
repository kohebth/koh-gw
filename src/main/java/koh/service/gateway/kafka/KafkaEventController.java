package koh.service.gateway.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static koh.service.gateway.kafka.KafkaJson.toJson;

public class KafkaEventController extends KafkaConnection {
    private final ConcurrentMap<String, CompletableFuture<String>> futuresMap;

    public KafkaEventController(String bootstrapServers, String group) {
        super(bootstrapServers, group);
        this.futuresMap = new ConcurrentHashMap<>();

        Executors.newSingleThreadExecutor().submit(this::startListening);
    }

    public String requestEvent(KafkaTopic topic, Object value)
            throws IOException {
        CompletableFuture<String> future = new CompletableFuture<>();
        ProducerRecord<String, String> message = new ProducerRecord<>(
                topic.name(),
                generateRequestId(),
                toJson(value)
        );
        futuresMap.put(message.key(), future);
        this.producer.send(message);
        return message.key();
    }

    public String responseEvent(String key, int timeoutInSeconds)
            throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = futuresMap.get(key);
        return future == null ? "Unknown request" : future.get(timeoutInSeconds, TimeUnit.SECONDS);
    }

    void startListening() {
        while (true) {
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(100));
            records.forEach(r -> {
                CompletableFuture<String> future = futuresMap.remove(r.key());
                if (future != null) {
                    future.complete(r.value());
                }
            });
        }
    }

    public String generateRequestId() {
        return Long.toString(System.currentTimeMillis());
    }
}
