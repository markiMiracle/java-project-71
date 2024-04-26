package hexlet.code;

import java.nio.file.Path;
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
        return getResult(mappedContent1, mappedContent2);
    }

    public static String getResult(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");
        var sortedMap = new TreeMap<>(map1);
        sortedMap.putAll(map2);
        for (var element: sortedMap.entrySet()) {
            var key = element.getKey();
            var value = "" + element.getValue();
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if ((map1.get(key) + "").equals(value)) {
                    result.append("    " + key + ": " + value + "\n");
                } else {
                    result.append("  - " + key + ": " + map1.get(key) + "\n");
                    result.append("  + " + key + ": " + value + "\n");
                }
            } else if (map2.containsKey(key)) {
                result.append("  + " + key + ": " + value + "\n");
            } else {
                result.append("  - " + key + ": " + value + "\n");
            }
        }
        result.append("}");
        return String.valueOf(result);
    }
}
