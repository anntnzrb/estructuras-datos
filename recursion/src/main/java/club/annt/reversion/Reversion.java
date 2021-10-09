package club.annt.reversion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase con variedad de métodos que logran revertir el orden de elementos
 * de una lista.
 * <p>
 * Recursos:
 * - https://codippa.com/how-to-reverse-a-list-in-java/
 */
public class Reversion {
    /**
     * Función que recibe una lista y retorna la misma pero revertida,
     * versión imperativa.
     * <p>
     * NOTA: Ésta implementación no es la mejor solución en términos de
     * rendimiento, sin embargo es una de las mas simples.
     * Una versión alterna es usar la misma lista y realizar las
     * operaciones de agregar y eliminar elementos de la misma.
     *
     * @param xs  Lista a revertir
     * @param <T> Tipo de elemento de la lista
     * @return Lista revertida
     */
    static <T> List<T> revertirImperativa(final List<T> xs) {
        final List<T> ys = new ArrayList<T>();

        for (int i = xs.size() - 1; i >= 0; --i) {
            ys.add(xs.get(i));
        }

        return ys;
    }

    /**
     * Función que recibe una lista y retorna la misma pero revertida,
     * versión recursiva.
     *
     * @param xs  Lista a revertir
     * @param <T> Tipo de elemento de la lista
     */
    static <T> void revertirRecursiva(final List<T> xs) {
        if (xs.size() == 0) {
            return;
        }

        /*
         * 1. Se remueve el primer elemento de la lista
         *    (primero desde izquierda)
         * 2. Se llama nuevamente a este mismo método (recursión) pero sin
         *    el elemento eliminado anteriormente.
         * 3. Se agrega este elemento a la lista
         * 4. Repetir (hasta que la lista esté vacía)
         */
        final T e = xs.remove(0);            /* 1 */
        revertirRecursiva(xs);               /* 2 */
        xs.add(e);                           /* 3 */
        /* ... */                            /* 4 */
    }
}
