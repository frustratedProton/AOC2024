package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Day1 {
    public static List<List<Integer>> extractLists(Path filepath) throws IOException {
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

        return List.of(left, right);
    }

    // Part 1
    public static int totalDist(Path filepath) throws IOException {
        List<List<Integer>> lists = extractLists(filepath);

        List<Integer> left = lists.get(0);
        List<Integer> right = lists.get(1);

        return IntStream.range(0, left.size())
                .map(i -> Math.abs(left.get(i) - right.get(i)))
                .sum();
    }

    // Part 2
    public static int totalSimilarity(Path filepath) throws IOException {
        List<List<Integer>> lists = extractLists(filepath);

        List<Integer> left = lists.get(0);
        List<Integer> right = lists.get(1);

        Map<Integer, Long> rightCount = right.stream()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));

        return left.stream()
                .mapToInt(num -> num * rightCount.getOrDefault(num, 0L).intValue())
                .sum();
    }

    public static void main(String[] args) throws IOException {
        Path filepath = Path.of("src/Day1/day1.txt");

        int totalDist = totalDist(filepath);
        System.out.println("Total Distance: " + totalDist);

        int similarity = totalSimilarity(filepath);
        System.out.println("Similarity: " + similarity);
    }
}
