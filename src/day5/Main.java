package day5;

import until.InputReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.getLines(5);

        Map<String, List<String>> rules = new HashMap<>();
        List<String> updates = new ArrayList<>();

        input.forEach(line -> {
            if (line.contains("|")) {
                String key = line.split("\\|")[0];
                String value = line.split("\\|")[1];
                List<String> currentValues = rules.get(key) == null ? new ArrayList<>() : rules.get(key);

                currentValues.add(value);
                rules.put(key, currentValues);
            }else if (line.contains(",")) {
                updates.add(line);
            }
        });

        System.out.println(partOne(rules, updates));
        System.out.println(partTwo(rules, updates));
    }

    private static int partTwo (Map<String, List<String>> rules, List<String> updates) {
        int result = 0;

        List<String> invalidUpdates = getUpdates(rules, updates, false);
        List<String> corectedUpdate = new ArrayList<>();

        for (String invalidUpdate : invalidUpdates) {
            List<String> pages = new ArrayList<>(List.of(invalidUpdate.split(",")));

            for (int i = pages.size() - 1; i > 0; i--) {
                List<String> before = pages.subList(0, i);
                List<String> rulesForNum = rules.get(pages.get(i));

                if (rulesForNum == null) {
                    continue;
                }

                for (String rule : rulesForNum) {
                    // index where error is
                    int index = before.indexOf(rule);

                    if (index != -1) {
                        String tmp = pages.get(index);
                        pages.set(index, pages.get(i));
                        pages.set(i, tmp);
                        i++;
                        break;
                    }

                }
            }
            corectedUpdate.add(String.join(",", pages));
        }

        return getSumOfMiddle(corectedUpdate);
    }

    private static int partOne(Map<String, List<String>> rules, List<String> updates) {
        return getSumOfMiddle(getUpdates(rules, updates, true));
    }

    private static int getSumOfMiddle(List<String> nums) {
        int result = 0;
        for (String num : nums) {
            List<String> orderAsList = List.of(num.split(","));
            result += Integer.parseInt(orderAsList.get(orderAsList.size() / 2));
        }
        return result;
    }

    private static List<String> getUpdates(Map<String, List<String>> rules, List<String> updates, boolean correctOrder) {
        List<String> correctOrders = new ArrayList<>();
        List<String> incorrectOrders = new ArrayList<>();

        for (String update : updates) {
            List<String> pages = List.of(update.split(","));
            boolean correct = true;

            for (int i = pages.size(); i > 0; i--) {
                List<String> before = pages.subList(0, i);
                List<String> ruleForNum = rules.get(pages.get(i - 1));

                if (ruleForNum == null) {
                    continue;
                }

                if (!Collections.disjoint(before, ruleForNum)){
                    correct = false;
                }
            }
            if (correct) {
                correctOrders.add(update);
            } else {
                incorrectOrders.add(update);
            }
        }

        return correctOrder ? correctOrders : incorrectOrders;
    }
}
