package club.annt.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testReverse() {
        // TODO
    }

    @Test
    void testSize() {
        final List<Integer> xs = new LinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        /* 3 -> 100+ */
        assertFalse(xs.size() > 100);
        IntStream.rangeClosed(0, 100)
                 .forEach(xs::addLast);
        assertTrue(xs.size() > 100);

        /* 100+ -> 1000+ */
        assertFalse(xs.size() > 1000);
        IntStream.rangeClosed(0, 1000)
                 .forEach(xs::addLast);
        assertTrue(xs.size() > 1000);
    }

    @Test
    void testIsEmpty() {
        final List<Integer> xs = new LinkedList<>();
        final List<String> ys = new LinkedList<>();
        ys.addLast("hi");

        assertTrue(xs.isEmpty());
        assertFalse(ys.isEmpty());
    }

    @Test
    void testClear() {
        // TODO
    }

    @Test
    void testAddFirst() {
        final List<Integer> xs = new LinkedList<>();
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
        final List<Integer> xs = new LinkedList<>();
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
        // TODO
    }

    @Test
    void testRemoveFirst() {
        // TODO
    }

    @Test
    void testRemoveLast() {
        // TODO
    }

    @Test
    void testRemove() {
        // TODO
    }

    @Test
    void testGet() {
        final List<Integer> xs = new LinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<String> ys = new LinkedList<>();
        ys.addLast("foo");
        ys.addLast("bar");
        ys.addLast("baz");

        assertEquals(2, xs.get(1));
        assertEquals(3, xs.get(2));
        assertEquals("baz", ys.get(2));
    }

    @Test
    void testSet() {
        // TODO
    }

    @Test
    void testKeepOnly() {
        // TODO
    }

    @Test
    void testInsertAt() {
        // TODO
    }

    @Test
    void testToString() {
        final List<Integer> xs = new LinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<Integer> ys = new LinkedList<>();
        ys.addLast(0);

        final List<Integer> zs = new LinkedList<>();

        assertEquals("[1, 2, 3]", xs.toString());
        assertEquals("[0]", ys.toString()); // FIXME :: Out of memory?
        assertEquals("[]", zs.toString());
    }
}