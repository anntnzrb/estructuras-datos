package club.annt.ejercicios;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static club.annt.ejercicios.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class TestEjercicios {
    @Test
    void testContarOcurrencias() {
        final List<Integer> xs = Arrays.asList(1, 2, 2, 3, 4, 2, 2);
        final List<Integer> ys = new ArrayList<>();

        // xs
        assertEquals(4, contarOcurrencias(xs, 2));
        assertEquals(0, contarOcurrencias(xs, 5));
        assertEquals(1, contarOcurrencias(xs, 1));
        assertEquals(0, contarOcurrencias(xs, 29));

        // ys
        assertEquals(0, contarOcurrencias(ys, 11));
    }

    @Test
    void testPotencia() {
        // exp > 0
        assertEquals(8, potencia(2, 3));
        assertEquals(177147, potencia(3, 11));
        assertEquals(14, potencia(14, 1));

        // exp == 0
        assertEquals(1, potencia(999999, 0));

        // exp < 0
        assertEquals(0.125, potencia(2, -3));
    }

    // @Test
    void testActualizarArrreglo() {
        // TODO :: comparar arrays
    }

    @Test
    void testBuscarNum() {
        final List<Integer> xs = Arrays.asList(1, 2, 3, 4);

        // V1 ==> O(n)
        assertTrue(buscarNumV1(xs, 1));
        assertTrue(buscarNumV1(xs, 3));
        assertFalse(buscarNumV1(xs, 5));
        assertFalse(buscarNumV1(xs, 0));

        // V2 ==> O(n/2)
        assertTrue(buscarNumV2(xs, 1));
        assertTrue(buscarNumV2(xs, 3));
        assertFalse(buscarNumV2(xs, 5));
        assertFalse(buscarNumV2(xs, 0));
    }
}