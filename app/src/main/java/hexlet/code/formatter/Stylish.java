package hexlet.code.formatter;

import hexlet.code.KeyStatus;

import java.util.Map;

public class Stylish {
    public static String stylish(Map<String, KeyStatus> resultDiff) {
        var formatted = new StringBuilder("{\n");
        for (var data: resultDiff.entrySet()) {
            var keyName = data.getKey();
            String status = data.getValue().getStatusOfKey();
            var pastValue = data.getValue().getPastValue();
            var currentValue = data.getValue().getCurrentValue();
            if (status.equals("unchanged")) {
                formatted.append("    ").append(keyName).append(": ").append(currentValue).append("\n");
            } else if (status.equals("updated")) {
                formatted.append("  - ").append(keyName).append(": ").append(pastValue).append("\n");
                formatted.append("  + ").append(keyName).append(": ").append(currentValue).append("\n");
            } else if (status.equals("removed")) {
                formatted.append("  - ").append(keyName).append(": ").append(pastValue).append("\n");
            } else if (status.equals("added")) {
                formatted.append("  + ").append(keyName).append(": ").append(currentValue).append("\n");
            }
        }
        formatted.append("}");
        return String.valueOf(formatted);
    }
}
