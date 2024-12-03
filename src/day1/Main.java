package day1;

import until.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.getLines(1);
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        for (String s : input) {
            left.add(s.split(" {3}")[0]);
            right.add(s.split(" {3}")[1]);
        }

        left.sort(Comparator.comparing(Integer::valueOf));
        right.sort(Comparator.comparing(Integer::valueOf));

        System.out.println(partOne(left, right));
        System.out.println(partTwo(left, right));
    }

    public static int partOne(List<String> left, List<String> right) {
        int result = 0;

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs((Integer.parseInt(left.get(i)) - Integer.parseInt(right.get(i))));
        }

        return result;
    }

    public static int partTwo(List<String> left, List<String> right) {
        int result = 0;

        for (String sl : left) {
            int occurances = (int) right.stream().filter(sr -> sr.equals(sl)).count();
            result += (Integer.parseInt(sl) * occurances);
        }
        return result;
    }
}
