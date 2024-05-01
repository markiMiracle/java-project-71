package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @Test
    void parseTest() throws Exception {
        Path path = Paths.get("src/test/resources/differTest/file3.json");
        var actualMap1 = parse(path);
        final int value2 = 50;
        var expectMap1 = Map.of("host", "hexlet.io", "timeout", value2, "proxy", "123.234.53.22", "follow", false);
        assertEquals(actualMap1, expectMap1);
    }

    @Test
    void stylishTest() throws Exception {
        String path1 = "src/test/resources/differTest/file1.json";
        String path2 = "src/test/resources/differTest/file2.json";
        String path3 = "src/test/resources/differTest/file1.yml";
        String path4 = "src/test/resources/differTest/file2.yml";
        var actual1 = Differ.generate(path1, path2, "stylish");
        var actual2 = Differ.generate(path3, path4, "stylish");
        assertEquals(resultStylish, actual1);
        assertEquals(resultStylish, actual2);
    }

    @Test
    void plainTest() throws Exception {
        String path1 = "src/test/resources/differTest/file1.json";
        String path2 = "src/test/resources/differTest/file2.json";
        String path3 = "src/test/resources/differTest/file1.yml";
        String path4 = "src/test/resources/differTest/file2.yml";
        var actual1 = Differ.generate(path1, path2, "plain");
        var actual2 = Differ.generate(path3, path4, "plain");
        assertEquals(resultPlain, actual1);
        assertEquals(resultPlain, actual2);
    }

    @Test
    void diffTest() throws Exception {
        String path1 = "src/test/resources/differTest/file3.json";
        String path2 = "src/test/resources/differTest/file4.json";
        String path3 = "src/test/resources/differTest/file3.yml";
        String path4 = "src/test/resources/differTest/file4.yml";
        var actual1 = GetDiff.getDiff(path1, path2);
        var actual2 = GetDiff.getDiff(path3, path4);
        var expected = new TreeMap<>();
        final int currentValue = 20;
        final int pastValue = 50;
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
                .currentValue(currentValue)
                .pastValue(pastValue)
                .build());
        expected.put("verbose", new KeyStatus.KeyStatusBuilder()
                        .keyName("verbose")
                .statusOfKey("added")
                .currentValue(true)
                .pastValue(null)
                .build());
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void jsonTest() throws Exception {
        String path1 = "src/test/resources/differTest/file1.json";
        String path2 = "src/test/resources/differTest/file2.json";
        String path3 = "src/test/resources/differTest/file1.yml";
        String path4 = "src/test/resources/differTest/file2.yml";
        var actual1 = Differ.generate(path1, path2, "json");
        var actual2 = Differ.generate(path3, path4, "json");
        assertEquals(resultJson, actual1);
        assertEquals(resultJson, actual2);
    }

    @Test
    void defaultTest() throws Exception {
        String path1 = "src/test/resources/differTest/file1.json";
        String path2 = "src/test/resources/differTest/file2.json";
        String path3 = "src/test/resources/differTest/file1.yml";
        String path4 = "src/test/resources/differTest/file2.yml";
        var actual1 = Differ.generate(path1, path2);
        var actual2 = Differ.generate(path3, path4);
        assertEquals(resultStylish, actual1);
        assertEquals(resultStylish, actual2);
    }
}
