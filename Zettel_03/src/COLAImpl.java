// TODO: Add Interface (3.3 b))

import java.util.ArrayList;

public class COLAImpl<E extends Comparable<E>> implements Insert<E> {

    // TODO: Add data representation (3.3 a))
    //List of arrays to represent the COLA
    private ArrayList<E[]> cola;

    /**
     * Inserts a new element into the array
     * if array is full, it will be resized to double the size
     * if there is a bigger array with empty space the smaller will be dumped into the bigger
     * merge the elements and try to put them in the next bigger array if possible
     * if not create an even bigger array to put them in till there is a fitting array
     * arrays are sorted in ascending order and have th size 2^n
     * @param key The element
     */
    public void insertElement(E key) {
        // TODO: Implement insert into COLA (3.3 a))
        //check if the 1st array is empty
        if (cola.get(0)[0] == null){
            cola.get(0)[0] = key;
        }
        //if not mergesort the existing element with the key and try to insert them in the next bigger array
        //loop though the arrays
        int n;
        for(n=1; n< cola.size(); n++){
            //check if there is a next bigger array
            if(cola.get(n) != null){
                //check if the next bigger array has empty space
                if (cola.get(n)[cola.get(n).length+1] == null ){
                    //dump all accumulated values
                }
                //mergesort the array to the others
            }
            //create a bigger array and dump the values into it

        }
    }

    // TODO: Implement search methods (3.3 b))

}
