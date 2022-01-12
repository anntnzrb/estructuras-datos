package club.annt.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "ClassWithoutConstructor", "JUnitTestMethodWithNoAssertions"})
final class GraphALTest {
    private GraphAL<String, String> graph1;

    @BeforeEach
    void setUp() {
        graph1 = new GraphAL<>(true);
        graph1.add("D")
              .add("C")
              .add("B")
              .add("A")
              .add("H")
              .add("T")
              .add("R");

        graph1.connect("D", "B")
              .connect("D", "C")
              .connect("C", "R")
              .connect("R", "H")
              .connect("B", "H")
              .connect("H", "D")
              .connect("H", "A")
              .connect("H", "T");
    }

    @Test
    void disconnect() {
        graph1.disconnect("C", "R");
        graph1.printBFS();
    }

    @Test
    void remove() {
        graph1.remove("D");
        graph1.printDFS();
    }

    @Test
    void testBFS() {
        graph1.printBFS("D");
        System.out.println("-".repeat(79));
        graph1.printBFS();
    }

    @Test
    void testDFS() {
        graph1.printDFS("D");
        System.out.println("-".repeat(79));
        graph1.printDFS();
    }
}