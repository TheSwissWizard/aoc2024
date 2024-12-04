package day4;

import until.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.getLines(4);

        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    public static int partTwo(List<String> lines) {
        int result = 0;

        //Transform list of Strings into a 2D String array
        String[][] lineArray = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            lineArray[i] = new String[lines.get(i).length()];
            for (int j = 0; j < lines.get(i).length(); j++) {
                lineArray[i][j] = String.valueOf(lines.get(i).charAt(j));
            }
        }

        //search for MAS / SAM
        for (int i = 0; i < lineArray.length - 1; i++) {
            for (int j = 0; j < lineArray[i].length - 1; j++) {
                if (lineArray[i][j].equalsIgnoreCase("A") && i > 0 && j > 0) {
                    String s1 = lineArray[i - 1][j - 1] + lineArray[i][j] + lineArray[i + 1][j + 1];
                    String s2 = lineArray[i + 1][j - 1] + lineArray[i][j] + lineArray[i - 1][j + 1];

                    Pattern pattern = Pattern.compile("(MAS|SAM)");

                    if (pattern.matcher(s1).find() && pattern.matcher(s2).find()){
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public static int partOne(List<String> lines) {
        int result = 0;

        result += count(lines);
        result += count(normalizeVertical(lines));
        result += count(normalizeDiagonal(lines));
        result += count(normalizeDiagonal(
                lines.stream().map(s -> new StringBuilder().append(s).reverse().toString()).toList()));

        return result;
    }

    public static List<String> normalizeDiagonal(List<String> lines) {
        List<String> result = new ArrayList<>();
        int noOfDiagonals = lines.size();
        for (int i = 0; i < noOfDiagonals * 2; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                int index = i - j;
                if (index < noOfDiagonals && j < noOfDiagonals) {
                    sb.append(lines.get(index).charAt(j));
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> normalizeVertical(List<String> lines) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < lines.getFirst().length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line.charAt(i));
            }
            result.add(sb.toString());
        }

        return result;
    }

    public static int count(List<String> lines) {
        Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
        int result = 0;

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                result++;
            }
        }

        return result;
    }
}
