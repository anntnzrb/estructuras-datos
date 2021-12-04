package club.annt.util;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings({"NonBooleanMethodNameMayNotStartWithQuestion",
        "BooleanMethodNameMustStartWithQuestion", "unused"})
public interface List<E> extends Iterable<E> {
    /**
     * Retorna el número de elementos en la colección.
     *
     * @return el número de elementos en la colección
     */
    int size();

    /**
     * Verifica si la colección no contiene elementos.
     *
     * @return {@code true} si la colección no contiene elementos
     */
    boolean isEmpty();

    /**
     * Remueve todos los elementos de la colección.
     */
    void clear();

    /**
     * Agrega el elemento especificado al principio de la colección.
     *
     * @param e elemento a ser agregado a la colección
     * @return {@code true} si se agregó satisfactoriamente
     */
    boolean addFirst(final E e);

    /**
     * Agrega el elemento especificado al final de la colección.
     * <p>
     *
     * @param e elemento a ser agregado a la colección
     * @return {@code true} si se agregó satisfactoriamente
     */
    boolean addLast(final E e);

    /**
     * Inserta el elemento dado en la posición indicada. Desplaza hacia la
     * derecha los elementos existentes.
     *
     * @param idx indice al cual el elemento será insertado
     * @param e   elemento a ser insertado
     */
    void add(final int idx, final E e);

    /**
     * Elimina el primer elemento de la colección.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     *
     * @return el elemento eliminado
     */
    E removeFirst();

    /**
     * Elimina el último elemento de la colección.
     *
     * @return el elemento eliminado
     */
    E removeLast();

    /**
     * Remueve el elemento de la colección en el índice indicado.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     *
     * @param idx el índice del elemento de la colección a remover
     * @return el elemento que fue removido de la lista
     */
    E remove(final int idx);

    /**
     * Retorna el elemento de la colección a partir del índice indicado.
     *
     * @param idx índice del elemento a retornar
     * @return el elemento de la colección en la posición indicada
     */
    E get(final int idx);

    E set(final int idx, final E e);

    /**
     * Modifica la colección dejando únicamente los elementos en el rango
     * proporcionado.
     * <p>
     * NOTA: Este método es parte de un ejercicio de exámen exclusivamente.
     *
     * @param from posición desde donde tomar los elementos de la colección
     * @param to   posición hasta donde tomar los elementos de la colección
     * @return {@code true} sí se realizó la operación correctamente
     */
    boolean keepOnly(final int from, final int to);

    void reverse();

    List<E> insertAt(final List<E> xs, final int idx);

    /**
     * Agrega un elemento a la colección de forma ordenada
     * (a partir de un {@link Comparator}).
     * <p>
     * NOTA: Este método es parte de un ejercicio de exámen exclusivamente.
     *
     * @param e   elemento a agregar
     * @param cmp {@link Comparator}
     * @return {@code true} sí se realizó la operación correctamente
     */
    boolean sortedInsert(final E e, final Comparator<E> cmp);

    /**
     * Iterador que puede partir de un inicio y saltarse elementos.
     * <p>
     * NOTA: Este método es parte de un ejercicio de exámen exclusivamente.
     *
     * @param start elemento donde empezar a iterar
     * @param step  salto entre elementos
     * @return @{@link Iterator}
     */
    Iterator<E> iteratorStep(final int start, final int step);

    /**
     * Verifica si la colección es el reverso de la colección pasada por
     * parámetro.
     * <p>
     * NOTA: Este método es parte de un ejercicio de exámen exclusivamente.
     *
     * @param xs colección a ser procesada
     * @return {@code true} sí se realizó la operación correctamente
     */
    boolean isReverse(final List<E> xs);
}
