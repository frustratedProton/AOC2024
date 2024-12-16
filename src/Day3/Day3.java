package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Path filepath = Path.of("src/Day3/Day3.txt");

        int count = resMul(filepath);
        System.out.println("Total sum of multiplications: " + count);
    }

    public static int resMul(Path path) throws IOException {
        String puzzleInp = Files.readString(path);

        // raw form is `mul\(\d+\,\d+\)`
        String regex = "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(puzzleInp)
                .results()
                .map(n -> {
                    int x = Integer.parseInt(n.group(1));
                    int y = Integer.parseInt(n.group(2));
                    return x * y;
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}
