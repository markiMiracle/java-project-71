package hexlet.code;


import static hexlet.code.Formatter.getFormat;


public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        var resultDiff = GetDiff.getDiff(filePath1, filePath2);
        return getFormat(resultDiff, format);
    }
}
