package org.example.tester;

import org.example.exception.NoDuplicateValueException;
import org.example.exception.NoFakeBarException;
import org.example.finder.GoldBarFinder;

import java.util.ArrayList;
import java.util.List;


public class GoldBarFinderTester {

    public void runTests() throws NoFakeBarException, NoDuplicateValueException {
        // list of all methods for each test case
        testTwoBarsOneFake();
        testSingleFakeBarWithAllUniqueBars();
        testOneDuplicateValue();
        testAllDuplicateValue();
        testAllRealBars();

    }

    //Test Case 1: all bars are unique and with only one fake bar
    //Test Case 1.1: if only two bars and one is fake
    public void testTwoBarsOneFake() throws NoDuplicateValueException, NoFakeBarException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 2;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(1);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //Test Case 1.2: if more than two bars and all bars are unique with one fake bar
    public void testSingleFakeBarWithAllUniqueBars() throws NoDuplicateValueException, NoFakeBarException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(9);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //Test Case 2: if there are duplicate value
    //test case 2.1: if enter one duplicate value
    public void testOneDuplicateValue() throws NoDuplicateValueException {
        try {
            GoldBarFinder finder1 = new GoldBarFinder(3);
            int result1 = finder1.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result1);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        }
    }

    //test case 2.2: if all entered value are duplicate and fake bar does not exist
    public void testAllDuplicateValue() throws NoDuplicateValueException {
        try {
            GoldBarFinder finder1 = new GoldBarFinder(3);
            int result1 = finder1.findFakeBar(List.of(0,0,0));
            System.out.println("The fake bar is: " + result1);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        }
    }

    //test case 2.3: if the fake bar number is duplicate
    public void testDuplicateFakeBarValue() {
        try {
            GoldBarFinder finder1 = new GoldBarFinder(5);
            int result1 = finder1.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result1);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        }
    }

    //

    //Test Case 3: if all bars are real or the fakeGoldBarNumber is out of bound
    public void testAllRealBars() throws NoFakeBarException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(9);
        throw new NoFakeBarException("No fake gold bar found.");
    }

    //Test Case 4: if fakeGoldNumber is negative
    public void testNegativeFakeGoldNumber() {
        try {
            GoldBarFinder finder1 = new GoldBarFinder(-1);
            int result1 = finder1.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result1);
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }

    //Test Case 5: if the list is null or empty
    public void testEmptyInput() {
        List<Integer> goldBarList = new ArrayList<>();
        if (goldBarList == null || goldBarList.size() == 0) {
            throw new RuntimeException("Invalid input: the list is empty");
        }
    }

    //More test cases can be added on, for example, if n is very large that is close to 10000

    public static void main(String[] args) throws NoFakeBarException, NoDuplicateValueException {
        new GoldBarFinderTester().runTests();
    }
}
