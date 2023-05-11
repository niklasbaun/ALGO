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
        ListSet<Integer> unionSet = new ListSet<>();
        unionSet.add(5);
        unionSet.add(6);
        unionSet.add(7);
        unionSet.add(8);
        unionSet.add(9);
        unionSet.add(10);
        listSet.union(unionSet);
        for(int i=0; i<10; i++){
            assertEquals(true, listSet.contains(i+1));
        }
    }

    @Test
    void intersect() {
        ListSet<Integer> intersectSet = new ListSet<>();
        intersectSet.add(3);
        intersectSet.add(5);
        intersectSet.add(7);
        intersectSet.add(-1);
        listSet.intersect(intersectSet);
        //the two remaining elements
        assertEquals(true, listSet.contains(3));
        assertEquals(true, listSet.contains(5));
        //test if an element from the inputted list would be there
        assertEquals(false, listSet.contains(7));
        //test if an element from the existing list is still there
        assertEquals(false, listSet.contains(2));
    }

    @Test
    void subtract() {
        ListSet<Integer> subtractSet = new ListSet<>();
        subtractSet.add(3);
        subtractSet.add(5);
        listSet.subtract(subtractSet);
        assertEquals(true, listSet.contains(1));
        assertEquals(true, listSet.contains(2));
        assertEquals(true, listSet.contains(4));
        assertEquals(false, listSet.contains(3));
        assertEquals(false, listSet.contains(5));
    }
}