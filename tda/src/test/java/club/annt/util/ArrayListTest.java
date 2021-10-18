package club.annt.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void testKeepOnly() {
        /*
        Implemente el método keepOnly (int from, int to) de la clase ArrayList. El
        método debe modificar al ArrayList que lo invoca manteniendo únicamente a
        los elementos ubicados en las posiciones dentro del rango [from, to]. Es
        decir, si from es 2 y to es 7, el método keepOnly mantendrá en el ArrayList
        a todos los elementos ubicados entre los índices 2 y 7 (es decir, desde el
        segundo al séptimo elemento).

        A continuación, se muestran dos ejemplos del uso de este método para un
        ArrayList de enteros y otro de Strings:

        ArrayList: [9, 3, 4, 5, 6, 5, 6, 2, 4, 6]
        ArrayList: [6, 5, 6, 2, 6, 1 , 6, 1, 4, 9]

        keepOnly(1, 3) mantiene únicamente los elementos [9, 3, 4]
        keepOnly(3, 8) mantiene los elementos [4, 5, 6, 5, 6, 2]

        ArrayList: [“manzana”, “naranja”, “banana”, “uva”]

        keepOnly(2, 4) mantiene los elementos [ “naranja”, “banana”, “uva”]
        keepOnly(1, 3) mantiene los elementos [“manzana”, “naranja”]

        Usted debe validar que los índices de entrada que se envían al método sean válidos.
        Por ejemplo, las llamadas keepOnly(-1, 3) y keepOnly(3, 183) son inválidas
        para cualquiera de los dos ArrayLists usados arriba.
    */
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(9);
        xs.addLast(3);
        xs.addLast(4);
        xs.addLast(5);
        xs.addLast(6);
        xs.addLast(2);
        xs.addLast(4);
        xs.addLast(6);

        final List<Integer> ys = new ArrayList<>();
        ys.addLast(6);
        ys.addLast(5);
        ys.addLast(6);
        ys.addLast(2);
        ys.addLast(6);
        ys.addLast(1);
        ys.addLast(6);
        ys.addLast(1);
        ys.addLast(4);
        ys.addLast(9);

        assertTrue(xs.keepOnly(1, 3));
        assertFalse(ys.keepOnly(3, 1));
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