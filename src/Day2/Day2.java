    package Day2;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;
    import java.util.List;

    public class Day2 {
        public static long safeReportCounts(Path filepath) throws IOException {
            List<int[]> reports;

            try (var stream = Files.lines(filepath)) {
                reports = stream
                        .map(line -> line.trim().split("\\s+"))
                        .map(arr -> Arrays.stream(arr).mapToInt(Integer::parseInt).toArray())
                        .toList();
            }

            return reports.stream()
                    .filter(Day2::isSafeDampened)
                    .count();
        }

        public static boolean isSafe(int[] levels) {
            boolean isIncreasing = false;
            boolean isDecreasing = false;

            for (int i = 0; i < levels.length - 1; i++) {
                int diff = levels[i + 1] - levels[i];

                if (Math.abs(diff) > 3) {
                    return false;
                }

                if (diff > 0) {
                    isIncreasing = true;
                } else if (diff < 0) {
                    isDecreasing = true;
                } else {
                    return false;
                }

                if (isIncreasing && isDecreasing) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isSafeDampened(int[] levels) {
            if (isSafe(levels)) {
                return true;
            }

            for (int i = 0; i < levels.length; i++) {
                int[] dampedArray = new int[levels.length - 1];
                System.arraycopy(levels, 0, dampedArray, 0, i);
                System.arraycopy(levels, i + 1, dampedArray, i, levels.length - i - 1);
                if (isSafe(dampedArray)) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) throws IOException {
            Path filepath = Path.of("src/Day2/Day2.txt");

            long reports = safeReportCounts(filepath);
            System.out.println("Safe reports: " + reports);
        }
    }
