import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    /**
     * Test method for MergeSort using the count for inversions with the sortAndCount method
     * which will count some inversions multiple times since they will be counted for each merge
     * @see MergeSort#sortAndCount(int[])
     */
    @Test
    void sortAndCount() {

        //create array to sort with 9 inversions
        int[] array = {1, 20, 6, 4, 5, 3, 13};
        //sort the array
        int count = MergeSort.sortAndCount(array);
        //check if the number of inversions is correct
        assertEquals(15, count);


        //another array with 10 inversions
        int[] array2 = {5, 4, 3, 2, 1};
        //sort the array
        int count2 = MergeSort.sortAndCount(array2);
        //check if the number of inversions is correct
        assertEquals(10, count2);


        int[] array3 = {2, 5, 9, 4, 1, 13};
        int count3 = MergeSort.sortAndCount(array3);
        assertEquals(7, count3);
    }

    @Test
    void countAlternative(){
        int[] array = {1, 20, 6, 4, 5, 3, 13};
        int count = MergeSort.countAlternative(array);
        assertEquals(10, count);

        int[] array2 = {5, 4, 3, 2, 1};
        int count2 = MergeSort.countAlternative(array2);
        assertEquals(10, count2);

        int[] array3 = {2, 5, 9, 4, 1, 13};
        int count3 = MergeSort.countAlternative(array3);
        assertEquals(6, count3);
    }

}