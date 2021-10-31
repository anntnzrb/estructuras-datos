package club.annt.stack;

import org.junit.jupiter.api.Test;

import static club.annt.stack.Notacion.*;
import static org.junit.jupiter.api.Assertions.*;

class NotacionTest {

    @Test
    void testInfixAPostfix() {
        assertEquals("AB+", infixAPostfix("A+B"));
        assertEquals("AB*C*D*", infixAPostfix("A*B*C*D"));
        assertEquals("AB*A/C+", infixAPostfix("A*B/A+C"));
        assertEquals("ABC*D/+", infixAPostfix("A+B*C/D"));
    }

    @Test
    void testEvalPostfixExpr() {
        assertEquals(20, evalPostfixExpr("45*").intValue());
        assertEquals(41, evalPostfixExpr("984*+").intValue());
    }
}