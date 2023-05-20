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
        ModuloHashFunc hashFunc = new ModuloHashFunc(10, 0);
        linearProbing = new LinearProbing(10, hashFunc);
        }

    @Test
    void put() {
        linearProbing.put(1, 5);
        linearProbing.put(2, 7);
        linearProbing.put(11, 3);

        assertEquals(5, linearProbing.get(1));
        assertEquals(7, linearProbing.get(2));
        assertEquals(3, linearProbing.get(11));
    }

    @Test
    void get() {
    }
}