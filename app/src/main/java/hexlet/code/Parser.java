package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filePath) throws  Exception {
        String content = Files.readString(filePath);
        if (filePath.toFile().toString().endsWith(".yml")) {
            return parseYaml(content);
        } else if (filePath.toFile().toString().endsWith(".json")) {
            return parseJson(content);
        } else {
            throw new RuntimeException("Illegal file format");
        }
    }

    public static Map<String, Object> parseJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }
    public static Map<String, Object> parseYaml(String yml) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yml, Map.class);
    }
}
