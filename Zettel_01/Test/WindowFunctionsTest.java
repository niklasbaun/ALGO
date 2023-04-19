import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowFunctionsTest {

    /**
     * tests for slidingWindowSum method
     */
    @Test
    void slidingWindowSum() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {15, 20, 25, 30, 35, 40, 45, 50};
        int[] actual = WindowFunctions.slidingWindowSum(input, 5);
        assertArrayEquals(expected, actual);

        int[] input2 = {-5, -23, 21, 21, 67, 32, 1};
        int[] expected2 = {-7, 1, 43, 100, 99, 33};
        int[] actual2 = WindowFunctions.slidingWindowSum(input2, 3);
    }

    @Test
    void tumblingWindowAverage() {
    }
}