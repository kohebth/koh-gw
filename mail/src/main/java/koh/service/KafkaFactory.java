package koh.service;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.LongSerializer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Author: (づ｡◕‿‿◕｡)づ*
 * Creation date: 2019-03-19
 */
public class KafkaFactory {

    public static Producer<Long, byte[]> createSpendingReportProducer(String brokerAddrs, String userName,
            String password, String jkspassword,
            String keyStoreFile, String trustStoreFile) {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, userName, password);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddrs);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "spending-report-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        props.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 60_000);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 8 * 1024 * 1024);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreFile);
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, jkspassword);
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreFile);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, jkspassword);
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
        return new KafkaProducer<>(props);
    }

    public static final class Topics {

        // Clickstream with requests' transition code != 255
        static final String SPENDING_REPORT = "events-internal-services-report";

    }

}
