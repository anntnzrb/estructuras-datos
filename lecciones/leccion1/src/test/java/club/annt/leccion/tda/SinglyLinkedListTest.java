package club.annt.leccion.tda;

import club.annt.leccion.Persona;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SinglyLinkedListTest {

    @Test
    void insertarOrdenado() {
        /* ********************************************************************
         * Caso #1
         * ***************************************************************** */
        List<Integer> xs = new SinglyLinkedList<>();

        final Comparator<Integer> cmp1 = Integer::compareTo;

        xs.insertarOrdenado(7, cmp1);
        xs.insertarOrdenado(2, cmp1);
        xs.insertarOrdenado(5, cmp1);
        xs.insertarOrdenado(0, cmp1);

        xs.insertarOrdenado(6, cmp1);

        assertEquals("[0, 2, 5, 6, 7]", xs.toString());

        /* ********************************************************************
         * Caso #2
         * ***************************************************************** */

        /* lista y comparador por nombre */
        List<Persona> ys = new SinglyLinkedList<>();
        final Comparator<Persona> cmp2 = Comparator.comparing(Persona::getNombre);

        ys.insertarOrdenado(new Persona("Raquel", 40), cmp2);
        ys.insertarOrdenado(new Persona("Isabel", 48), cmp2);
        ys.insertarOrdenado(new Persona("Felix", 42), cmp2);

        assertEquals("[{Felix, 42}, {Isabel, 48}, {Raquel, 40}]",
                ys.toString());

        /* lista y comparador por edad */
        List<Persona> zs = new SinglyLinkedList<>();
        final Comparator<Persona> cmp3 = Comparator.comparingInt(Persona::getEdad);

        zs.insertarOrdenado(new Persona("Raquel", 40), cmp3);
        zs.insertarOrdenado(new Persona("Isabel", 48), cmp3);
        zs.insertarOrdenado(new Persona("Felix", 42), cmp3);

        assertEquals("[{Raquel, 40}, {Felix, 42}, {Isabel, 48}]",
                zs.toString());
    }
}