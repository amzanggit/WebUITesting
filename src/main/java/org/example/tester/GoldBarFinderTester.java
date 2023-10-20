package org.example.tester;

import org.example.exception.EmptyGoldBarListException;
import org.example.exception.InvalidGoldNumberException;
import org.example.exception.NoDuplicateValueException;
import org.example.exception.NoFakeBarException;
import org.example.finder.GoldBarFinder;

import java.util.ArrayList;
import java.util.List;


public class GoldBarFinderTester {

    public void runTests() throws NoFakeBarException, NoDuplicateValueException, EmptyGoldBarListException, InvalidGoldNumberException {
        // list of all methods for each test case
        testTwoBarsOneFake();
        testSingleFakeBarWithAllUniqueBars();
        testEvenNumberGoldBarList();
        testOddNumberGoldBarList();

        testSingleDuplicateValue();
        testAllDuplicatesOfNonFakeBars();
        testMultipleDuplicateNonFakeBars();
        testMultipleDuplicateFakeBars();
        testAllRealBars();
        testNegativeFakeGoldNumber();
        testNullGoldBarList();
        testEmptyGoldBarList();
    }

    //Test Case 1: all bars are unique and with only one fake bar
    //Test Case 1.1: if only two bars and one is fake
    public void testTwoBarsOneFake() throws NoDuplicateValueException, NoFakeBarException, EmptyGoldBarListException, InvalidGoldNumberException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 2;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(1);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //Test Case 1.2: if more than two bars and all bars are unique with one fake bar（regular scenario)
    public void testSingleFakeBarWithAllUniqueBars() throws NoDuplicateValueException, NoFakeBarException, EmptyGoldBarListException, InvalidGoldNumberException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(9);
        System.out.println("The fake bar is: " + test.findFakeBar(goldBarList));
    }

    //Test Case 2: if n is even integer
    public void testEvenNumberGoldBarList() {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            finder.findFakeBar(List.of(1, 2, 3, 4));
            System.out.println("Test successful for even number of gold bars.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Test Case 3: if n is odd integer
    public void testOddNumberGoldBarList() {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            finder.findFakeBar(List.of(1, 2, 3));
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    //Test Case 4: if there are duplicate value
    //test case 4.1: if enter one duplicate value
    public void testSingleDuplicateValue() throws NoDuplicateValueException {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            int result = finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Invalid ");
        }
    }

    //test case 4.2: if all entered value are duplicate and fake bar does not exist
    public void testAllDuplicatesOfNonFakeBars() throws NoDuplicateValueException {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            int result = finder.findFakeBar(List.of(0,0,0));
            System.out.println("The fake bar is: " + result);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Invalid gold number.");
        }
    }

    //test case 4.3: if multiple duplicates of non-fake bars
    public void testMultipleDuplicateNonFakeBars() {
        try {
            GoldBarFinder finder = new GoldBarFinder(1);
            int result = finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Invalid gold number.");
        }
    }

    //test case 4.4: if multiple duplicates of fake bars
    public void testMultipleDuplicateFakeBars() {
        try {
            GoldBarFinder finder = new GoldBarFinder(5);
            int result = finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result);
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Invalid gold number.");
        }
    }

    //Test Case 5: if all gold bars are real
    public void testAllRealBars() throws NoFakeBarException {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        GoldBarFinder test = new GoldBarFinder(9);
        throw new NoFakeBarException("No fake gold bar found.");
    }

    //Test Case 6: if gold bar number is negative
    // test case 6.1: if fakeGoldNumber is negative
    public void testNegativeFakeGoldNumber() {
        try {
            GoldBarFinder finder = new GoldBarFinder(-1);
            int result = finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
            System.out.println("The fake bar is: " + result);
        } catch (NoFakeBarException e) {
            System.out.println("No expected output: " + e.getMessage());
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Invalid fake gold number!");
        } catch (RuntimeException e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }

    //test case 6.2: if non-fakeBarNumber is/are negative
    public void testNegativeNonFakeGoldNumber() {
        try {
            GoldBarFinder finder = new GoldBarFinder(5);
            int result = finder.findFakeBar(List.of(-3, -2, -1, 0, 1, 2, 3, 4, 5, 6));
            System.out.println("The fake bar is: " + result);
        } catch (NoFakeBarException e) {
            System.out.println("No fake bar is found");
        } catch (NoDuplicateValueException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Gold bar list cannot be empty.");
        } catch (InvalidGoldNumberException e) {
            System.out.println("Gold bar list contains negative numbers!");
        } catch (RuntimeException e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }

    //Test Case 7: if the list is null
    public void testNullGoldBarList() {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            finder.findFakeBar(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    //Test Case 8: if the list is empty
    public void testEmptyGoldBarList() {
        try {
            GoldBarFinder finder = new GoldBarFinder(3);
            finder.findFakeBar(new ArrayList<>());
        } catch (EmptyGoldBarListException e) {
            System.out.println("Expected error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    //More test cases can be added on, for example, if n is very large that is close to 10000

    public static void main(String[] args) throws NoFakeBarException, NoDuplicateValueException, EmptyGoldBarListException, InvalidGoldNumberException {
        new GoldBarFinderTester().runTests();
    }
}
