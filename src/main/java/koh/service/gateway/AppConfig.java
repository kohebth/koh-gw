package koh.service.gateway;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class AppConfig {
    public static final String MARIADB_USER = System.getenv("MARIADB_USER");
    public static final String MARIADB_PASSWORD = System.getenv("MARIADB_PASSWORD");
    public static final String MARIADB_HOST = System.getenv("MARIADB_HOST");
    public static final String MARIADB_DATABASE = System.getenv("MARIADB_DATABASE");
    public static final String MARIADB_PORT = System.getenv("MARIADB_PORT");

    public static final String KAFKA_NODE = System.getenv("KAFKA_HOST") + ':' + System.getenv("KAFKA_PORT");
    public static final String KAFKA_GROUP = System.getenv("KAFKA_GROUP");

    public static final Class<? extends Deserializer<?>> KAFKA_KEY_DESERIALIZER = StringDeserializer.class;
    public static final Class<? extends Deserializer<?>> KAFKA_VALUE_DESERIALIZER = StringDeserializer.class;
    public static final Class<? extends Serializer<?>>
            KAFKA_KEY_SERIALIZER =
            org.apache.kafka.common.serialization.StringSerializer.class;
    public static final Class<? extends Serializer<?>>
            KAFKA_VALUE_SERIALIZER =
            org.apache.kafka.common.serialization.StringSerializer.class;

    public static final String APP_PRIVATE_KEY_PATH = System.getenv("APP_PRIVATE_KEY_PATH");
    public static final String APP_PUBLIC_KEY_PATH = System.getenv("APP_PUBLIC_KEY_PATH");
    public static final Boolean APP_DEBUG_MODE = Boolean.parseBoolean(System.getenv("APP_DEBUG_MODE"));

    public static final String HTTPS_KEYSTORE_PATH = System.getenv("HTTPS_KEYSTORE_PATH");
    public static final String HTTPS_KEYSTORE_PASSWORD = System.getenv("HTTPS_KEYSTORE_PASSWORD");
    public static final String HTTPS_KEYSTORE_TYPE = System.getenv("HTTPS_KEYSTORE_TYPE");

}
