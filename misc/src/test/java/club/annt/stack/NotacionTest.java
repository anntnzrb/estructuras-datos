package club.annt.stack;

import org.junit.jupiter.api.Test;

import static club.annt.stack.Notacion.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotacionTest {

    @Test
    void testInfixAPostfix() {
        assertEquals("A B +", infixAPostfix("A + B"));
        assertEquals("21 13 16 * + 18 -", infixAPostfix("21 + 13 * 16 - 18"));
        assertEquals("2 3 4 * +", infixAPostfix("2 + 3 * 4"));
        assertEquals("A B * 5 +", infixAPostfix("A * B + 5"));
        assertEquals("A B * C /", infixAPostfix("A * B / C"));
    }

    @Test
    void testEvalPostfixExpr() {
        assertEquals(20, evalPostfixExpr("4 5 *").intValue());
        assertEquals(41, evalPostfixExpr("9 8 4 * +").intValue());
        assertEquals(29, evalPostfixExpr("13 16 +").intValue());
        assertEquals(1372, evalPostfixExpr("15 34 + 28 *").intValue());
    }

    @Test
    void testInfixAPostfixParens() {
        assertEquals("Expresión inválida.",
                infixAPostfixParens("((A-(B+C))*D^(E+F)"));
        assertEquals("ABC+-DEF+^*",
                infixAPostfixParens("((A-(B+C))*D^(E+F))"));
    }
}