package club.annt.stack;

import org.junit.jupiter.api.Test;

import static club.annt.stack.Postfix.*;
import static org.junit.jupiter.api.Assertions.*;

class PostfixTest {

    @Test
    void testInfixAPostfix() {
        assertEquals("AB+", infixAPostfix("A+B"));
        assertEquals("AB*C*D*", infixAPostfix("A*B*C*D"));
        assertEquals("AB*A/C+", infixAPostfix("A*B/A+C"));
        assertEquals("ABC*D/+", infixAPostfix("A+B*C/D"));
    }
}