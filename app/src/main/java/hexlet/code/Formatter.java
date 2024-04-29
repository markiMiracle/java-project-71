package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

import static hexlet.code.App.format;
import static hexlet.code.formatter.Json.json;
import static hexlet.code.formatter.Plain.plain;
import static hexlet.code.formatter.Stylish.stylish;

public class Formatter {
    public static String getFormat(Map<String, KeyStatus> resultDiff) throws JsonProcessingException {
        if (format.equals("stylish")) {
            return stylish(resultDiff);
        } else if (format.equals("plain")) {
            return plain(resultDiff);
        } else if (format.equals("json")) {
            return json(resultDiff);
        } else {
            throw new RuntimeException("No valid argument. Use 'stylish' or 'plain'.");
        }
    }
}
