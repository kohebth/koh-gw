package koh.service.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.security.scram.ScramLoginModule;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class KafkaConfig {
    static Properties getKafkaConfig(
            String clientId,
            String brokerAddress,
            String userName,
            String password,
            String jksPassword,
            String keyStoreFile,
            String trustStoreFile
    ) {
        String jaasCfg = String.format(
                "%s required username=\"%s\" password=\"%s\";",
                ScramLoginModule.class.getName(),
                userName,
                password
        );
        Properties properties = new Properties();
        // Configure Producer
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        properties.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 60_000);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 8 * 1024 * 1024);

        // Configure Consumer
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "email");

        // Configure SSL
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        properties.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);
        properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreFile);
        properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, jksPassword);
        properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreFile);
        properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, jksPassword);
        properties.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");

        return properties;
    }
}
