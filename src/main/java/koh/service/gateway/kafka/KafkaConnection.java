package koh.service.gateway.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

public class KafkaConnection {
    protected final KafkaProducer<String, String> producer;
    protected final KafkaConsumer<String, String> consumer;

    public KafkaConnection(String bootstrapServers, String groupId) {
        this.producer = initKafkaProducer(bootstrapServers);
        this.consumer = initKafkaConsumer(bootstrapServers, groupId);
    }

    KafkaProducer<String, String> initKafkaProducer(String bootstrapServers) {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new KafkaProducer<>(producerProps);
    }

    KafkaConsumer<String, String> initKafkaConsumer(String bootstrapServers, String group) {
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> c = new KafkaConsumer<>(consumerProps);
        c.subscribe(Arrays.stream(KafkaTopic.values()).map(KafkaTopic::name).collect(Collectors.toList()));
        return c;
    }
}
