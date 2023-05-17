

/**
 * A buffer based on the least recently used strategy.
 * The buffer keeps a configurable number of elements.
 * Once full, it evicts the element that has not been accessed longest,
 * i.e. the put or get operation is furthest away from the present.
 */
public class LRUBuffer<Key, Value> {

    /**
     * The maximum size of the buffer. Once reached, an element will be replaced.
     */
    private final int capacity;
    //TODO: Add data structures

    public LRUBuffer(int capacity) {
        this.capacity = capacity;
        //TODO: Potentially adjust according to your implementation strategy.
    }

    /**
     * Puts the given key and value into the buffer possibly replacing and returning a value previously
     * associated with the key. If the buffer is full, the least recently used element is evicted.
     * The operation has an expected runtime of O(1).
     *
     * @param key   The search key of the element.
     * @param value The value associated with the key.
     * @return The value previously associated with key; null if the key is not present in the buffer.
     */
    public Value put(Key key, Value value) {
        //TODO: Implement
        return null;
    }

    /**
     * Gets the value associated with the given key.
     * The operation has an expected runtime of O(1).
     *
     * @param key The search key of the element.
     * @return The value associated with the key; null if the key is not present in the buffer.
     */
    public Value get(Key key) {
        //TODO: Implement
        return null;
    }

}
