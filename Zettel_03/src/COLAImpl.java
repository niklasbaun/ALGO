// TODO: Add Interface (3.3 b))

public class COLAImpl<E extends Comparable<E>> implements Insert<E> {

    // TODO: Add data representation (3.3 a))
    //array of arrays to represent the COLA
    private E[][] cola;




    /**
     * Inserts a new element into the array
     * if array is full, it will be resized to double the size
     * if there is a bigger array with empty space the smaller will be dumped into the bigger
     * @param key The element
     */
    public void insertElement(E key) {
        // TODO: Implement insert into COLA (3.3 a))
        //check if array is empty
        if (cola[0][0] == null) {
            cola[0][0] = key;
        }
    }

    // TODO: Implement search methods (3.3 b))

}
