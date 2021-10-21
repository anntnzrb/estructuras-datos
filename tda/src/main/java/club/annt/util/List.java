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
     * Remueve todos los elementos del arreglo.
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

    void add(final int idx, final E e);

    E removeFirst();

    E removeLast();

    E remove(final int idx);

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
