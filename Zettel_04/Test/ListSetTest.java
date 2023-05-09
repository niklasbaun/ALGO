import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListSetTest {

    private ListSet<Integer> listSet = new ListSet<Integer>();
    @BeforeEach
    void setUp() {
        listSet.add(1);
        listSet.add(2);
        listSet.add(3);
        listSet.add(4);
        listSet.add(5);
    }

    @Test
    void add() {
        assertEquals(true, listSet.contains(1));
        assertEquals(true, listSet.contains(2));
        assertEquals(true, listSet.contains(3));
        assertEquals(true, listSet.contains(4));
        assertEquals(true, listSet.contains(5));
    }

    @Test
    void remove() {
        listSet.remove(1);
        assertEquals(false, listSet.contains(1));
        listSet.remove(2);
        assertEquals(false, listSet.contains(2));
        listSet.remove(3);
        assertEquals(false, listSet.contains(3));
        listSet.remove(4);
        assertEquals(false, listSet.contains(4));
        listSet.remove(5);
        assertEquals(false, listSet.contains(5));
    }

    @Test
    void union() {
    }

    @Test
    void intersect() {
    }

    @Test
    void subtract() {
    }
}