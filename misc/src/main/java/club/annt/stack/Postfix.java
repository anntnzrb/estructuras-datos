package club.annt.stack;

import java.util.Stack;

public class Postfix {

    /**
     * Convierte la expresión dada de notación in-fija a post-fija.
     *
     * @param expr Expresión en notación in-fija
     * @return la expresión transformada a notación post-fija
     */
    public static String infixAPostfix(final String expr) {
        final StringBuilder str = new StringBuilder();
        final Stack<Character> charStack = new Stack<>();

        for (int idx = 0; idx < expr.length(); idx++) {
            final char caracter = expr.charAt(idx);

            if (isOperador(caracter)) {
                if (!charStack.isEmpty()) {
                    if (getPrioridad(caracter)
                            <= getPrioridad(charStack.peek())) {
                        while (!charStack.isEmpty()
                                && !(getPrioridad(caracter)
                                > getPrioridad(charStack.peek()))) {
                            str.append(charStack.pop());
                        }
                    }
                }
                charStack.push(caracter);
            } else {
                str.append(caracter);
            }
        }
        while (!charStack.isEmpty()) {
            str.append(charStack.pop());
        }

        return str.toString();
    }

    private static boolean isOperando(final char c) {
        return !isOperador(c);
    }

    private static boolean isOperador(final char c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }

    private static byte getPrioridad(final char c) {
        switch (c) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
}
