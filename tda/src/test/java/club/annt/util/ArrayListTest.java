package club.annt.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void testKeepOnly() {
        // TODO
    }

    /**
     * ["foo", "bar", "baz", "qux"]
     * ==> reverse()
     * ["qux", "baz", "bar", "foo"]
     */
    @Test
    void testReverse() {
        final List<String> xs = new ArrayList<>();
        xs.addLast("foo");
        xs.addLast("bar");
        xs.addLast("baz");
        xs.addLast("qux");
        xs.reverse();

        final List<String> ys = new ArrayList<>();
        ys.addLast("qux");
        ys.addLast("baz");
        ys.addLast("bar");
        ys.addLast("foo");

        assertEquals(xs, ys);
    }

    @Test
    void testToString() {
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<Integer> ys = new ArrayList<>();
        ys.addLast(0);

        final List<Integer> zs = new ArrayList<>();

        assertEquals("[1, 2, 3]", xs.toString());
        assertEquals("[0]", ys.toString());
        assertEquals("[]", zs.toString());
    }

    @Test
    void testInsertAt() {
        final List<String> as = new ArrayList<>();
        as.addLast("Dog");
        as.addLast("Cat");
        as.addLast("Rat");

        final List<String> ext1 = new ArrayList<>();
        ext1.addLast("Deer");
        ext1.addLast("Dove");

        final List<String> as1 = new ArrayList<>();
        as1.addLast("Dog");
        as1.addLast("Cat");
        as1.addLast("Deer");
        as1.addLast("Dove");
        as1.addLast("Rat");

        as.insertAt(ext1, 2);
        assertEquals(as, as1);
    }

    @Test
    void testIsEmpty() {
        final List<Integer> xs = new ArrayList<>();
        final List<String> ys = new ArrayList<>();
        ys.addLast("hi");

        assertTrue(xs.isEmpty());
        assertFalse(ys.isEmpty());
    }

    @Test
    void testClear() {
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(1);

        /* contiene elementos */
        assertFalse(xs.isEmpty());

        /* no contiene elementos */
        xs.clear();
        assertTrue(xs.isEmpty());
    }

    @Test
    void testGet() {
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);

        final List<String> ys = new ArrayList<>();
        ys.addLast("foo");
        ys.addLast("bar");
        ys.addLast("baz");

        assertEquals(2, xs.get(1));
        assertEquals(3, xs.get(2));
        assertEquals("baz", ys.get(2));
    }

    @Test
    void testAddFirst() {
        final List<Integer> xs = new ArrayList<>();
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
        final List<Integer> xs = new ArrayList<>();
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
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(1);
        xs.addLast(2);
        xs.addLast(3);
        xs.addLast(4);
        xs.addLast(5);
        xs.addLast(6);

        assertNotEquals(xs.get(2), 11);
        xs.set(2, 11);
        assertEquals(xs.get(2), 11);

    }

    @Test
    void testCapacity() {
        final List<Integer> xs = new ArrayList<>();
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
}