package club.annt.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertWithoutMessage")
final class GraphAMTest {
    private GraphAM<Integer> graph1;

    @BeforeEach
    void setUp() {
        graph1 = new GraphAM<>();
        graph1.add(1)
              .add(2)
              .add(3)
              .add(1);
    }

    @Test
    void conexiones() {
        /* ********************************************************************
         * conectar
         ******************************************************************* */
        assertFalse(graph1.isAdjancent(2, 3));
        assertFalse(graph1.isAdjancent(1, 2));
        assertFalse(graph1.isAdjancent(1, 3));

        graph1.connect(2, 3);
        graph1.connect(1, 2);
        graph1.connect(1, 3);

        assertTrue(graph1.isAdjancent(2, 3));
        assertTrue(graph1.isAdjancent(1, 2));
        assertTrue(graph1.isAdjancent(1, 3));

        /* ********************************************************************
         * desconectar
         ******************************************************************* */
        graph1.disconnect(2, 3);
        graph1.disconnect(1, 2);
        graph1.disconnect(1, 3);

        assertFalse(graph1.isAdjancent(2, 3));
        assertFalse(graph1.isAdjancent(1, 2));
        assertFalse(graph1.isAdjancent(1, 3));
    }

    @Test
    void remove() {
        graph1.connect(2, 3);
        graph1.connect(1, 2);
        graph1.connect(1, 3);
        assertTrue(graph1.isAdjancent(1, 2));

        graph1.remove(1);
        assertFalse(graph1.isAdjancent(1, 2));
    }

    @Test
    void inDegree() {
        graph1.connect(2, 3);
        graph1.connect(1, 2);
        graph1.connect(1, 3);

        assertEquals(2, graph1.inDegree(1));
        assertEquals(2, graph1.inDegree(2));
        assertEquals(2, graph1.inDegree(3));
    }

    @Test
    void outDegree() {
        graph1.connect(2, 3);
        graph1.connect(1, 2);
        graph1.connect(1, 3);

        assertEquals(2, graph1.outDegree(1));
        assertEquals(2, graph1.outDegree(2));
        assertEquals(2, graph1.outDegree(3));
    }
}