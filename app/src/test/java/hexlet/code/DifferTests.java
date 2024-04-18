package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import static hexlet.code.Differ.getDiff;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static hexlet.code.Differ.parse;

public class DifferTests {

    @Test
    void parseTest() throws Exception {
        Path path = Paths.get("src/test/resources/differTest/file1.json");
        String content1 = Files.readString(path);
        var actualMap1 = parse(content1);
        var expectMap1 = Map.of("host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);
        assertEquals(actualMap1, expectMap1);
    }

    @Test
    void diffTest() throws Exception {
        Path path1 = Paths.get("src/test/resources/differTest/file1.json");
        Path path2 = Paths.get("src/test/resources/differTest/file2.json");
        Path path3 = Paths.get("src/test/resources/differTest/file3.json");
        Path path4 = Paths.get("src/test/resources/differTest/file4.json");
        var actual1 = getDiff(path1, path2);
        var actual2 = getDiff(path3, path4);
        var expect1 = "{\n"
               + "  - follow: false\n"
               + "    host: hexlet.io\n"
               + "  - proxy: 123.234.53.22\n"
               + "  + timeout: 20\n"
               + "  - timeout: 50\n"
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
}
