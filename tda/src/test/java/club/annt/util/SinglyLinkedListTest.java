package club.annt.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SimplyLinkedListTest {

    @Test
    void testClear() {
        final List<Integer> xs = new SimplyLinkedList<>();
        xs.addLast(1);

        /* contiene elementos */
        assertFalse(xs.isEmpty());

        /* no contiene elementos */
        xs.clear();
        assertTrue(xs.isEmpty());
    }

    @Test
    void testGet() {
        final List<Integer> xs = new SimplyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<String> ys = new SimplyLinkedList<>();
        ys.addLast("foo");
        ys.addLast("bar");
        ys.addLast("baz");

        assertEquals(2, xs.get(1));
        assertEquals(3, xs.get(2));
        assertEquals("baz", ys.get(2));
    }

    @Test
    void testAddFirst() {
        final List<Integer> xs = new SimplyLinkedList<>();
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
        final List<Integer> xs = new SimplyLinkedList<>();
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
        final List<Integer> xs = new SimplyLinkedList<>();
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
        final List<Integer> xs = new SimplyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        int rmVal = xs.remove(3);
        assertEquals(6, xs.size());
        assertEquals(3, rmVal);
    }

    @Test
    void testRemoveFirst() {
        /* vars */
        int rmVal;

        /* xs */
        final List<Integer> xs = new SimplyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        rmVal = xs.removeFirst();
        assertEquals(6, xs.size());
        assertEquals(0, rmVal);

        /* ys */
        final List<Integer> ys = new SimplyLinkedList<>();
        assertEquals(0, ys.size());
        ys.removeFirst();
        assertEquals(0, ys.size());

        /* zs */
        final List<Integer> zs = new SimplyLinkedList<>();
        zs.addLast(99);
        assertEquals(1, zs.size());
        rmVal = zs.removeFirst();
        assertEquals(0, zs.size());
        assertEquals(99, rmVal);
    }

    @Test
    void testRemoveLast() {
        /* vars */
        int rmVal;

        /* xs */
        final List<Integer> xs = new SimplyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        assertEquals(7, xs.size());
        rmVal = xs.removeLast();
        assertEquals(6, xs.size());
        assertEquals(6, rmVal);

        /* ys */
        final List<Integer> ys = new SimplyLinkedList<>();
        assertEquals(0, ys.size());
        ys.removeFirst();
        assertEquals(0, ys.size());

        /* zs */
        final List<Integer> zs = new SimplyLinkedList<>();
        zs.addLast(99);
        assertEquals(1, zs.size());
        rmVal = zs.removeLast();
        assertEquals(0, zs.size());
        assertEquals(99, rmVal);
    }

    @Test
    void testToString() {
        final List<Integer> xs = new SimplyLinkedList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<Integer> ys = new SimplyLinkedList<>();
        ys.addLast(0);

        final List<Integer> zs = new SimplyLinkedList<>();

        assertEquals("[1, 2, 3]", xs.toString());
        assertEquals("[0]", ys.toString());
        assertEquals("[]", zs.toString());
    }

    @Test
    void iterator() {
        final List<Integer> xs = new SimplyLinkedList<>();
        IntStream.rangeClosed(0, 6)
                 .forEach(xs::addLast);

        final List<Integer> ys = new SimplyLinkedList<>();

        /* xs es no vacío */
        assertTrue(xs.iterator().hasNext());
        /* ys es vacío */
        assertFalse(ys.iterator().hasNext());
    }
}