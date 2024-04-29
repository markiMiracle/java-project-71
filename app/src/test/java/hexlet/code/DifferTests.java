package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.App.format;
import static hexlet.code.Differ.getDiff;
import static hexlet.code.Formatter.getFormat;
import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {

    @Test
    void parseTest() throws Exception {
        Path path = Paths.get("src/test/resources/differTest/file3.json");
        var actualMap1 = parse(path);
        var expectMap1 = Map.of("host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);
        assertEquals(actualMap1, expectMap1);
    }

    @Test
    void stylishTestJson() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.json");
        Path path2 = Paths.get("src/test/resources/differTest/file2.json");
        Path path3 = Paths.get("src/test/resources/differTest/file3.json");
        Path path4 = Paths.get("src/test/resources/differTest/file4.json");
        format = "stylish";
        var actual1 = getFormat(getDiff(path1, path2));
        var actual2 = getFormat(getDiff(path3, path4));
        var expect1 = "{\n"
               + "    chars1: [a, b, c]\n"
               + "  - chars2: [d, e, f]\n"
               + "  + chars2: false\n"
               + "  - checked: false\n"
               + "  + checked: true\n"
               + "  - default: null\n"
               + "  + default: [value1, value2]\n"
               + "  - id: 45\n"
               + "  + id: null\n"
               + "  - key1: value1\n"
               + "  + key2: value2\n"
               + "    numbers1: [1, 2, 3, 4]\n"
               + "  - numbers2: [2, 3, 4, 5]\n"
               + "  + numbers2: [22, 33, 44, 55]\n"
               + "  - numbers3: [3, 4, 5]\n"
               + "  + numbers4: [4, 5, 6]\n"
               + "  + obj1: {nestedKey=value, isNested=true}\n"
               + "  - setting1: Some value\n"
               + "  + setting1: Another value\n"
               + "  - setting2: 200\n"
               + "  + setting2: 300\n"
               + "  - setting3: true\n"
               + "  + setting3: none\n"
               + "}";
        var expect2 = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}";

        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    void stylishTestYaml() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.yml");
        Path path2 = Paths.get("src/test/resources/differTest/file2.yml");
        Path path3 = Paths.get("src/test/resources/differTest/file3.yml");
        Path path4 = Paths.get("src/test/resources/differTest/file4.yml");
        format = "stylish";
        var actual1 = getFormat(getDiff(path1, path2));
        var actual2 = getFormat(getDiff(path3, path4));
        var expect1 = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var expect2 = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}";

        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    void plainTest() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.json");
        Path path2 = Paths.get("src/test/resources/differTest/file2.json");
        format = "plain";
        var actual1 = getFormat(getDiff(path1, path2));
        String expected1 = "Property 'chars2' was updated. From [complex value] to false\n"
               + "Property 'checked' was updated. From false to true\n"
               + "Property 'default' was updated. From null to [complex value]\n"
               + "Property 'id' was updated. From 45 to null\n"
               + "Property 'key1' was removed\n"
               + "Property 'key2' was added with value: 'value2'\n"
               + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
               + "Property 'numbers3' was removed\n"
               + "Property 'numbers4' was added with value: [complex value]\n"
               + "Property 'obj1' was added with value: [complex value]\n"
               + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
               + "Property 'setting2' was updated. From 200 to 300\n"
               + "Property 'setting3' was updated. From true to 'none'";
        assertEquals(expected1, actual1);
    }

    @Test
    void diffTest() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.yml");
        Path path2 = Paths.get("src/test/resources/differTest/file2.yml");
        var actual = getDiff(path1, path2);
        var expected = new TreeMap<>();
        expected.put("follow", new KeyStatus.KeyStatusBuilder()
                        .keyName("follow")
                .statusOfKey("removed")
                .currentValue(null)
                .pastValue(false)
                .build());
        expected.put("host", new KeyStatus.KeyStatusBuilder()
                        .keyName("host")
                .statusOfKey("unchanged")
                .currentValue("hexlet.io")
                .pastValue("hexlet.io")
                .build());
        expected.put("proxy", new KeyStatus.KeyStatusBuilder()
                        .keyName("proxy")
                .statusOfKey("removed")
                .currentValue(null)
                .pastValue("123.234.53.22")
                .build());
        expected.put("timeout", new KeyStatus.KeyStatusBuilder()
                        .keyName("timeout")
                .statusOfKey("updated")
                .currentValue(20)
                .pastValue(50)
                .build());
        expected.put("verbose", new KeyStatus.KeyStatusBuilder()
                        .keyName("verbose")
                .statusOfKey("added")
                .currentValue(true)
                .pastValue(null)
                .build());
        assertEquals(expected, actual);
    }

    @Test
    void jsonTest() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.yml");
        Path path2 = Paths.get("src/test/resources/differTest/file2.yml");
        format = "json";
        var actual1 = getFormat(getDiff(path1, path2));
        var expect1 = "[{\"keyName\":\"follow\",\"statusOfKey\":\"removed\","
               + "\"pastValue\":false,\"currentValue\":null},{\"keyName\":\"host\","
               + "\"statusOfKey\":\"unchanged\",\"pastValue\":\"hexlet.io\","
               + "\"currentValue\":\"hexlet.io\"},{\"keyName\":\"proxy\","
               + "\"statusOfKey\":\"removed\",\"pastValue\":\"123.234.53.22\","
               + "\"currentValue\":null},{\"keyName\":\"timeout\",\"statusOfKey\":\"updated\","
               + "\"pastValue\":50,\"currentValue\":20},{\"keyName\":\"verbose\",\"statusOfKey\":\"added\","
               + "\"pastValue\":null,\"currentValue\":true}]";
        assertEquals(expect1, actual1);
    }
}
