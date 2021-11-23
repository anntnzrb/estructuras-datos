package club.annt.leccion.eji;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static club.annt.leccion.eji.EjI.combineOrderedStacks;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EjITest {

    @Test
    void testCombineOrderedStacks() {
        final Stack<Integer> s1 = new Stack<>();
        s1.addAll(List.of(80, 40, 10));

        final Stack<Integer> s2 = new Stack<>();
        s2.addAll(List.of(90, 50, 20, 5));

        final Stack<Integer> s3 =
                combineOrderedStacks(s1, s2, Integer::compareTo);

        assertEquals("[90, 80, 50, 40, 20, 10, 5]", s3.toString());
    }
}