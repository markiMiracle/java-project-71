package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyStatus;

import java.util.ArrayList;
import java.util.Map;

public class Json {
    public static String json(Map<String, KeyStatus> resultDiff) throws JsonProcessingException {
        var listOfKeys = new ArrayList<Object>();
        for (var value: resultDiff.entrySet()) {
            listOfKeys.add(value.getValue());
        }
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(listOfKeys));
        return mapper.writeValueAsString(listOfKeys);
    }
}
