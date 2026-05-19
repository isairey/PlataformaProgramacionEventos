package org.ess.app.common;

import lombok.Getter;
import org.ess.app.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Data {

    public static User user;
    public static String token;

    // List of parishes in Jamaica
    @Getter
    private static final List<String> parishes = Arrays.asList(
            "Kingston",
            "St. Andrew",
            "St. Catherine",
            "Clarendon",
            "Manchester",
            "St. Elizabeth",
            "Westmoreland",
            "Hanover",
            "St. James",
            "Trelawny",
            "St. Ann",
            "St. Mary",
            "Portland",
            "St. Thomas"
    );

    /**
     * Retrieves all possible two-level combinations of parishes in Jamaica.
     *
     * @return a list of strings representing the two-level combinations of parishes
     */
    public static List<String> getAllCombination() {
        // Print all parishes
        System.out.println("Parishes in Jamaica:");
        for (String parish : parishes) {
            System.out.println(parish);
        }

        // Print all possible twoLevelCombinations
        List<List<String>> twoLevelCombinations = generateTwoLevelCombinations();

        // Print all twoLevelCombinations
        System.out.println("All possible twoLevelCombinations in two levels:");
        List<String> combinations = new ArrayList<>();
        for (List<String> combination : twoLevelCombinations) {
            System.out.println(combination);
            combinations.add(String.join(" - ", combination));
        }

        return combinations;
    }

    /**
     * Generates two-level combinations of parishes in Jamaica.
     * Each combination consists of two distinct parishes.
     *
     * @return a list of lists representing the two-level combinations of parishes
     */
    private static List<List<String>> generateTwoLevelCombinations() {
        List<List<String>> combinations = new ArrayList<>();
        for (int i = 0; i < Data.parishes.size(); i++) {
            for (int j = i + 1; j < Data.parishes.size(); j++) {
                List<String> combination = new ArrayList<>();
                combination.add(Data.parishes.get(i));
                combination.add(Data.parishes.get(j));
                combinations.add(combination);
            }
        }
        return combinations;
    }

}
