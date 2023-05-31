package hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a map that uses linear hashing to expand the underlying hash table
 *
 * @param <K>
 * @param <V>
 */
public class LinearHashingMap<K, V> implements Map<K, V> {

    private final int initialBucketCount;

    private final double alphaMax;//max load factor

    private final List<List<MapEntry<K, V>>> buckets;

    private final HashFunction<K> hashFunction;

    private int currentLevel = 0;

    private int expansionPointer = 0;

    private int numberOfElements;

    //new variables, needed for further expension
    private int numberOfBuckets;



    public LinearHashingMap(final int initialBucketCount, double alphaMax, HashFunction<K> h) {
        this.initialBucketCount = initialBucketCount;
        this.alphaMax = alphaMax;

        buckets = new ArrayList<>();

        for (int i = 0; i < initialBucketCount; i++)
            buckets.add(i, new LinkedList<MapEntry<K, V>>());

        hashFunction = h;
    }

    /**
     * get the address for the given key with respect to current level
     *
     * @param key
     * @return the address for the given key with respect to current level
     */
    public int getAddress(K key) {
        //check if the key is null
        if (key == null)
            throw new IllegalArgumentException("key must not be null");
        //get the hash value of the key
        int hash = hashFunction.hash(key);
        //if smaller than pointer then use hash1
        if (hash < expansionPointer || hash > initialBucketCount) {
            return  hashFunction.hash(key) + (int) Math.pow(2, currentLevel) * initialBucketCount;
        }
        //else use hash0
        else {
            return hashFunction.hash(key);
        }
    }

    /**
     * get the current alpha value, i.e. the current load factor
     *
     * @return the current alpha value
     */
    public double getAlpha() {
        //get the number of full buckets
        int numberOfElements = 0;
        for (List<MapEntry<K, V>> bucket : buckets) {
            if (bucket.size() > 0) {
                numberOfElements++;
            }
        }
        //return the number of elements divided by the number of buckets
        return numberOfElements / (double) numberOfBuckets;
    }

    /**
     * check if number of elements in hash table exceeds threshold
     *
     * @return true if the hash table needs to be extended
     */
    public boolean checkOverflow() {
        //check length of buckets
        return getAlpha() >= alphaMax;
    }

    /**
     * expands the hash table
     */
    protected void split() {
        //add new bucket to the end of the list while alpha is greater than alphaMax
        while (getAlpha()>alphaMax){
            //check if pointer is at the initial size; if yes than increase level
            if (expansionPointer == initialBucketCount){
                //one level was completed
                currentLevel++;
                //reset pointer
                expansionPointer = 0;
                //set new size
                numberOfBuckets = initialBucketCount * (int) Math.pow(2, currentLevel);
            }
            //add new bucket
            buckets.add(new LinkedList<MapEntry<K, V>>());
            //rehash all elements in the bucket with new hash function, which is double of old function
            for (MapEntry<K, V> entry : buckets.get(expansionPointer)){
                //get the new hash value
                //how can the hash function be changed?
                int hash = hashFunction.hash(entry.getKey());
                //add the entry to the new bucket
                buckets.get(hash).add(entry);
            }
            //increment pointer
            expansionPointer++;
        }
    }


    /**
     * get the value for the given key
     *
     * @param key
     * @return the value for the given key
     */
    public V get(K key) {
        //check if the key is null
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        //get the address for the key
        int address = getAddress(key);
        //check if the address is not -1
        if (address != -1) {
            //return the value for the key
            return buckets.get(address).get(address).getValue();
        }
        return null;
    }

    public void put(K key, V value) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                next.setValue(value);
                return;
            }
        }

        buckets.get(getAddress(key)).add(new MapEntry<K, V>(key, value));

        numberOfElements++;
        if (checkOverflow()) split();
        return;
    }


    public void remove(K key) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                iterator.remove();
                numberOfElements--;
                break;
            }
        }
        return;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Iterable<?> bucket : buckets) {
            str.append('[');
            for (Object entry : bucket)
                str.append(entry);

            str.append(']');
        }

        return str.toString();
    }

}
