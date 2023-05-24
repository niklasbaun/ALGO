package hashmap;

import static org.junit.jupiter.api.Assertions.*;

class LinearHashingMapTest {
    //create a new LinearHashingMap
    LinearHashingMap<Integer, String> map = new LinearHashingMap<>(10, 0.75, new HashFunction<Integer>() {
        @Override
        public int hash( Integer e) {
            return e % 10;
        }
    });

    @org.junit.jupiter.api.Test
    void getAddress() {
        //add keys
        map.put(1, "One");
        map.put(2, "Two");
        map.put(12, "Twelve");

        //check if the keys are in the right buckets
        assertEquals(1, map.getAddress(1));
        assertEquals(2, map.getAddress(2));
        assertEquals(2, map.getAddress(12));

    }

    @org.junit.jupiter.api.Test
    void getAlpha() {
    }

    @org.junit.jupiter.api.Test
    void checkOverflow() {
    }

    @org.junit.jupiter.api.Test
    void split() {
    }
}