package hexlet.code.formatter;

import hexlet.code.KeyStatus;

import java.util.Map;

public class Plain {
    public static String plain(Map<String, KeyStatus> resultDiff) {
        var formatted = new StringBuilder();
        int count = resultDiff.size();
        for (var data : resultDiff.entrySet()) {
            var keyName = data.getKey();
            var values = data.getValue();
            String status = values.getStatusOfKey();
            var pastValue = values.isComplexValue(values.getPastValue());
            var currentValue = values.isComplexValue(values.getCurrentValue());
            if (status.equals("updated")) {
                formatted.append("Property " + "'" + keyName + "'" + " was " + status + ". From " + pastValue
                        + " to " + currentValue);
                if (count > 1) {
                    formatted.append("\n");
                }
            } else if (status.equals("removed")) {
                formatted.append("Property " + "'" + keyName + "'" + " was " + status);
                if (count > 1) {
                    formatted.append("\n");
                }
            } else if (status.equals("added")) {
                formatted.append("Property " + "'" + keyName + "'" + " was " + status + " with value: " + currentValue);
                if (count > 1) {
                    formatted.append("\n");
                }
            }
            --count;
        }
        return String.valueOf(formatted);
    }
}
