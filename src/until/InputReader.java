package until;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {

    public static  List<String> getLines(int day) throws IOException {
        return Files.readAllLines(Paths.get(String.format("src/day%s/input.txt", day)));
    }
}
