import org.example.exception.EmptyGoldBarListException;
import org.example.exception.InvalidGoldNumberException;
import org.example.exception.NoDuplicateValueException;
import org.example.exception.NoFakeBarException;
import org.example.finder.GoldBarFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoldBarFinderTest {
    private GoldBarFinder finder;

    @Test
    public void testTwoBarsOneFake() throws NoDuplicateValueException, NoFakeBarException, EmptyGoldBarListException, InvalidGoldNumberException {
        finder = new GoldBarFinder(1);
        List<Integer> goldBarList = List.of(0, 1);
        int fakeBar = finder.findFakeBar(goldBarList);

        assertEquals(1, fakeBar);
    }
    @Test
    public void testSingleFakeBarWithAllUniqueBars() throws EmptyGoldBarListException, NoDuplicateValueException, InvalidGoldNumberException, NoFakeBarException {
        finder = new GoldBarFinder(4);
        List<Integer> goldBarList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
        int fakeBarIndex = finder.findFakeBar(goldBarList);

        assertEquals(4, fakeBarIndex);
    }

    @Test
    public void testEvenNumberGoldBarList() throws EmptyGoldBarListException, NoDuplicateValueException, InvalidGoldNumberException, NoFakeBarException {
        finder = new GoldBarFinder(4);
        List<Integer> goldBarList = List.of(0, 1, 2, 3, 4, 5, 6, 7);
        int fakeBarIndex = finder.findFakeBar(goldBarList);
        assertEquals(4, fakeBarIndex);
    }

    @Test
    public void testOddNumberGoldBarList() throws EmptyGoldBarListException, NoDuplicateValueException, InvalidGoldNumberException, NoFakeBarException {
        finder = new GoldBarFinder(1);
        List<Integer> goldBarList = List.of(0, 1, 2);
        int fakeBarIndex = finder.findFakeBar(goldBarList);
        assertEquals(1, fakeBarIndex);
    }

    @Test
    public void testSingleDuplicateValue() {
        finder = new GoldBarFinder(2);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
        });

        assertEquals("Duplicate gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testAllDuplicatesOfNonFakeBars() {
        finder = new GoldBarFinder(1);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 0, 0, 0, 0));
        });

        assertEquals("Duplicate gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testMultipleDuplicateNonFakeBars() {
        finder = new GoldBarFinder(8);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 0, 1, 1, 1, 2, 3, 4, 5, 5, 6, 7, 8));
        });

        assertEquals("Duplicate gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testMultipleDuplicateFakeBars() {
        // 5 is the fake gold bar number
        finder = new GoldBarFinder(5);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8));
        });

        assertEquals("Duplicate fake gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testAllRealBars() throws NoDuplicateValueException, NoFakeBarException, EmptyGoldBarListException, InvalidGoldNumberException {
        // Fake gold bar number is outside of the range
        finder = new GoldBarFinder(9);
        Exception exception = assertThrows(NoFakeBarException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        });
        assertEquals("No fake bar is found", exception.getMessage());
    }


    @Test
    public void testNegativeFakeGoldNumber() {
        finder = new GoldBarFinder(-1); // Set fake gold number to -1
        List<Integer> goldBarList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Exception exception = assertThrows(InvalidGoldNumberException.class, () -> {
            finder.findFakeBar(goldBarList);
        });
        assertEquals("Invalid fake gold number!", exception.getMessage());
    }

    @Test
    public void testNegativeNonFakeGoldNumber() {
        finder = new GoldBarFinder(5);
        List<Integer> goldBarList = List.of(-2, -1, 0, 1, 2, 3, 4, 5, 6);
        Exception exception = assertThrows(InvalidGoldNumberException.class, () -> {
            finder.findFakeBar(goldBarList);
        });
        assertEquals("Gold bar list contains negative numbers!", exception.getMessage());
    }

    @Test
    public void testNullGoldBarList() {
        finder = new GoldBarFinder(3);
        assertThrows(IllegalArgumentException.class, () -> finder.findFakeBar(null));
    }

    @Test
    public void testEmptyGoldBarList() {
        finder = new GoldBarFinder(3);
        EmptyGoldBarListException exception = assertThrows(EmptyGoldBarListException.class, () -> finder.findFakeBar(new ArrayList<>()));
        assertEquals("Gold bar list cannot be empty.", exception.getMessage());
    }

    @Test
    public void testEvenLengthGoldBarList() throws Exception {
        finder = new GoldBarFinder(6);
        List<Integer> goldBarList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int fakeBarIndex = finder.findFakeBar(goldBarList);
        assertEquals(6, fakeBarIndex);
    }

    @Test
    public void testOddLengthGoldBarList() throws Exception {
        finder = new GoldBarFinder(2);
        List<Integer> goldBarList = List.of(0, 1, 2);
        int fakeBarIndex = finder.findFakeBar(goldBarList);
        assertEquals(2, fakeBarIndex);
    }

    @Test
    public void testDuplicateRealGoldBars() {
        // 5 is the fake gold bar number, but we have duplicate real gold bars (4 in this case)
        finder = new GoldBarFinder(5);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 4, 5, 6, 7, 8));
        });

        assertEquals("Duplicate gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testDuplicateFakeGoldBars() {
        // 5 is the fake gold bar number, and we have duplicate fake gold bars
        finder = new GoldBarFinder(5);
        Exception exception = assertThrows(NoDuplicateValueException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
        });

        assertEquals("Duplicate fake gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testEmptyInput() throws NoDuplicateValueException, NoFakeBarException, EmptyGoldBarListException, InvalidGoldNumberException {
        finder = new GoldBarFinder(3);
        Exception exception = assertThrows(EmptyGoldBarListException.class, () -> {
            finder.findFakeBar(Collections.emptyList());
        });
        assertEquals("Gold bar list cannot be empty.", exception.getMessage());
    }

}
