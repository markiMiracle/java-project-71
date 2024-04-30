package hexlet.code;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Formatter.getFormat;
import static hexlet.code.Parser.parse;



public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        var resultDiff = getDiff(filePath1, filePath2);
        return getFormat(resultDiff);
    }
    public static Map<String, KeyStatus> getDiff(String filePath1, String filePath2) throws Exception {
        Path path1 = Path.of(filePath1);
        Path path2 = Path.of(filePath2);
        var mappedContent1 = parse(path1);
        var mappedContent2 = parse(path2);
        var sortedMap = new TreeMap<>(mappedContent1);
        sortedMap.putAll(mappedContent2);
        var resultDiff = new TreeMap<String, KeyStatus>();
        for (var element: sortedMap.entrySet()) {
            var key = element.getKey();
            var currentValue = element.getValue();
            if (mappedContent1.containsKey(key) && mappedContent2.containsKey(key)) {
                if (mappedContent1.get(key) == null && currentValue != null) {
                    var keyStatus = new KeyStatus.KeyStatusBuilder()
                            .keyName(key)
                            .statusOfKey("updated")
                            .pastValue(null)
                            .currentValue(currentValue)
                            .build();
                    resultDiff.put(key, keyStatus);
                } else if (mappedContent1.get(key) != null && currentValue == null) {
                    var keyStatus = new KeyStatus.KeyStatusBuilder()
                            .keyName(key)
                            .statusOfKey("updated")
                            .pastValue(mappedContent1.get(key))
                            .currentValue(null)
                            .build();
                    resultDiff.put(key, keyStatus);
                } else if (mappedContent1.get(key).equals(currentValue)) {
                    var keyStatus = new KeyStatus.KeyStatusBuilder()
                            .keyName(key)
                            .statusOfKey("unchanged")
                            .pastValue(currentValue)
                            .currentValue(currentValue)
                            .build();
                    resultDiff.put(key, keyStatus);
                } else {
                    var keyStatus = new KeyStatus.KeyStatusBuilder()
                            .keyName(key)
                            .statusOfKey("updated")
                            .pastValue(mappedContent1.get(key))
                            .currentValue(currentValue)
                            .build();
                    resultDiff.put(key, keyStatus);
                }
            } else if (mappedContent2.containsKey(key)) {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("added")
                        .currentValue(currentValue)
                        .build();
                resultDiff.put(key, keyStatus);
            } else {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("removed")
                        .pastValue(mappedContent1.get(key))
                        .build();
                resultDiff.put(key, keyStatus);
            }
        }
        return resultDiff;
    }
}
