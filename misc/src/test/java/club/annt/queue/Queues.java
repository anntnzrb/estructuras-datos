package club.annt.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class Queues {
    Repartidor rep1;
    Repartidor rep2;
    Repartidor rep3;

    @BeforeEach
    void setUp() {
        rep1 = new Repartidor("John");
        rep2 = new Repartidor("Mariana");
        rep3 = new Repartidor("Carlos");

        /* órdenes */
        rep1.addOrder(new Orden("Paul", "Termo", 1));
        rep1.addOrder(new Orden("Luis", "Camisa", 5));
        rep1.addOrder(new Orden("María", "Flores", 50));

        rep2.addOrder(new Orden("Sonia", "Hojas", 1000));

        rep3.addOrder(new Orden("Raúl", "Teléfono", 4));
        rep3.addOrder(new Orden("María", "Mantel", 1));
    }

    @Test
    public void ordenNaturalPQ() {
        final Queue<Repartidor> repPQ = new PriorityQueue<>();

        repPQ.offer(rep1);
        repPQ.offer(rep2);
        repPQ.offer(rep3);

        while (!repPQ.isEmpty()) {
            System.out.println(repPQ.poll());
        }
    }

    @Test
    public void ordenCantidadItemsEnOrdenPQ() {
        /* PQ ordenado ascendentemente a partir de la cantidad de artículos
         * por orden
         */
        final Queue<Repartidor> repPQ =
                new PriorityQueue<>(Comparator.comparingInt(rep -> rep.getOrdenes()
                                                                      .stream()
                                                                      .mapToInt(Orden::getCantidad)
                                                                      .sum()));

        repPQ.offer(rep1);
        repPQ.offer(rep2);
        repPQ.offer(rep3);

        while (!repPQ.isEmpty()) {
            System.out.println(repPQ.poll());
        }
    }

    @Test
    void qux() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("foo", 1);
        map.put("bar", 2);

    }
}