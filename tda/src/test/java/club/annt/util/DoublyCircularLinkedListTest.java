package club.annt.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DoublyCircularLinkedListTest {

    @Test
    void testAddLast() {
        final List<Integer> xs = new DoublyCircularLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        xs.addFirst(11);
        xs.addLast(21);
        assertEquals(xs.get(xs.size() - 1), 21);
        assertNotEquals(xs.get(0), 21);
    }

    @Test
    void testAdd() {
        final List<Integer> xs = new DoublyCircularLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);
        xs.addLast(4);
        xs.addLast(5);
        xs.addLast(6);

        assertEquals(6, xs.size());
        assertNotEquals(xs.get(2), 11);
        xs.add(2, 11);
        assertEquals(7, xs.size());
        assertEquals(xs.get(2), 11);
    }

    @Test
    void testToString() {
        final List<Integer> xs = new DoublyCircularLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<Integer> ys = new DoublyCircularLinkedList<>();
        ys.addLast(0);

        final List<Integer> zs = new DoublyCircularLinkedList<>();

        assertEquals("[1, 2, 3]", xs.toString());
        assertEquals("[0]", ys.toString());
        assertEquals("[]", zs.toString());
    }
}