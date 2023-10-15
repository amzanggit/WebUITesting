package org.example.tester;

import org.example.finder.GoldBarFinder;

import java.util.ArrayList;
import java.util.List;

public class GoldBarFinderTester {
    // List<Integer> goldBarList = new ArrayList<>();

    public void runTests() {
        // list of all methods for each test case

    }

    //Test Case 1: all bars are unique and with only one fake bar
    public void testUniqueBarsWithOneFakeBar() {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 8;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(9);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //Test Case 2: if there are duplicate coins

    //test case 2.1: if enter one duplicate coin 全部數據， 額外加一個重複的
    public void testOneDuplicateCoin(List<Integer> goldBarList) {
        // List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        goldBarList.add(0);
        GoldBarFinder test = new GoldBarFinder(6);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //test case 2.2: if enter two duplicate coin
    public void testTwoDuplicateCoin(List<Integer> goldBarList) {
        //List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        goldBarList.add(0);
        goldBarList.add(0);
        GoldBarFinder test = new GoldBarFinder(6);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //test case 2.3:
    public void test() {

    }

    //Test Case 3:


    public static void main(String[] args) {
        new GoldBarFinderTester().runTests();
    }
}
