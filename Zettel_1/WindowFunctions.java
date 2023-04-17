/**
 * Adaptation from window functions known from stream processing analytics to process arrays in a stream-like fashion.
 */
public class WindowFunctions {

    /**
     * This sliding window function computes the sum of multiple windowSize-many consecutive elements in an array.
     * It slides one element at a time, producing an overlap of windows.
     * @param input The array to process with sliding window (needs to be have at least windowSize elements).
     * @param windowSize The positive size of the window.
     * @return The results for each window when sliding one element at a time.
     */
    public static int[] slidingWindowSum(int[] input, int windowSize){
        //TODO: 1.4 a)
        return null;
    }

    /**
     * This tumbling window function computes the average of multiple windowSize-many consecutive elements in an array.
     * It slides windowSize element at a time, producing no overlap of windows.
     * @param input The array to process with tumbling window
     *              (needs to have an integer number of distinct windows with exactly windowSize elements).
     * @param windowSize The positive size of the window.
     * @return The results for each window when sliding windowSize element at a time.
     */
    public static double[] tumblingWindowAverage(int[] input, int windowSize){
        //TODO: 1.4 b)
        return null;
    }

}
