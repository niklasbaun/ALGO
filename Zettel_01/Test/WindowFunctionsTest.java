import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowFunctionsTest {

    /**
     * tests for slidingWindowSum method
     */
    @Test
    void slidingWindowSum() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {15, 20, 25, 30, 35, 40};
        int[] actual = WindowFunctions.slidingWindowSum(input, 5);
        assertArrayEquals(expected, actual);

        int[] input2 = {-5, -23, 21, 21, 67, 32, 1};
        int[] expected2 = {-7, 19, 109, 120, 100};
        int[] actual2 = WindowFunctions.slidingWindowSum(input2, 3);
        assertArrayEquals(expected2, actual2);

        int[] input3 = {1, 2};
        assertThrows(IllegalArgumentException.class, () -> WindowFunctions.slidingWindowSum(input3, 3));
    }

    /**
     * tests for tumblingWindowAverage method
     */
    @Test
    void tumblingWindowAverage() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        double[] expected = {2.0, 5.0, 8.0, 11.0};
        double[] actual = WindowFunctions.tumblingWindowAverage(input, 3);
        assertArrayEquals(expected, actual);

        int[] input2 = {-5, -23, 21, 21, 67, 32, 1, 42, 69, 100};
        double[] expected2 = {16.2, 48.8};
        double[] actual2 = WindowFunctions.tumblingWindowAverage(input2, 5);
        assertArrayEquals(expected2, actual2);

        int[] input3 = {1, 2};
        assertThrows(IllegalArgumentException.class, () -> WindowFunctions.tumblingWindowAverage(input3, 3));
    }
}