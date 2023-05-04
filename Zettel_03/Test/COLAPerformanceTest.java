import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class COLAPerformanceTest {

    @Test
    void insertElement() {
        //create new COLA
        COLAImpl<Integer> cola = new COLAImpl<Integer>();
        //insert 5 mio random elements
        Random rand = new Random();
        //save start time
        double startCOLA = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++){
            cola.insertElement(rand.nextInt(10000000));
        }
        double endCOLA = System.currentTimeMillis();

        double COLAtime = endCOLA - startCOLA;

        // try insertion and sort with array

        //save start time
        double startArray = System.currentTimeMillis();
        //create new array with size 1
        Integer[] array = new Integer[1];
        for(int i = 0; i < 5000000; i++){
            //create temp array with size of array + 1
            Integer[] temp = new Integer[array.length+1];
            //copy the array into the temp array
            System.arraycopy(array, 0, temp, 0, array.length);
            //insert the element at the end of the array
            temp[array.length] = rand.nextInt(10000000);
        }
        //sort the array
        Arrays.sort(array);
        double endArray = System.currentTimeMillis();

        double arrayTime = endArray - startArray;
        System.out.println("COLA: " + COLAtime + "ms");
        System.out.println("Array: " + arrayTime + "ms");
    }
}