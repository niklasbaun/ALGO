public class MergeSort{
    private static int count;

    /**
     * method to sort an array and count the number of inversions
     * this will count the inversions by using the mergeSort method, which will count some inversions multiple times since
     * they will be counted for each merge
     * @param array the array to sort and count
     * @return the count of inversions
     */
    public static int sortAndCount(int[] array){
        int r = array.length - 1;
        count = 0;
        sort(array, 0, r);
        return count;
    }

    /**
     * method to sort an array
     * @param arr the array to sort
     * @param l the left index
     * @param r the right index
     */
    private static void sort(int[] arr, int l, int r) {
        if(arr == null || arr.length == 0) {
            return;
        }
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    /**
     * method to merge two arrays
     * @param arr the array to merge, which will be split
     * @param l the left index
     * @param m the middle index
     * @param r the right index
     */
    private static void merge(int[] arr, int l, int m, int r) {
        //size of array
        int size = arr.length;
        if (size > 1) {

            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            // Create temp arrays left and right
            int[] L = new int[n1];
            int[] R = new int[n2];

            //Copy data to temp arrays
            for (int i = 0; i < n1; i++)
                L[i] = arr[l + i];
            for (int j = 0; j < n2; j++)
                R[j] = arr[m + 1 + j];

            // Merge the temp arrays

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarray array
            int k = l;
            while (i < n1 && j < n2) {
                //if the left element is smaller than the right element
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    //if the right element is smaller than the left element
                    arr[k] = R[j];
                    j++;
                    //count the number of inversions
                    count += n1 - i;
                }
                     /*This part is not needed, because the remaining elements are already sorted and therefore not inversions
            // Copy remaining elements of L[] if any
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }
            // Copy remaining elements of R[] if any
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }*/
            }
        }
    }

    /**
     * method to count the number of inversions in an array
     * using the definition on the exercise sheet; like in example a)
     * @param array
     * @return number of inversions
     */
    public static int countAlternative(int[] array){
        int count = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = i+1; j < array.length; j++){
                if(array[i] > array[j]){
                    count++;
                }
            }
        }
        return count;
    }
}