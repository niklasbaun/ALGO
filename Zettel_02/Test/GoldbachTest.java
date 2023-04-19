import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GoldbachTest {
    /**
     * tests for goldbach method
     * different calues
     */
    @org.junit.jupiter.api.Test
    void goldbach() {
        Goldbach goldbach = new Goldbach();
        Pair<Long> p1 = goldbach.goldbach(1000000L);
        assertEquals(17, p1.getElement1());
        assertEquals(999983, p1.getElement2());

        Pair<Long> p2 = goldbach.goldbach(28L);
        assertEquals(5, p2.getElement1());
        assertEquals(23, p2.getElement2());

        Pair<Long> p3 = goldbach.goldbach(100L);
        assertEquals(3, p3.getElement1());
        assertEquals(97, p3.getElement2());

        Pair<Long> p4 = goldbach.goldbach(3L);
        assertNull(p4);

        Pair<Long> p5 = goldbach.goldbach(-2L);
        assertNull(p5);

        Pair<Long> p6 = goldbach.goldbach(23L);
        assertNull(p6);

        Pair<Long> p7 = goldbach.goldbach(65L);
        assertNull(p7);
    }
}