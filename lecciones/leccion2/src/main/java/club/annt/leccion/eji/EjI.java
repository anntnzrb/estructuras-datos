package club.annt.leccion.eji;

import java.util.Comparator;
import java.util.Stack;

public final class EjI {
    public static <T> Stack<T> combineOrderedStacks(final Stack<T> s1,
                                                    final Stack<T> s2,
                                                    final Comparator<T> cmp) {
        final Stack<T> s3 = new Stack<>();
        /* Stack temporal para unir todo */
        final Stack<T> tmp = new Stack<>();

        /* iterar hasta que alguno de los Stacks esté vacíos */
        while (!s1.isEmpty() && !s2.isEmpty()) {
            /* comparar e ingresar en tmp el menor número primero */
            if (cmp.compare(s1.peek(), s2.peek()) < 0) {
                tmp.push(s1.pop());
            } else {
                tmp.push(s2.pop());
            }
        }

        /* por diferencia de tamaño, algún Stack no estará vacío */
        while (!s1.isEmpty()) {
            tmp.push(s1.pop());
        }

        while (!s2.isEmpty()) {
            tmp.push(s2.pop());
        }

        /* ingresarlo en el Stack final y arreglar su orden */
        while (!tmp.isEmpty()) {
            s3.push(tmp.pop());
        }

        return s3;
    }
}
