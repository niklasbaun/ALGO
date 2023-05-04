import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    /**
     * Test method for MergeSort
     * @see MergeSort#sortAndCount(int[])
     */
    @Test
    void sortAndCount() {
        //create array to sort
        int[] array = {1, 20, 6, 4, 5, 3, 13};
        //sort the array
        int count = MergeSort.sortAndCount(array);
        //check if the number if the inversions is correct
        assertEquals(9, count);
    }
}