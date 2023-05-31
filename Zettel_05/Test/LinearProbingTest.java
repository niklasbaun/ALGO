import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearProbingTest {

    LinearProbing linearProbing;
    /**
     * set up for the test
     * create Linear Probing object
     */
    @BeforeEach
    void setUp() {
        //create FixedRangeHashFunction object
        FixedRangeHashFunction fixed = new FixedRangeHashFunction() {
            @Override
            public int hash(Object key) {
                return key.hashCode() % 10; }
        };
        linearProbing = new LinearProbing(10, fixed);

        }

    @Test
    void put() {
        linearProbing.insert(1);
        linearProbing.insert(2);
        linearProbing.insert(11);

        assertEquals(1, linearProbing.get(1));
        assertEquals(2, linearProbing.get(2));
        assertEquals(3, linearProbing.get(11));
    }

    @Test
    void get() {
    }
}