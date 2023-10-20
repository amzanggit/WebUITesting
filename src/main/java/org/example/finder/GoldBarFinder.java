package org.example.finder;

import org.example.exception.EmptyGoldBarListException;
import org.example.exception.InvalidGoldNumberException;
import org.example.exception.NoDuplicateValueException;
import org.example.exception.NoFakeBarException;

import java.util.*;


public class GoldBarFinder {

    // define fake gold number
    private int fakeGoldNumber;

    public void setFakeGoldNumber(int fakeGoldNumber) {
        this.fakeGoldNumber = fakeGoldNumber;
    }

    public GoldBarFinder(int fakeGoldNumber) {
        this.fakeGoldNumber = fakeGoldNumber;
    }

    public int findFakeBar(List<Integer> goldBarList) throws NoFakeBarException, NoDuplicateValueException, EmptyGoldBarListException, InvalidGoldNumberException {
        // corner cases
        if (goldBarList == null) {
            throw new IllegalArgumentException("Gold bar list cannot be null.");
        }
        if (goldBarList.isEmpty()) {
            throw new EmptyGoldBarListException("Gold bar list cannot be empty.");
        }
        if (goldBarList.stream().anyMatch(n -> n < 0)) {
            // Check if the negative number is the fake gold number
            if (goldBarList.stream().filter(n -> n < 0).anyMatch(n -> n == fakeGoldNumber)) {
                throw new InvalidGoldNumberException("Invalid fake gold number!");
            } else {
                throw new InvalidGoldNumberException("Gold bar list contains negative numbers!");
            }
        }

        Set<Integer> set = new HashSet<>(goldBarList.size());
        for (Integer item : goldBarList) {
            // test if there is duplicate value
            if (set.contains(item) && item == fakeGoldNumber) {
                throw new NoDuplicateValueException("Duplicate fake gold bar sequence numbers are not allowed.");
            } else if (set.contains(item) && item != fakeGoldNumber) {
                throw new NoDuplicateValueException("Duplicate gold bar sequence numbers are not allowed.");
            }
            set.add(item);
        }
        return findFakeBarHelper(goldBarList, 0, goldBarList.size() - 1);
    }

    /**
     *
     * @param goldBarList
     * @param start
     * @param end
     * @return -1: No fake bar is found
     *
     */
    private int findFakeBarHelper(List<Integer> goldBarList, int start, int end) throws NoFakeBarException {
        int n = end - start + 1;
        if (n == 1) {
            return start;
        }

        int third = n / 3;

        // need to consider when third is less than 2 cases
        if (third <= 1) {
            // Weigh bars one by one against a known real bar or another bar if there isn't a known real one
            for (int i = start; i <= end; i++) {
                if (goldBarList.get(i) == fakeGoldNumber) {
                    return i;
                }
            }
            throw new NoFakeBarException("No fake bar is found");
        }

        List<Integer> firstGroup = goldBarList.subList(start, start + third);
        List<Integer> secondGroup = goldBarList.subList(start + third, start + 2 * third);
        if (weigh(firstGroup, secondGroup) == 0) {
            // fake bar is in the third group
            return findFakeBarHelper(goldBarList, start + 2 * third, end);
        } else if (weigh(firstGroup, secondGroup) == 1) {
            // fake bar is in the first group
            return findFakeBarHelper(goldBarList, start, start + third - 1);
        } else {
            // fake bar is in the second group
            return findFakeBarHelper(goldBarList, start + third, start + 2 * third - 1);
        }
    }

    /**
     * @param list1
     * @param list2
     * @return -1: fake bar exists in list2
     *         0: fake bar exists in neither list1 nor list2
     *         1: fake bar exists in list1
     */
    public int weigh(List<Integer> list1, List<Integer> list2) {
        if (list1.size() > list2.size()) {
            return -1;
        }
        if (list1.size() < list2.size()) {
            return 1;
        }
        if (list1.contains(fakeGoldNumber)) {
            return 1;
        }
        if (list2.contains(fakeGoldNumber)) {
            return -1;
        }
        return 0;
    }
}
