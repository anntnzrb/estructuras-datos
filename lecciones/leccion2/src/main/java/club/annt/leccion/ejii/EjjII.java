package club.annt.leccion.ejii;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public final class EjjII {
    /**
     * Map que representa la existencia de un camino directo entre pares de
     * ciudades.
     * <p>
     * De preferencia emplear la interfaz Map y usar HashMap concretamente al
     * instanciar.
     */
    static Queue<Distancia> lleganA(
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
}
