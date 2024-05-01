package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

import static hexlet.code.formatter.Json.json;
import static hexlet.code.formatter.Plain.plain;
import static hexlet.code.formatter.Stylish.stylish;

public class Formatter {
    public static String getFormat(Map<String, KeyStatus> resultDiff, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> stylish(resultDiff);
            case "plain" -> plain(resultDiff);
            case "json" -> json(resultDiff);
            default -> throw new RuntimeException("'" + format + "'" + " is no valid argument."
                   + " Use 'stylish', 'plain' or 'json'.");
        };
    }
}
