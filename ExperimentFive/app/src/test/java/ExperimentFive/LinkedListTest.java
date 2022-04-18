package ExperimentFive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    void testRemoveNULL() {
        LinkedList<Double> ll = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            ll.add(i * 1.0);
        }
        ll.add(null);
        assertTrue(ll.remove(null));
    }

    @Test
    void testRemoveNULLFail() {
        LinkedList<Double> ll = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            ll.add(i * 1.0);
        }
        assertFalse(ll.remove(null));
    }

    @Test
    void testRemoveEqual() {
        LinkedList<Double> ll = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            ll.add(i * 1.0);
        }
        assertTrue(ll.remove(2.0));
    }

    @Test
    void testRemoveNotEqual() {
        LinkedList<Double> ll = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            ll.add(i * 1.0);
        }
        assertFalse(ll.remove(3.5));
    }

    @Test
    void testRemoveNullListNull() {
        LinkedList<Double> ll = new LinkedList<>();
        assertFalse(ll.remove(3.5));
    }

    @Test
    void testRemoveNotEqualListNull() {
        LinkedList<Double> ll = new LinkedList<>();
        assertFalse(ll.remove(3.5));
    }
}
