package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ClassWithoutConstructor", "AssertWithoutMessage", "ClassHasNoToStringMethod"})
final class HeapTest {
    private Heap<String> pq0;
    private Heap<Integer> pq1;
    private Heap<Integer> pq2;

    @BeforeEach
    void setUp() {
        /* *********************************************************************
         * Heap #0
         ******************************************************************** */
        pq0 = new Heap<>(String::compareTo);

        /* ********************************************************************
         * Heap #1
         ******************************************************************* */
        pq1 = new Heap<>();
        pq1.offer(6);
        pq1.offer(21);
        pq1.offer(1);
        pq1.offer(2);
        pq1.offer(64);
        pq1.offer(-14);
        pq1.offer(7);
        pq1.offer(4);

        /* ********************************************************************
         * Heap #2
         ******************************************************************* */
        pq2 = new Heap<>(Integer::compareTo, true);
        pq2.offer(-7);
        pq2.offer(-111);
        pq2.offer(11);
        pq2.offer(-121);
        pq2.offer(-151);
        pq2.offer(100);
    }

    @Test
    void isEmpty() {
        assertTrue(pq0.isEmpty());
        assertFalse(pq1.isEmpty());
    }

    @Test
    void peek() {
        /* Heap #1 */
        assertEquals(-14, pq1.peek());

        /* Heap #2 */
        assertEquals(100, pq2.peek());
    }

    @Test
    void clear() {
        assertFalse(pq1.isEmpty());
        pq1.clear();
        assertTrue(pq1.isEmpty());
    }

    @Test
    void poll() {
        /* Heap #1 */
        while (!pq1.isEmpty()) { System.out.println(pq1.poll()); }

        System.out.println("-".repeat(79));

        /* Heap #2 */
        while (!pq2.isEmpty()) { System.out.println(pq2.poll()); }
    }
}