package club.annt.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ClassHasNoToStringMethod", "ClassWithoutConstructor",
        "AssertWithoutMessage", "JUnitTestMethodWithNoAssertions",
        "NonBooleanMethodNameMayNotStartWithQuestion"})
final class DoublyLinkedListTest {
    private List<Integer> as; /* lista no vacía */
    private List<Integer> bs; /* lista no vacía (1 elemento) */
    private List<Integer> zs; /* lista vacía */

    @BeforeEach
    void setUp() {
        /* as */
        as = new DoublyLinkedList<>();
        /* llenar la colección de números de 0 a 9 */
        IntStream.rangeClosed(0, 9)
                 .forEach(as::addLast);

        /* bs */
        bs = new DoublyLinkedList<>();
        bs.addLast(0);

        /* zs */
        zs = new DoublyLinkedList<>();
    }

    @Test
    void size() {
        /* as */
        assertNotEquals(9, as.size());
        assertEquals(10, as.size());

        /* agregar mas elementos */
        as.addLast(11);
        assertNotEquals(10, as.size());
        assertEquals(11, as.size());

        /* bs */
        assertEquals(1, bs.size());

        /* zs */
        assertEquals(0, zs.size());
    }

    @Test
    void isEmpty() {
        /* xs */
        assertFalse(as.isEmpty());
        assertNotEquals(0, as.size());

        /* zs */
        assertTrue(zs.isEmpty());
        assertEquals(0, zs.size());

        /* agregar elementos */
        zs.addLast(1);
        assertFalse(zs.isEmpty());
        assertNotEquals(0, zs.size());
    }

    @Test
    void clear() {
        /* contiene elementos */
        assertFalse(as.isEmpty());

        /* no contiene elementos */
        as.clear();
        assertTrue(as.isEmpty());
    }

    @Test
    void addFirst() {
        assertEquals(10, as.size());
        assertEquals(0, as.get(0));
        as.addFirst(9);
        assertEquals(9, as.get(0));
        assertEquals(11, as.size());
    }

    @Test
    void addLast() {
        assertEquals(10, as.size());
        assertEquals(9, as.get(as.size() - 1));
        as.addLast(11);
        assertEquals(11, as.get(as.size() - 1));
        assertEquals(11, as.size());
    }

    @Test
    void add() {
        assertEquals(10, as.size());
        assertEquals(2, as.get(2));
        as.add(2, 11);
        assertEquals(11, as.get(2));
        assertEquals(11, as.size());
    }

    @Test
    void removeFirst() {
        /* vars */
        int rmVal;

        /* as */
        assertEquals(10, as.size());
        rmVal = as.removeFirst();
        assertEquals(9, as.size());
        assertEquals(0, rmVal);

        /* bs */
        assertEquals(1, bs.size());
        rmVal = bs.removeFirst();
        assertEquals(0, bs.size());
        assertEquals(0, rmVal);

        /* zs */
        assertEquals(0, zs.size());
        zs.removeFirst(); // FIXME :: NPE
        assertEquals(0, zs.size());
    }

    @Test
    void removeLast() {
        /* vars */
        int rmVal;

        /* as */
        assertEquals(10, as.size());
        rmVal = as.removeLast();
        assertEquals(9, as.size());
        assertEquals(9, rmVal);

        /* bs */
        assertEquals(1, bs.size());
        rmVal = bs.removeLast();
        assertEquals(0, bs.size());
        assertEquals(0, rmVal);

        /* zs */
        assertEquals(0, zs.size());
        zs.removeLast(); // FIXME :: NPE
        assertEquals(0, zs.size());
    }

    @Test
    void remove() {
        assertEquals(10, as.size());
        final int rmVal = as.remove(3);
        assertEquals(9, as.size());
        assertEquals(3, rmVal);
    }

    @Test
    void get() {
        /* as */
        assertEquals(0, as.get(0));
        assertEquals(9, as.get(as.size() - 1));

        /* bs */
        assertEquals(0, bs.get(0));
    }

    @Test
    void set() {
        // TODO
    }

    @Test
    void keepOnly() {
        // TODO
    }

    @Test
    void reverse() {
        // TODO
    }

    @Test
    void insertAt() {
        // TODO
    }

    @Test
    void testToString() {
        /* as */
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", as.toString());
        as.removeLast();
        as.removeFirst();
        as.remove(3);
        as.add(1, 7);
        assertEquals("[1, 7, 2, 3, 5, 6, 7, 8]", as.toString());

        /* bs */
        assertEquals("[0]", bs.toString());
        bs.addLast(1);
        bs.removeLast();
        bs.addFirst(1);
        bs.removeFirst();
        assertEquals("[0]", bs.toString());

        /* zs */
        assertEquals("[]", zs.toString());
        zs.addLast(3);
        assertEquals("[3]", zs.toString());
    }

    @Test
    void iterator() {
        // TODO
    }

    @Test
    void testSortedInsert() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(3);
        xs.addLast(5);
        xs.addLast(8);
        xs.addLast(10);
        xs.addLast(12);

        xs.sortedInsert(13, Integer::compareTo);
        xs.sortedInsert(9, Integer::compareTo);
        xs.sortedInsert(0, Integer::compareTo);

        assertEquals("[0, 3, 5, 8, 9, 10, 12, 13]", xs.toString());
    }

    @Test
    void testIsReverseImperativo() {
        final List<Integer> xs = new DoublyLinkedList<>();
        xs.addLast(22);
        xs.addLast(2);
        xs.addLast(77);
        xs.addLast(6);
        xs.addLast(30);

        final List<Integer> ys = new DoublyLinkedList<>();
        ys.addLast(30);
        ys.addLast(6);
        ys.addLast(77);
        ys.addLast(2);
        ys.addLast(22);

        assertTrue(xs.isReverse(ys));
    }
}