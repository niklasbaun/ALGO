/**
 * Adaptation from window functions known from stream processing analytics to process arrays in a stream-like fashion.
 * @author baun, niklas
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
        //check if the given array has at least windowSize elements
        if (input.length < windowSize) {
            throw new IllegalArgumentException("The given array has less elements than the window size.");
        }
        //create result array
        int[] result = new int[input.length - windowSize + 1];
        //loop through the array and sum up the elements in each window
        for (int i = 0; i < input.length - windowSize + 1; i++) {
            //var for Sum
            int sum = 0;
            //sum up the elements in the window
            for (int j = 0; j < windowSize; j++) {
                sum += input[i + j];
            }
            result[i] = sum;
        }
        return result;
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
        //check if even valid, natural number of windows can be created
        if (input.length % windowSize != 0) {
            throw new IllegalArgumentException("The given array has an invalid number of elements for the given window size.");
        }
        //create result array
        double[] result = new double[input.length / windowSize];
        //loop through the array and get the average of the  elements in each window
        for (int i = 0; i < input.length / windowSize; i++) {
            int sum = 0;
            //sum up the elements in the window
            for (int j = 0; j < windowSize; j++) {
                sum += input[i * windowSize + j];
            }
            result[i] = (double) sum / windowSize;
        }
        return result;
    }
}
