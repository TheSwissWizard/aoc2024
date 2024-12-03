package day2;

import until.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.getLines(2);

        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    public static int partOne(List<String> reports) {
        int safe = 0;

        for (String report : reports) {
            List<Integer> levels = Stream.of(report.split(" ")).map(Integer::parseInt).toList();
            if (check(levels)) {
                safe++;
            }
        }

        return safe;
    }

    public static int partTwo(List<String> reports) {

        int safe = 0;

        for (String report : reports) {
            List<Integer> levels = Stream.of(report.split(" ")).map(Integer::parseInt).toList();

            if (check(levels)) {
                safe++;
            } else {
                for (int i = 0; i < levels.size(); i++){
                    List<Integer> correctedList = new ArrayList<>(levels);
                    correctedList.remove(i);
                    if (check(correctedList)){
                        safe++;
                        break;
                    }
                }
            }
        }

        return safe;
    }

    public static boolean check(List<Integer> levels) {
        boolean asc = levels.get(0) < levels.get(1);

        for (int i = 0; i < levels.size() - 1; i++) {
            int diff = Math.abs(levels.get(i) - levels.get(i + 1));

            if (diff > 3 || diff < 1) {
                return false;
            } else if (asc && levels.get(i) > levels.get(i + 1)) {
                return false;
            } else if (!asc && levels.get(i) < levels.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
