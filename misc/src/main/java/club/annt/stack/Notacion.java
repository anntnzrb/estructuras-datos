package club.annt.stack;

import java.util.Stack;

public class Notacion {

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

    public static Number evalPostfixExpr(final String expr) {
        final Stack<Number> numStack = new Stack<>();

        for (int idx = 0; idx < expr.length(); idx++) {
            final char caracter = expr.charAt(idx);

            if (isOperando(caracter)) {
                /* agregar al stack si no es un operador */
                numStack.push(Character.getNumericValue(caracter));
            } else {
                /* evaluar los dos últimos operandos con el operador actual */
                numStack.push(evalOp(caracter, numStack.pop(),
                        numStack.pop()));
            }
        }

        /* peek() y pop() sirven igualmente pero se vacía para ayudar al GC */
        return numStack.pop();
    }

    private static Number evalOp(final char op, final Number x,
                                 final Number y) {
        switch (op) {
            case '^':
                return Math.pow(x.floatValue(), y.floatValue());
            case '*':
                return x.floatValue() * y.floatValue();
            case '/':
                return x.floatValue() / y.floatValue();
            case '+':
                return x.floatValue() + y.floatValue();
            case '-':
                return x.floatValue() - y.floatValue();
            default:
                return 0;
        }
    }

    private static boolean isOperando(final char c) {
        return !isOperador(c);
    }

    private static boolean isOperador(final char c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }

    private static byte getPrioridad(final char c) {
        switch (c) {
            case '^':
                return 3;
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
