package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Differ.getDataFormat;
import static hexlet.code.Parser.parse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTests {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static String resultParse;

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
        resultParse = readFixture("result_parse.json");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void parseTest(String format) throws Exception {
        String path1 = getFixturePath("file3." + format).toString();
        var actual1 =  Files.readString(Path.of(path1));
        var mapperJson = new ObjectMapper();
        assertThat(parse(actual1, getDataFormat(path1))).isEqualTo(mapperJson.readValue(resultParse, Map.class));
    }

    @Test
    void diffTest() throws Exception {
        String path1 = getFixturePath("file3.json").toString();
        String path2 = getFixturePath("file4.json").toString();
        String path3 = getFixturePath("file3.yml").toString();
        String path4 = getFixturePath("file4.yml").toString();
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
    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(resultJson);
        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(resultStylish);
    }
}
