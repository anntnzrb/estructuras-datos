package club.annt.util;

public interface List<E> {
    /**
     * Retorna el número de elementos en el arreglo.
     *
     * @return el número de elementos en el arreglo
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
     * Agrega el elemento especificado al principio del arreglo.
     *
     * @param e elemento a ser agregado al arreglo
     * @return {@code true} si se agregó satisfactoriamente
     */
    boolean addFirst(final E e);

    /**
     * Agrega el elemento especificado al final del arreglo.
     * <p>
     *
     * @param e elemento a ser agregado al arreglo
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
     * Elimina el primer elemento del arreglo.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     *
     * @return el elemento eliminado
     */
    E removeFirst();

    /**
     * Elimina el último elemento del arreglo.
     *
     * @return el elemento eliminado
     */
    E removeLast();

    /**
     * Remueve el elemento del arreglo en el índice indicado.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     *
     * @param idx el índice del elemento del arreglo a remover
     * @return el elemento que fue removido de la lista
     */
    E remove(final int idx);

    /**
     * Retorna el elemento de la colección a partir del índice indicado.
     *
     * @param idx índice del elemento a retornar
     * @return el elemento del arreglo en la posición indicada
     */
    E get(final int idx);

    E set(final int idx, final E e);

    /**
     * Modifica el arreglo dejando únicamente los elementos en el rango
     * proporcionado.
     *
     * @param from posición desde donde tomar los elementos del arreglo
     * @param to   posición hasta donde tomar los elementos del arreglo
     * @return {@code true} sí se realizó la operación correctamente
     */
    boolean keepOnly(final int from, final int to);

    void reverse();

    List<E> insertAt(final List<E> xs, final int idx);
}
