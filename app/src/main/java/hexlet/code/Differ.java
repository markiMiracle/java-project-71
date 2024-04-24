package hexlet.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.parse;


public class Differ {
    public static void generate(Path filePath1, Path filePath2) throws Exception {
        System.out.println(getDiff(filePath1, filePath2));
    }
    public static String getDiff(Path filePath1, Path filePath2) throws Exception {
        var mappedContent1 = parse(filePath1);
        var mappedContent2 = parse(filePath2);
        var resultMap = getResultMap(mappedContent1, mappedContent2);
        StringBuilder result = new StringBuilder("{\n");
        for (var element: resultMap.entrySet()) {
            result.append("  ").append(element.getValue()).append(element.getKey()).append("\n");
        }
        result.append("}");
        return String.valueOf(result);
    }

    public static TreeMap<String, String> getResultMap(Map<String, Object> map1, Map<String, Object> map2) {
        var resultMap = new HashMap<String, String>();
        for (var element: map1.entrySet()) {
            var key = element.getKey();
            var value = element.getValue();
            if (map2.containsKey(key)) {
                if (map2.get(key).equals(value)) {
                    resultMap.put(key + ": " + value, "  ");
                } else {
                    resultMap.put(key + ": " + value, "- ");
                    resultMap.put(key + ": " + map2.get(key), "+ ");
                }
            } else {
                resultMap.put(key + ": " + value, "- ");
            }
        }
        for (var element2: map2.entrySet()) {
            var key2 = element2.getKey();
            var value2 = element2.getValue();
            if (!map1.containsKey(key2)) {
                resultMap.put(key2 + ": " + value2, "+ ");
            }
        }
        return new TreeMap<>(resultMap);
    }
}
