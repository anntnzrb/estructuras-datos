package club.annt.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void testClear() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(1);

        /* contiene elementos */
        assertFalse(xs.isEmpty());

        /* no contiene elementos */
        xs.clear();
        assertTrue(xs.isEmpty());
    }

    @Test
    void testGet() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<String> ys = new DoublyLinkedList<>();
        ys.addLast("foo");
        ys.addLast("bar");
        ys.addLast("baz");

        assertEquals(2, xs.get(1));
        assertEquals(3, xs.get(2));
        assertEquals("baz", ys.get(2));
    }

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

    @Test
    void testAdd() {
        final List<Integer> xs = new DoublyLinkedList<>();
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
    void testRemove() {
        final List<Integer> xs = new DoublyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        int rmVal = xs.remove(3);
        assertEquals(6, xs.size());
        assertEquals(3, rmVal);
    }

    @Test
    void testRemoveFirst() {
        final List<Integer> xs = new DoublyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        int rmVal = xs.removeFirst();
        assertEquals(6, xs.size());
        assertEquals(0, rmVal);
    }

    @Test
    void testRemoveLast() {
        final List<Integer> xs = new DoublyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        int rmVal = xs.removeLast();
        assertEquals(6, xs.size());
        assertEquals(6, rmVal);
    }

    @Test
    void testToString() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<Integer> ys = new DoublyLinkedList<>();
        ys.addLast(0);

        final List<Integer> zs = new DoublyLinkedList<>();

        assertEquals("[1, 2, 3]", xs.toString());
        assertEquals("[0]", ys.toString());
        assertEquals("[]", zs.toString());
    }
}