import org.example.exception.NoDuplicateValueException;
import org.example.exception.NoFakeBarException;
import org.example.finder.GoldBarFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoldBarFinderTest {

    // 正常情況檢測出假金條
    @Test
    public void test() {

    }
    @Test
    public void testDuplicateValues() {
        // 5 is the fake gold bar number
        GoldBarFinder finder = new GoldBarFinder(5);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8));
        });
        assertEquals("Duplicate gold bar sequence numbers are not allowed.", exception.getMessage());
    }

    @Test
    public void testAllRealBars() throws NoDuplicateValueException, NoFakeBarException {
        // Fake gold bar number is outside of the range
        GoldBarFinder finder = new GoldBarFinder(9);
        int result = finder.findFakeBar(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(-1, result);  // Expects -1 indicating all bars are real
    }

    @Test
    public void testOneDuplicateCoin() {
        List<Integer> goldBarList = new ArrayList<>();
        int n = 9;
        for (int i = 0; i < n; i++) {
            goldBarList.add(i);
        }
        goldBarList.add(0);

        // 確保在建構函數中拋出異常
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            GoldBarFinder test = new GoldBarFinder(goldBarList.size());
        });
        assertTrue(exception.getMessage().contains("Duplicate gold bar sequence numbers are not allowed."));
    }

    @Test
    public void testNegativeFakeGoldNumber() {
        GoldBarFinder finder = new GoldBarFinder(-1); // Set fake gold number to -1
        List<Integer> goldBarList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);

        // Expect an exception because fake gold number is negative
        Exception exception = assertThrows(RuntimeException.class, () -> {
            finder.findFakeBar(goldBarList);
        });

        assertEquals("Invalid fake gold number!", exception.getMessage());
    }

    @Test
    public void testEmptyInput() throws NoDuplicateValueException, NoFakeBarException {
        GoldBarFinder finder = new GoldBarFinder(3);  // Fake gold number doesn't matter here

        int fakeBarIndex = finder.findFakeBar(Collections.emptyList());

        assertEquals(-1, fakeBarIndex, "Expected -1 for an empty gold bar list");
    }


    @Test
    public void testTwoBarsOneFake() throws NoDuplicateValueException, NoFakeBarException {
        GoldBarFinder finder = new GoldBarFinder(1); // Set bar number 1 as fake
        List<Integer> goldBarList = List.of(0, 1);

        int fakeBarIndex = finder.findFakeBar(goldBarList);

        assertEquals(1, fakeBarIndex); // Expect index 1 which represents bar number 1
    }
}
