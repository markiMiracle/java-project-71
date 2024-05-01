package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyStatus;
import java.util.Map;

public class Json {
    public static String json(Map<String, KeyStatus> resultDiff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resultDiff);
    }
}
