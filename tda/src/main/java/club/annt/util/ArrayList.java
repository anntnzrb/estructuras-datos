package club.annt.util;

public class ArrayList<E> implements List<E> {

    /**
     * Capacidad inicial del arreglo por defecto.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Buffer del arreglo en donde se almacenan los elementos del ArrayList.
     */
    private final E[] elems;
    /**
     * Cantidad de elementos presentes en el arreglo (no su capacidad).
     */
    private int size = 0;

    public ArrayList() {
        elems = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean addFirst(E e) {
        ++size;

        // TODO

        return false;
    }

    @Override
    public boolean addLast(E e) {
        ++size;

        // TODO

        return false;
    }

    @Override
    public void add(int idx, E e) {
        checkRange(idx);
        ++size;

        // TODO
    }

    @Override
    public E remove(int idx) {
        checkRange(idx);
        --size;

        // TODO

        return null;
    }

    @Override
    public E get(int idx) {
        checkRange(idx);

        return elems[idx];
    }

    @Override
    public E set(int idx, E e) {
        checkRange(idx);

        final E oldVal = elems[idx];
        elems[idx] = e;

        return oldVal;
    }

    /**
     * Verifica si el índice se encuentra en el rango del arreglo.
     * Este método se emplea previamente al acceso de un arreglo.
     *
     * @param idx índice a verificar
     */
    private void checkRange(int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
