public class LinearProbing implements Hashing {

    //values for the hash table
    int maxSize, Size;
    //hash table and hash function
    Object[][] hashTable;
    FixedRangeHashFunction hashFunction;

    /**
     * construcor for the linear probing hash table
     */
    LinearProbing (int maxSize, FixedRangeHashFunction hashFunction) {
        this.maxSize = maxSize;
        this.hashFunction = hashFunction;
        hashTable = new Object[maxSize][2];
        Size = 0;
    }


    /**
     * method to create the hash of an object
     * @param key The object to hash.
     * @return The hash value of the object.
     */
    private int hash(Object key){
        return key.hashCode() % maxSize;
    }

    /**
     * method to insert a key value pair into the hash table using linear probing
     * @param val The value of the (key, value) pair.
     * @return True if the value was inserted, false if the value could not be inserted.
     */
    boolean insert(Object val){
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
     * method to put a key value pair into the hash table using linear probing
     * @param key   The key of the (key, value) pair.
     * @param val The value of the (key, value) pair.
     * @return A value previously associated with the key; null if key did not exist before.
     * @throws IllegalStateException
     */
    @Override
    public Object put(Object key, Object val) throws IllegalStateException {
        //test if key is null
        if (key == null) {
            throw new IllegalStateException("Key is null");
        }
        //check if place of key is empty
        if (hashTable[hash(key)][0] == null) {
            //insert key and value
            hashTable[hash(key)][0] = key;
            hashTable[hash(key)][1] = val;
            return null;
        } else {
            //if place is not empty, use linear probing to find the next empty place
            int index = hash(key);
            while (hashTable[index][0] != null) {
                index++;
                if (index == maxSize) {
                    index = 0;
                }
            }
            //insert key and value
            hashTable[index][0] = key;
            hashTable[index][1] = val;
        }
        return null;
    }

    /**
     * method to get the value of a key from the hash table using linear probing
     * @param key The key to search for.
     * @return The value associated with the key; null if the key is not present in the buffer.
     */
    @Override
    public Object get(Object key) {
        //test if key is null
        if (key == null) {
            throw new IllegalStateException("Key is null");
        }
        //calculate the hash of the key
        int hash = hash(key);
        //check if the key is at the calculated hash
        if (hashTable[hash][0] == key) {
            return hashTable[hash][1];
        } else {
            //if not, use linear probing to find the key
            int index = hash;
            while (hashTable[index][0] != key) {
                index++;
                //loop if at the end of the hash table
                if (index == maxSize) {
                    index = 0;
                }
            }
            return hashTable[index][1];
        }
    }
}
