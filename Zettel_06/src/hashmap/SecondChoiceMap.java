package hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a map that uses second choice hashing
 *
 * @param <K>
 * @param <V>
 */
public class SecondChoiceMap<K, V> implements Map<K, V> {

    private final List<List<MapEntry<K, V>>> table1;

    private final List<List<MapEntry<K, V>>> table2;

    private final HashFunction<K> h1;

    private final HashFunction<K> h2;

    public final int numBuckets;

    /**
     * Constructor
     *
     * @param numBuckets the number of buckets to use
     * @param h1         the first hash function
     * @param h2         the second hash function
     */
    public SecondChoiceMap(int numBuckets, HashFunction<K> h1, HashFunction<K> h2) {
        this.numBuckets = numBuckets;
        this.h1 = h1;
        this.h2 = h2;
        table1 = new ArrayList<>();
        table2 = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            table1.add(new LinkedList<>());
            table2.add(new LinkedList<>());
        }
    }

    /**
     * get the value for the given key
     * @param key the key for which to get the value
     * @return the value for the given key
     */
    @Override
    public V get(K key) {
        //check if the key is null
        if (key == null)
            throw new IllegalArgumentException("key must not be null");
        //get hash value with 1st function
        int hash1 = h1.hash(key);
        //get hash value with 2nd function
        int hash2 = h2.hash(key);
        //check if the key is in the first table
        for (MapEntry<K, V> entry : table1.get(hash1)) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        //check if the key is in the second table
        for (MapEntry<K, V> entry : table2.get(hash2)) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        //return null if the key is not in either table
        return null;
    }

    /**
     * put the given key value pair into the map
     * @param key the key to put
     * @param value the value to put
     */
    @Override
    public void put(K key, V value) {
        //check if the key is null
        if (key == null)
            throw new IllegalArgumentException("key must not be null");
        //get hash value with 1st function
        int hash1 = h1.hash(key);
        //get hash value with 2nd function
        int hash2 = h2.hash(key);
        //check in which table to put the Pair; decided by which table has less elements
        if(getNumberOfFullBuckets(table1) <= getNumberOfFullBuckets(table2)) {
            //check if the key is already in the first table
            for (MapEntry<K, V> entry : table1.get(hash1)) {
                //if true replace the value
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
            //add the key value pair to the first table
            table1.get(hash1).add(new MapEntry<>(key, value));
        } else {
            //check if the key is already in the second table
            for (MapEntry<K, V> entry : table2.get(hash2)) {
                //if true replace the value
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
            //add the key value pair to the second table
            table2.get(hash2).add(new MapEntry<>(key, value));
        }


    }

    @Override
    public void remove(K key) {
        //check if the key is null
        if (key == null)
            throw new IllegalArgumentException("key must not be null");
        //get hash value with 1st function
        int hash1 = h1.hash(key);
        //get hash value with 2nd function
        int hash2 = h2.hash(key);
        //check if the key is in the first table
        for (MapEntry<K, V> entry : table1.get(hash1)) {
            //if true remove the key value pair
            if (entry.getKey().equals(key)) {
                table1.get(hash1).remove(entry);
                return;
            }
        }
        //check if the key is in the second table
        for(MapEntry<K, V> entry : table2.get(hash2)) {
            //if true remove the key value pair
            if(entry.getKey().equals(key)) {
                table2.get(hash2).remove(entry);
                return;
            }
        }

    }

    /**
     * Helper method to check number of full buckets in the map
     * @return the number of full buckets in the map
     */
    public int getNumberOfFullBuckets(List<List<MapEntry<K, V>>> table) {
        //iterate through the table
        int count = 0;
        for (List<MapEntry<K, V>> bucket : table) {
            //check if the bucket is full
            if (bucket.size() > 0) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Table1: [");

        this.table1.forEach(str::append);
        str.append("]\nTable2: [");
        this.table2.forEach(str::append);

        return str.append(']').toString();
    }

}
