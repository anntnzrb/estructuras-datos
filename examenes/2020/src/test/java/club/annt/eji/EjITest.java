package club.annt.eji;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static club.annt.eji.EjI.*;
import static org.junit.jupiter.api.Assertions.*;

class EjITest {

    @Test
    void testGetStringsShorterThan() {
        final List<String> xs = new LinkedList<>();
        xs.add("123");
        xs.add("12");
        xs.add("1234");
        xs.add("1");
        xs.add("abcdef");
        xs.add("abc");

        final List<String> ys = new LinkedList<>();
        assertEquals("[]", getStringsShorterThan(ys, 3).toString());

        assertEquals("[12, 1]",
                     getStringsShorterThan(xs, 3).toString());
    }
}