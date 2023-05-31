public class LinearProbing implements Hashing, Remove {

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
        hashTable = new Object[maxSize][3];
        for (int i = 0; i < maxSize; i++) {
            hashTable[i][2] = "empty";
        }
        Size = 0;
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
        if (hashTable[hashFunction.hash(key)][0] != null) {
            //insert key and value
            hashTable[hashFunction.hash(key)][0] = key;
            hashTable[hashFunction.hash(key)][1] = val;
            hashTable[hashFunction.hash(key)][2] = "full";
            return null;
        } else {
            //if place is not empty, use linear probing to find the next empty place
            int index = hashFunction.hash(key);
            while (hashTable[index][0] != "empty") {
                index++;
                if (index == maxSize) {
                    index = 0;
                }
            }
            //insert key and value
            hashTable[index][0] = key;
            hashTable[index][1] = val;
            hashTable[index][2] = "full";
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
        int hash = hashFunction.hash(key);
        //check if the key is at the calculated hash
        if (hashTable[hash][0] == key) {
            return hashTable[hash][1];
        } else {
            //if not, use linear probing to find the key
            int index = hash;
            while (hashTable[index][0] != null) {
                if (hashTable[index][0] == key) {
                    return hashTable[index][1];
                }
                //loop if at the end of the hash table
                if (index == maxSize) {
                    index = 0;
                }
                index++;
            }
        }
        return null;
    }


    @Override
    public Object remove(Object key) {
        //test if key is null
        if (key == null) {
            throw new IllegalStateException("Key is null");
        }
        int hash = hashFunction.hash(key);
        //check if the key is at the calculated hash
        if (hashTable[hash][0] == key) {
            hashTable[hash][2] = "removed";
            return hashTable[hash][1];
        } else {
            //if not, use linear probing to find the key
            int index = hash;
            while (hashTable[index][0] != "empty" && hashTable[index][2] != "removed") {
                if (hashTable[index][0] == key) {
                    hashTable[index][2] = "removed";
                    return hashTable[index][1];
                }
                //loop if at the end of the hash table
                if (index == maxSize) {
                    index = 0;
                }
                index++;
            }
        }
        //return null if key is not in the hash table
        return null;
    }
}
