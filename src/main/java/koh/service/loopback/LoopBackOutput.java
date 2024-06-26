package koh.service.loopback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import koh.core.base.SerializableResponse;

public class LoopBackOutput implements SerializableResponse {
    @JsonProperty("name")
    String name;
    @JsonProperty("status")
    String status;
    @Override
    public String toJson() {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(this);
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }
}
