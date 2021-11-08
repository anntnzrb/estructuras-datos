package club.annt.stack;

import java.util.Stack;

public class Notacion {
    /**
     * Convierte la expresión dada de notación in-fija a post-fija.
     * NOTA: Ésta versión no acepta paréntesis.
     *
     * @param expr Expresión en notación in-fija
     * @return la expresión transformada a notación post-fija
     */
    public static String infixAPostfix(final String expr) {
        final StringBuilder strBuff = new StringBuilder();
        final Stack<String> strStack = new Stack<>();

        /* separar expresión por elemento (sea operando u operador) e iterar */
        final String[] exprElems = expr.split(" ");
        for (final String elem : exprElems) {
            /*
             * sea el elemento un operador, mientras que el stack no esté
             * vacío y la prioridad del operador sea menor o igual al último
             * operador del stack, removerlo del stack y agregarlo a la
             * expresión final.
             */
            if (isOperador(elem.charAt(0))) {
                while (!strStack.isEmpty()
                        && getPrioridad(elem.charAt(0))
                        <= getPrioridad(strStack.peek().charAt(0))) {
                    strBuff.append(strStack.pop()).append(" ");
                }
                /* si el operador es de mayor prioridad o el stack esté vació,
                 * agregarlo al stack.
                 */
                strStack.push(elem);
                /*
                 * por otro lado, si no es un operador, es un operando,
                 * simplemente agregarlo a la expresión final.
                 */
            } else {
                strBuff.append(elem).append(" ");
            }
        }

        /* vaciar stack, cuando la cantidad de elementos es distinta de 1,
         * agregar un espacio al lado derecho
         */
        while (!strStack.isEmpty()) {
            if (strStack.size() != 1) {
                strBuff.append(strStack.pop()).append(" ");
            } else {
                strBuff.append(strStack.pop());
            }
        }

        return strBuff.toString();
    }

    /**
     * Convierte la expresión dada de notación in-fija a post-fija.
     *
     * @param expr Expresión en notación in-fija
     * @return la expresión transformada a notación post-fija
     */
    public static String infixAPostfixParens(final String expr) {
        final StringBuilder strBuff = new StringBuilder();
        final Stack<Character> chStack = new Stack<>();

        for (int i = 0; i < expr.length(); ++i) {
            final char ch = expr.charAt(i);

            /*
             * sea el ch un operador, mientras que el stack no esté
             * vacío y la prioridad del operador sea menor o igual al último
             * operador del stack, removerlo del stack y agregarlo a la
             * expresión final.
             */
            if (isOperador(ch)) {
                while (!chStack.isEmpty() && getPrioridad(ch)
                        <= getPrioridad(chStack.peek())) {

                    strBuff.append(chStack.pop());
                }

                /* si el operador es de mayor prioridad o el stack esté vació,
                 * agregarlo al stack.
                 */
                chStack.push(ch);
            }

            /* sea ch un '(', agregarlo al stack */
            else if (ch == '(') { chStack.push(ch); }

            /*
             * sea ch un '(', agregarlo al stack, remover elementos del stack
             * hasta encontrar su par.
             */
            else if (ch == ')') {
                while (!chStack.isEmpty() && chStack.peek() != '(') {
                    strBuff.append(chStack.pop());
                }
                chStack.pop();

                /* si no es ninguno de los anteriores, entonces es operando */
            } else {
                strBuff.append(ch);
            }
        }

        /* vaciar stack con los elementos que quedan y verificar expresión */
        while (!chStack.isEmpty()) {
            /* si se encuentra un '(' es porque la expresión es inválida */
            if (chStack.peek() == '(') { return "Expresión inválida."; }
            strBuff.append(chStack.pop());
        }

        return strBuff.toString();
    }

    /**
     * Evalúa la expresión pasada, esta expresión debe estar en notación
     * post-fija.
     *
     * @param expr Expresión en notación post-fija a evaluar
     * @return resultado numérico al evaluar la expresión
     */
    public static Number evalPostfixExpr(final String expr) {
        final Stack<Number> numStack = new Stack<>();

        /* separar expresión por elemento (sea operando u operador) e iterar */
        final String[] exprElems = expr.split(" ");
        for (final String elem : exprElems) {
            /* agregar al stack si no es un operador */
            if (!isOperador(elem.charAt(0))) {
                numStack.push(Integer.parseInt(elem));
                /* evaluar los dos últimos operandos con el operador actual */
            } else {
                numStack.push(evalOp(elem.charAt(0), numStack.pop(),
                        numStack.pop()));
            }
        }

        /* peek() y pop() sirven igualmente pero se vacía para ayudar al GC */
        return numStack.pop();
    }

    private static boolean isOperador(final char c) {
        return c == '^'
                || c == '*'
                || c == '/'
                || c == '+'
                || c == '-';
    }

    private static byte getPrioridad(final char str) {
        switch (str) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
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
}
