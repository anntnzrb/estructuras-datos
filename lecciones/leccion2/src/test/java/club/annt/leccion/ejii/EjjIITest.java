package club.annt.leccion.ejii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static club.annt.leccion.ejii.EjjII.lleganA;

class EjjIITest {
    private Map<String, Map<String, Integer>> caminos;

    @BeforeEach
    void setUp() {
        caminos = new HashMap<>();

        /* Esmeraldas */
        final Map<String, Integer> camEsmeraldas = new HashMap<>();
        camEsmeraldas.put("Pedernales", 190);
        camEsmeraldas.put("Muisne", 83);
        camEsmeraldas.put("Macará", 1);

        /* Muisne */
        final Map<String, Integer> camMuisne = new HashMap<>();
        camMuisne.put("Quevedo", 339);
        camMuisne.put("Pedernales", 121);

        /* Muisne */
        final Map<String, Integer> camAmbato = new HashMap<>();
        camAmbato.put("Azogues", 280);
        camAmbato.put("Babahoyo", 212);
        camAmbato.put("Pedernales", 318);

        /* finalmente agregar al Map caminos */
        caminos.put("Esmeraldas", camEsmeraldas);
        caminos.put("Muisne", camMuisne);
        caminos.put("Ambato", camAmbato);
    }

    @Test
    void res() {
        final Queue<Distancia> distPQ1 = lleganA(caminos, "Pedernales");

        /* no se puede testear con assertEquals() ya que toString del Queue
         * no respetará su orden.
         */
        while (!distPQ1.isEmpty()) {
            System.out.println(distPQ1.poll());
        }
    }

    @Test
    void mostrarMap() {
        System.out.println(caminos);
    }

}