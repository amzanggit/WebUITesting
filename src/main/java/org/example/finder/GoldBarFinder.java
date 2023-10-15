package org.example.finder;

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

    public int findFakeBar(List<Integer> goldBarList) {
        Set<Integer> set = new HashSet<>(goldBarList.size());
        for (Integer item : goldBarList) {
            // Test if there is duplicate value
            if (set.contains(item)) {
                throw new RuntimeException("Duplicate values are not allowed");
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
     * @return -1： all gold bars are real or return fake bar number.
     */
    private int findFakeBarHelper(List<Integer> goldBarList, int start, int end) {
        int n = end - start + 1;
        if (n == 1) {
            return start;
        }

        int third = n / 3;
        List<Integer> firstGroup = goldBarList.subList(start, start + third);
        List<Integer> secondGroup = goldBarList.subList(start + third, start + 2 * third);
        if (weigh(firstGroup, secondGroup) == 0) {
            // Check
            if (third == 1) {
                // all gold bars are real
                return -1;
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

    public static void main(String[] args) {


//        // Test Case 1: if there are duplicate coins
//        {
//            //Test Case 1.1: if enter duplicate coin 全部數據， 額外加一個重複的
//            {
//                List<Integer> goldBarList = new ArrayList<>();
//                int n = 9;
//                for (int i = 0; i < n; i++) {
//                    goldBarList.add(i);
//                }
//                goldBarList.add(0);
//                GoldBarFinder test = new GoldBarFinder(6);
//                System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
//            }
//
//            //有兩個數據， 並且重複
//            {
//                List<Integer> goldBarList = new ArrayList<>();
//                goldBarList.add(0);
//                goldBarList.add(0);
//                GoldBarFinder test = new GoldBarFinder(6);
//                System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
//            }
//
//
//        }
//        // test case 2: with all non-duplicate gold bars and only one fake bar
//        List<Integer> goldBarList = new ArrayList<>();
//        int n = 9;
//        for (int i = 0; i < n; i++) {
//            goldBarList.add(i);
//        }
//        GoldBarFinder test = new GoldBarFinder(6);
//        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));

        // test case 3: 全部都是真的
        {
            List<Integer> goldBarList = new ArrayList<>();
            int n = 8;
            for (int i = 0; i < n; i++) {
                goldBarList.add(i);
            }
            GoldBarFinder test = new GoldBarFinder(9);
            System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
        }

//        // test case 4: 有一個假的
//        {
//            List<Integer> goldBarList = new ArrayList<>();
//            int n = 9;
//            for (int i = 0; i < n; i++) {
//                goldBarList.add(i);
//            }
//            GoldBarFinder test = new GoldBarFinder(8);
//            System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
//        }
    }
}