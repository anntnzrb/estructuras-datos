package club.annt.paoi.ejiv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuoTest {

    Duo<Integer> duo1;

    @BeforeEach
    void setUp() {
        duo1 = new Duo<>();
    }

    @Test
    void testAll() {
        duo1.addTo(23, 1, Integer::compareTo);
        duo1.addTo(45, 1, Integer::compareTo);
        duo1.addTo(56, 2, Integer::compareTo);
        duo1.addTo(56, 2, Integer::compareTo);

        assertEquals("[]", duo1.getDuplicates(Integer::compareTo).toString());

        duo1.addTo(23, 2, Integer::compareTo);
        duo1.addTo(45, 2, Integer::compareTo);
        assertEquals("[23, 45]",
                     duo1.getDuplicates(Integer::compareTo).toString());
        duo1.deleteFrom(56, 2, Integer::compareTo);
        duo1.deleteFrom(23, 1, Integer::compareTo);
        assertEquals("[45]",
                     duo1.getDuplicates(Integer::compareTo).toString());

        assertEquals("[45] y [23, 45]",
                     duo1.toString());
    }
}