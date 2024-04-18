package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {


    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    public Path filePath1;
    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    public Path filePath2;

    @Override
    public Integer call() throws Exception {
        Differ.generate(filePath1, filePath2);
        return 0;
    }
    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}