package club.annt.util;

public class ArrayList<E> implements List<E> {
    /**
     * Capacidad inicial del arreglo por defecto.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Buffer del arreglo en donde se almacenan los elementos del ArrayList.
     */
    private E[] elems;
    /**
     * Cantidad de elementos presentes en el arreglo (no su capacidad).
     */
    private int size;

    public ArrayList() {
        elems = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Retorna el número de elementos en el arreglo.
     *
     * @return el número de elementos en el arreglo
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Verifica si el arreglo no contiene elementos.
     *
     * @return <tt>true</tt> si el arreglo no contiene elementos
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remueve todos los elementos del arreglo.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elems[i] = null;
        }

        /* alternative usando streams */
        //IntStream.range(0, size).forEach(i -> elems[i] = null);

        /* alternative usando Arrays.fill */
        //Arrays.fill(elems, 0);

        size = 0;
    }

    /**
     * Verifica si el índice se encuentra en el rango del arreglo.
     * Este método se emplea previamente al acceso de un arreglo.
     *
     * @param idx índice a verificar
     */
    private void checkRange(int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException(idx);
        }
    }

    /**
     * Verifica si el arreglo está lleno.
     *
     * @return <tt>true</tt> si el arreglo está lleno
     */
    private boolean isFull() {
        return elems.length == size;
    }

    /**
     * Aumenta la capacidad del arreglo, multiplica su capacidad 1.5 veces.
     */
    private void grow() {
        final int newCap = size + (size >> 1);
        final E[] tmpArr = (E[]) new Object[newCap];
        //noinspection ManualArrayCopy
        for (int idx = 0; idx < size; ++idx) {
            tmpArr[idx] = elems[idx];
        }
        elems = tmpArr;

        // FIXME :: WTF?????????????????????????????????
        //size = newCap;

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, tmpArr, 0, size);
    }

    /**
     * Agrega el elemento especificado al principio del arreglo.
     * <p>
     * Complejidad: O(n)
     *
     * @param e elemento a ser agregado al arreglo
     * @return <tt>true</tt> si se agregó satisfactoriamente
     */
    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elems[size++] = e;
            return true;
        } else if (isFull()) {
            grow();
        }

        /* desplazar los nodos originales hacia la derecha */
        //noinspection ManualArrayCopy
        for (int idx = size - 1; idx >= 0; --idx) {
            elems[idx + 1] = elems[idx];
        }

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, elems, 1, size - 1 + 1);

        /* Insertar el elemento en el primer índice & actualizar tamaño */
        elems[0] = e;
        ++size;

        return true;
    }

    /**
     * Agrega el elemento especificado al final del arreglo.
     * <p>
     * Complejidad: O(1)
     *
     * @param e elemento a ser agregado al arreglo
     * @return <tt>true</tt> si se agregó satisfactoriamente
     */
    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        } else if (isFull()) {
            grow();
        }

        elems[size++] = e;

        return true;
    }

    /**
     * Inserta el elemento dado en la posición indicada. Desplaza hacia la
     * derecha los elementos existentes.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx indice al cual el elemento será insertado
     * @param e   elemento a ser insertado
     */
    @Override
    public void add(int idx, E e) {
        checkRange(idx);

        if (isFull()) {
            grow();
        }

        ++size;
        /* desplazar los nodos originales hacia la derecha */
        for (int i = idx; i < size; ++i) {
            elems[i] = elems[i + 1];
        }

        // TODO

        //elems[idx] = e;
    }

    /**
     * Elimina el primer elemento del arreglo.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     * <p>
     * Complejidad: O(n)
     *
     * @return el elemento eliminado
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) { return null; }

        final E oldVal = elems[0];

        /* desplazar los nodos originales hacia la izquierda */
        --size;
        for (int idx = 0; idx < size; ) {
            elems[idx] = elems[++idx];
        }

        /* actualizar último elemento a null */
        // FIXME :: necesario?
        elems[size] = null;

        return oldVal;
    }

    /**
     * Elimina el último elemento del arreglo.
     * <p>
     * Complejidad: O(1)
     *
     * @return el elemento eliminado
     */
    @Override
    public E removeLast() {
        if (isEmpty()) { return null; }

        final E oldVal = elems[--size];
        elems[size] = null;

        return oldVal;
    }

    /**
     * Remueve el elemento del arreglo en el índice indicado.
     * Desplaza hacia la izquierda los elementos mayores al índice.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx el índice del elemento del arreglo a remover
     * @return el elemento que fue removido de la lista
     */
    @Override
    public E remove(int idx) {
        checkRange(idx);
        if (isEmpty()) { return null; }

        final E oldVal = elems[idx];

        /* desplazar los nodos originales hacia la izquierda */
        --size;
        while (idx < size) {
            elems[idx] = elems[++idx];
        }

        /* actualizar último elemento a null */
        // FIXME :: necesario?
        elems[size] = null;

        return oldVal;
    }

    /**
     * Retorna el elemento del arreglo a partir del índice indicado.
     * <p>
     * Complejidad: O(1)
     *
     * @param idx índice del elemento a retornar
     * @return el elemento del arreglo en la posición indicada
     */
    @Override
    public E get(int idx) {
        checkRange(idx);

        return elems[idx];
    }

    /**
     * Reemplaza el elemento del arreglo en el índice indicado.
     * <p>
     * Complejidad: O(1)
     *
     * @param idx índice del elemento a reemplazar
     * @param e   elemento a almacenar en el índice indicado
     * @return el elemento previo a ser reemplazado
     */
    @Override
    public E set(int idx, E e) {
        checkRange(idx);

        final E oldVal = elems[idx];
        elems[idx] = e;

        return oldVal;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (int idx = 0; idx < size; ++idx) {
            if (idx != (size - 1)) {
                str.append(elems[idx]).append(", ");
            } else {
                str.append(elems[idx]);
            }
        }
        str.append("]");

        return str.toString();
    }
}
