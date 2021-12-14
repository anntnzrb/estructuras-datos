package club.annt.leccion.tda;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EjIII {
    public static List<Integer> sumarNumerosGrandes(final List<Integer> xs,
                                                    final List<Integer> ys) {
        /* SBs para almacenar String de números */
        final StringBuilder strB1 = new StringBuilder();
        final StringBuilder strB2 = new StringBuilder();

        /* lista resultante */
        final List<Integer> zs = new ArrayList<>();

        /* por cada lista, crear un string con el valor númerico de la misma */
        xs.forEach(strB1::append);
        ys.forEach(strB2::append);

        /* suma de enteros */
        final int sum = Integer.parseInt(strB1.toString())
                + Integer.parseInt(strB2.toString());

        final String str = String.valueOf(sum);
        IntStream.range(0, str.length())
                 .forEachOrdered(i -> zs.addLast(Integer.parseInt(String.valueOf(str.charAt(i)))));

        return zs;
    }

    @Test
    void testSumarNumerosGrandes() {
        /* ********************************************************************
         * Caso #1
         * ***************************************************************** */
        final List<Integer> as = new ArrayList<>();
        as.addLast(7);
        as.addLast(5);
        as.addLast(9);
        as.addLast(4);
        as.addLast(8);

        final List<Integer> bs = new ArrayList<>();
        bs.addLast(8);
        bs.addLast(7);

        final List<Integer> cs = sumarNumerosGrandes(as, bs);

        final List<Integer> expected1 = new ArrayList<>();
        expected1.addLast(7);
        expected1.addLast(6);
        expected1.addLast(0);
        expected1.addLast(3);
        expected1.addLast(5);

        assertEquals(expected1, cs);

        /* ********************************************************************
         * Caso #2
         * ***************************************************************** */
        final List<Integer> xs = new ArrayList<>();
        xs.addLast(3);
        xs.addLast(1);
        xs.addLast(4);

        final List<Integer> ys = new ArrayList<>();
        ys.addLast(1);
        ys.addLast(1);
        ys.addLast(1);

        final List<Integer> zs = sumarNumerosGrandes(xs, ys);

        final List<Integer> expected2 = new ArrayList<>();
        expected2.addLast(4);
        expected2.addLast(2);
        expected2.addLast(5);

        assertEquals(expected2, zs);
    }
}