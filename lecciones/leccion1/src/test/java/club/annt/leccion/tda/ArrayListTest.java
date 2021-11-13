package club.annt.leccion.tda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest {

    @Test
    void removerElementos() {
        /* ********************************************************************
         * Caso #1
         * ***************************************************************** */
        final List<String> xs = new ArrayList<>();
        xs.addLast("One");
        xs.addLast("Two");
        xs.addLast("Three");
        xs.addLast("Four");
        xs.addLast("Five");

        final List<Integer> intList1 = new ArrayList<>();
        intList1.addLast(3);
        intList1.addLast(1);
        intList1.addLast(4);

        final List<String> resXs = new ArrayList<>();
        resXs.addLast("One");
        resXs.addLast("Three");

        xs.removerElementos(intList1);
        assertEquals(resXs, xs);

        /* ********************************************************************
         * Caso #2
         * ***************************************************************** */

        final List<Integer> ys = new ArrayList<>();
        ys.addLast(10);
        ys.addLast(3);
        ys.addLast(25);
        ys.addLast(4);
        ys.addLast(8);

        final List<Integer> intList2 = new ArrayList<>();
        intList2.addLast(0);
        intList2.addLast(2);

        final List<Integer> resYs = new ArrayList<>();
        resYs.addLast(3);
        resYs.addLast(4);
        resYs.addLast(8);

        ys.removerElementos(intList2);
        assertEquals(resYs, ys);
    }
}