public class MergeSort {

    /**
     * method to sort an array and count the number of inversions
     * @param array the array to sort and count
     * @return the count of inversions
     */
    public static int sortAndCount(int[] array){
        // TODO: Aufgabe 3.4 b)
        int count = 0;
        int r = array.length - 1;
        count = sort(array, 0, r);
        return count;
    }

    /**
     * method to sort an array
     * @param arr the array to sort
     * @param l the left index
     * @param r the right index
     */
    static int count = 0;
    private static int sort(int[] arr, int l, int r) {
        if(arr == null || arr.length == 0) {
            return count;
        }
        if (l >= r) {
            return -1;
        } else {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            count = count + sort(arr, l, m);
            count = count + sort(arr, m + 1, r);

            // Merge the sorted halves
            count = count + merge(arr, l, m, r);
        }
        return count;
    }

    static int merge(int[] arr, int l, int m, int r) {
        //count the number of inversions
        int countM = 0;
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
                    countM++;
                }
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
        return countM;
    }
}
