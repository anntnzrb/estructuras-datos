package club.annt.a.ejv;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static club.annt.a.ejv.EjV.sortStack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EjVTest {

    @Test
    void testSortStack() {
        final Deque<Integer> dq = new ArrayDeque<>();
        dq.push(42);
        dq.push(10);
        dq.push(56);
        dq.push(5);

        assertEquals("[5, 10, 42, 56]",
                     sortStack(dq, Integer::compareTo).toString());
    }
}