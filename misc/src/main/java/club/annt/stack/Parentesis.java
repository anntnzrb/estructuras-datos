package club.annt.stack;

import java.util.Stack;

public class Parentesis {
    /**
     * Verifica si la expresión dada contiene paréntesis balanceados.
     *
     * @param expr Expresión que contenga paréntesis
     * @return {@code true} si los paréntesis de la expresíon están balanceados
     */
    public static boolean checkParens(final String expr) {
        final Stack<Character> charStack = new Stack<>();

        /* recorrer por cada caracter del string */
        for (int idx = 0; idx < expr.length(); idx++) {
            final char caracter = expr.charAt(idx);
            if (caracter == '(') {
                charStack.push(caracter);
            } else if (caracter == ')') {
                /* StackUnderflow check */
                if (charStack.isEmpty()) {
                    return false;
                } else {
                    charStack.pop();
                }
            }
        }

        return charStack.isEmpty();
    }
}