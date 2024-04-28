package hexlet.code;


import java.util.Map;

import static hexlet.code.App.format;
import static hexlet.code.formatter.Plain.plain;
import static hexlet.code.formatter.Stylish.stylish;

public class Formatter {
    public static String getFormat(Map<String, KeyStatus> resultDiff) {
        if (format.equals("stylish")) {
            return stylish(resultDiff);
        } else if (format.equals("plain")) {
            return plain(resultDiff);
        } else {
            throw new RuntimeException("No valid argument. Use 'stylish' or 'plain'.");
        }
    }
}
