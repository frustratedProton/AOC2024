package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;


public class Day1 {
    public static int totalDist(Path filepath) throws IOException {
        // reading input file
        List<Integer> left;
        List<Integer> right;

        // basic splitting whitespace to form right/left type stuff
        try (var lines = Files.lines(filepath)) {
            left = lines
                    .map(line -> Integer.parseInt(line.split("\\s+")[0].strip()))
                    .sorted()
                    .toList();
        }

        try (var lines = Files.lines(filepath)) {
            right = lines
                    .map(line -> Integer.parseInt(line.split("\\s+")[1].strip()))
                    .sorted()
                    .toList();
        }

        return IntStream.range(0, left.size())
                .map(i -> Math.abs(left.get(i) - right.get(i)))
                .sum();
    }

    public static void main(String[] args) throws IOException {
        Path filepath = Path.of("src/Day1/day1.txt");

        int totalDist = totalDist(filepath);
        System.out.println(totalDist);
    }
}
