package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static hexlet.code.Differ.getDataFormat;
import static hexlet.code.Parser.parse;

public class GetDiff {
    public static Map<String, KeyStatus> getDiff(String filePath1, String filePath2) throws Exception {
        String content1 = Files.readString(Path.of(filePath1));
        String content2 = Files.readString(Path.of(filePath2));
        var mappedContent1 = parse(content1, getDataFormat(filePath1));
        var mappedContent2 = parse(content2, getDataFormat(filePath2));
        var sortedMap = new TreeMap<>(mappedContent1);
        sortedMap.putAll(mappedContent2);
        var resultDiff = new TreeMap<String, KeyStatus>();
        for (var element: sortedMap.entrySet()) {
            var key = element.getKey();
            var currentValue = element.getValue();
            if (!mappedContent1.containsKey(key)) {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("added")
                        .currentValue(currentValue)
                        .build();
                resultDiff.put(key, keyStatus);
            } else if (!mappedContent2.containsKey(key)) {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("removed")
                        .pastValue(mappedContent1.get(key))
                        .build();
                resultDiff.put(key, keyStatus);
            } else if (Objects.equals(mappedContent1.get(key), mappedContent2.get(key))) {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("unchanged")
                        .pastValue(currentValue)
                        .currentValue(currentValue)
                        .build();
                resultDiff.put(key, keyStatus);
            } else if (!Objects.equals(mappedContent1.get(key), mappedContent2.get(key))) {
                var keyStatus = new KeyStatus.KeyStatusBuilder()
                        .keyName(key)
                        .statusOfKey("updated")
                        .pastValue(mappedContent1.get(key))
                        .currentValue(currentValue)
                        .build();
                resultDiff.put(key, keyStatus);
            }
        }
        return resultDiff;
    }
}
