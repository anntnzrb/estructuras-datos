package club.annt.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DoublyLinkedListTest {

    @Test
    void testAddFirst() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        xs.addFirst(11);
        xs.addLast(21);
        assertEquals(xs.get(0), 11);
        assertNotEquals(xs.get(xs.size() - 1), 11);
    }

    @Test
    void testAddLast() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        xs.addFirst(11);
        xs.addLast(21);
        assertEquals(xs.get(xs.size() - 1), 21);
        assertNotEquals(xs.get(0), 21);
    }
}