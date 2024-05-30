package org.PostCommentsAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    public static <T> String StringConverter(T comment) {
        ObjectMapper mapper = new ObjectMapper();
        String stringBody;

        try {
            stringBody = mapper.writeValueAsString(comment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return stringBody;
    }
}
