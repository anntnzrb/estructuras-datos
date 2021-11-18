package club.annt.leccion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Revisar instrucciones en el archivo assets/l02-ej2.png
 */
final class EjII {
    /**
     * Map que representa la existencia de un camino directo entre pares de
     * ciudades.
     * <p>
     * De preferencia emplear la interfaz Map y usar HashMap concretamente al
     * instanciar.
     */
    private Map<String, Map<String, Integer>> caminos;

    private static Queue<Distancia> lleganA(
            final Map<String, Map<String, Integer>> caminos,
            final String ciudadDestino) {
        /* se pasa el Comparator al constructor de PQ, de forma explícita:
         * (d1, d2) -> d2.getDistancia() - d1.getDistancia()
         *
         * ó ...
         */
        final Queue<Distancia> distPQ =
                new PriorityQueue<>(Comparator.comparingInt(Distancia::getDistancia)
                                              .reversed());

        /*
         * iterar a través del HashMap (hay muchas maneras de hacer esto...)
         *
         * 1. filtrar únicamente ciudades que contengan la ciudadDestino
         * 2. por cada ciudad que contenga la ciudadDestino, crear una
         * instancia de la clase Distancia con la distancia entre ciudad
         * y ciudadDistancia.
         */
        caminos.keySet()
               .stream()
               .filter(ciudad -> caminos.get(ciudad).containsKey(ciudadDestino))
               .forEach(ciudad -> {
                   final int dist = caminos.get(ciudad).get(ciudadDestino);
                   distPQ.offer(new Distancia(ciudad, dist));
               });

        /* forma tradicional */
        // for (final String ciudad : caminos.keySet()) {
        //     if (caminos.get(ciudad).containsKey(ciudadDestino)) {
        //         final int dist = caminos.get(ciudad).get(ciudadDestino);
        //         distPQ.offer(new Distancia(ciudad, dist));
        //     }
        // }

        return distPQ;
    }

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