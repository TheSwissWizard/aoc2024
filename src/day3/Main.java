package day3;

import until.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.getLines(3);

        System.out.println(partOne(String.join("", input)));
        System.out.println(partTwo(String.join("", input)));
    }

    public static int partOne(String inputString) {

        int result = 0;

        Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        List<String> multiplications = getMatches(mulPattern, inputString);

        for (String mult : multiplications) {
            result += multiply(mult);
        }

        return result;
    }

    public static int partTwo(String inputString) {
        int result = 0;
        boolean enabled = true;

        Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        List<String> matches = getMatches(mulPattern, inputString);

        for (String match : matches) {
            switch (match) {
            case String s when s.contains("don't()") -> enabled = false;
            case String s when s.contains("do()") -> enabled = true;
            default -> {
                if (enabled) {
                    result += multiply(match);
                }
            }
            }
        }

        return result;
    }

    public static int multiply(String mult) {
        Pattern numPattern = Pattern.compile("(\\d+,\\d+)");
        List<Integer> multValues = getMatches(numPattern, mult)
                .stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(Integer::parseInt)
                .toList();

        return multValues.get(0) * multValues.get(1);
    }

    public static List<String> getMatches(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }
}
