package koh.core.helper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import koh.core.base.SimpleRequest;
import koh.core.base.SimpleResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class JsonTools {
    private static final ObjectReader OBJECT_READER;
    private static final ObjectWriter OBJECT_WRITER;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.setConstructorDetector(ConstructorDetector.USE_DELEGATING);

        OBJECT_READER = objectMapper.reader();
        OBJECT_WRITER = objectMapper.writer();
    }

    public static SimpleRequest deserialize(String jsonString, Class<? extends SimpleRequest> cls)
            throws IOException {
        return OBJECT_READER.readValue(jsonString, cls);
    }

    public static SimpleRequest deserialize(BufferedReader inputStream, Class<? extends SimpleRequest> cls)
            throws IOException {
        return OBJECT_READER.readValue(inputStream, cls);
    }

    public static <T> void serialize(BufferedWriter outputStream, T object)
            throws IOException {
        OBJECT_WRITER.writeValue(outputStream, object);
    }

    public static <T extends SimpleResponse> String serialize(T object)
            throws IOException {
        return OBJECT_WRITER.writeValueAsString(object);
    }
}
