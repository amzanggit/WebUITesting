package org.example.finder;

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

    public int findFakeBar(List<Integer> goldBarList) throws NoFakeBarException, NoDuplicateValueException {
        Set<Integer> set = new HashSet<>(goldBarList.size());
        for (Integer item : goldBarList) {
            // Test if there is duplicate value
            if (set.contains(item)) {
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
        List<Integer> firstGroup = goldBarList.subList(start, start + third);
        List<Integer> secondGroup = goldBarList.subList(start + third, start + 2 * third);
        if (weigh(firstGroup, secondGroup) == 0) {
            // Check case when third is equal to 1
            if (third == 1) {
                // all gold bars are real
                throw new NoFakeBarException("No fake bar is found");
            }
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
