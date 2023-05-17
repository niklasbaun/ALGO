public class RobinHood implements Hashing{

    //values for the hash table
    int maxSize, Size;
    //hash table and hash function
    Object[][] hashTable;
    FixedRangeHashFunction hashFunction;

    /**
     * construcor for the linear probing hash table
     */
    RobinHood (int maxSize, FixedRangeHashFunction hashFunction) {
        this.maxSize = maxSize;
        this.hashFunction = hashFunction;
        hashTable = new Object[maxSize][2];
        Size = 0;
    }

    private int hash(Object key){
        return key.hashCode() % maxSize;
    }
    /**
     * method to insert a key value pair into the hash table using the Robin Hood method
     * this method will call the put method and calculate the hash of the value and use the hash as the key
     * @param val The value of the (key, value) pair.
     *            @return True if the value was inserted, false if the value could not be inserted.
     */
    boolean insert(Object val) {
        //calculate the hash of the value
        int hash = hash(val);
        //use put to insert into the hash table
        Object cont = put(hash, val);
        if (cont == null) {
            return true;
        }
        return false;
    }
    /**
     * method to put a key value pair into the hash table using the Robin Hood method
     * @param key   The key of the (key, value) pair.
     * @param value The value of the (key, value) pair.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public Object put(Object key, Object value) throws IllegalStateException {
        //test if key is null
        if (key == null) {
            throw new IllegalStateException("Key is null");
        }
        //check if place of key is empty
        if (hashTable[hash(key)][0] == null) {
            //insert key and value
            hashTable[hash(key)][0] = key;
            hashTable[hash(key)][1] = value;
            return null;
        }
        return key;
    }

    @Override
    public Object get(Object key) {
        return null;
    }
}
