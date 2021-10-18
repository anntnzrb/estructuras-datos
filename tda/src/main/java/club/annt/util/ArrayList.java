package club.annt.util;

import java.util.Arrays;

@SuppressWarnings("unchecked")
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
     * Cantidad de elementos del arreglo.
     */
    private int capacity;

    /**
     * Cantidad de elementos presentes en el arreglo (no su capacidad).
     */
    private int size;

    public ArrayList(final int minCapacity) {
        capacity = minCapacity;
        elems = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
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
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elems[i] = null;
        }

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
    private void checkRange(final int idx) {
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
        return capacity == size;
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
        capacity = newCap;

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, tmpArr, 0, size);
    }

    /**
     * Desplaza los nodos del arreglo hacia la izquierda.
     *
     * @param idx índice a partir del cual se iniciará el desplazamiento
     */
    private void shiftLeft(final int idx) {
        --size;
        System.arraycopy(elems, idx + 1, elems, idx, size - idx);

        /* implementación imperativa (menos eficiente) */
        //for (int i = idx; i < size; i++) {
        //    elems[i] = elems[i + 1];
        //}
    }

    /**
     * Desplaza los nodos del arreglo hacia la derecha.
     *
     * @param idx índice a partir del cual se iniciará el desplazamiento
     */
    private void shiftRight(final int idx) {
        ++size;
        System.arraycopy(elems, idx, elems, idx + 1, size - idx);

        /* implementación imperativa (menos eficiente) */
        //for (int i = (size - 1); i >= idx; --i) {
        //    elems[i + 1] = elems[i];
        //}

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
    public boolean addFirst(final E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elems[size++] = e;
            return true;
        } else if (isFull()) {
            grow();
        }

        /* desplazar los nodos originales hacia la derecha */
        shiftRight(0);

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, elems, 1, size - 1 + 1);

        /* Insertar el elemento en el primer índice & actualizar tamaño */
        elems[0] = e;

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
    public boolean addLast(final E e) {
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
    public void add(final int idx, final E e) {
        checkRange(idx);

        if (isFull()) {
            grow();
        }

        /* desplazar los nodos originales hacia la derecha */
        shiftRight(idx);

        elems[idx] = e;
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
        shiftLeft(0);

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
    public E remove(final int idx) {
        checkRange(idx);
        if (isEmpty()) { return null; }

        final E oldVal = elems[idx];

        /* desplazar los nodos originales hacia la izquierda */
        shiftLeft(idx);

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
    public E get(final int idx) {
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
    public E set(final int idx, final E e) {
        checkRange(idx);

        final E oldVal = elems[idx];
        elems[idx] = e;

        return oldVal;
    }

    /**
     * Modificado el arreglo dejando únicamente los elementos en el rango
     * proporcionado.
     *
     * @param from posición desde donde tomar los elementos del arreglo
     * @param to   posición hasta donde tomar los elementos del arreglo
     * @return {@code true} sí se realizó la operación correctamente
     */
    public boolean keepOnly(final int from, final int to) {
        checkRange(from - 1);
        checkRange(to - 1);
        if (to < from) {
            return false;
        }

        final int diffRange = to - from;
        for (int i = 0, init = from; i <= diffRange; ++i, ++init) {
            elems[i] = elems[init - 1];
        }

        for (int idx = to; idx < size; ++idx) {
            elems[idx] = null;
        }

        /* actualizar tamaño */
        size = diffRange + 1;

        return true;
    }

    /**
     * Invierte el orden de los elementos del arreglo.
     * <p>
     * Complejidad: O(n/2)
     */
    public void reverse() {
        if (!isEmpty()) {
            reverse(0, size - 1);
        }
    }

    private void reverse(final int i, final int j) {
        checkRange(i);
        checkRange(j);

        if (j >= i) {
            final E tmp = elems[i];
            elems[i] = elems[j];
            elems[j] = tmp;
            reverse(i + 1, j - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final ArrayList<?> arr = (ArrayList<?>) o;
        return capacity == arr.capacity && size == arr.size
                && Arrays.equals(elems, arr.elems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();

        str.append("[");
        if (!isEmpty()) {
            for (int idx = 0; idx < size; ++idx) {
                str.append(elems[idx])
                   .append(idx != (size - 1) ? ", " : "");
            }
        }
        str.append("]");

        return str.toString();
    }

    @Override
    public void insertAt(final List<E> xs, final int idx) {
        checkRange(idx);

        /* incrementar capacidad si es necesario */
        final int xsSize = xs.size();
        while (capacity < (xsSize + size)) {
            grow();
        }
        size += xsSize;

        for (int i = size; i - xsSize >= idx; --i) {
            elems[i] = elems[i - xsSize];
        }

        for (int i = 0; i < xsSize; ++i) {
            elems[idx + i] = xs.get(i);
        }
    }
}
