package club.annt.stack;

import org.junit.jupiter.api.Test;

import static club.annt.stack.Parentesis.checkParens;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParentesisTest {

    @Test
    void testCheckParens() {
        /* true */
        assertTrue(checkParens("(a+b)-2"));
        assertTrue(checkParens("((a+b))-2"));
        assertTrue(checkParens("a*b*c+(x/2)-81+(14*2)"));

        /* false */
        assertFalse(checkParens("((a+b)-2"));
        assertFalse(checkParens("((a+b)-2))"));
        assertFalse(checkParens("(a*b*c+(x/2)-81+(14*2)"));
    }
}