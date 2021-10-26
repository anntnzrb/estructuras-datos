package club.annt.util;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private SinglyNode<E> first;

    /**
     * Puntero al último nodo.
     */
    private SinglyNode<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public SinglyLinkedList() {
        first = last = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public final void clear() {
        // TODO
    }

    /**
     * Verifica si el índice se encuentra en el rango de la colección.
     * Este método se emplea previamente a cualquier función que accede a
     * los índices de una colección.
     *
     * @param idx índice a verificar
     */
    private void checkRange(final int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException(idx);
        }
    }

    /**
     * Retorna el node anterior al solicitado.
     * <p>
     * Complejidad: O(n)
     *
     * @param node nodo cual se solicitará el nodo anterior
     * @return nodo en la posición anterior al solicitado
     */
    private SinglyNode<E> getPrevious(final SinglyNode<E> node) {
        if (isEmpty()) {
            return null;
        } else if (first == node) {
            return null;
        }

        for (SinglyNode<E> n = first; n != null; n = n.getNext()) {
            if (n.getNext() == node) { return n; }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        final SinglyNode<E> newNode = new SinglyNode<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
            ++size;
            return true;
        }

        newNode.setNext(first);
        first = newNode;

        ++size;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addLast(final E e) {
        SinglyNode<E> newNode = new SinglyNode<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
            ++size;

            return true;
        }

        last.setNext(newNode);
        last = newNode;

        ++size;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: ???
     */
    @Override
    public final void add(final int idx, final E e) {
        /* reducir complejidad */
        if (e == null) {
            return;
        }
        if (idx == 0) {
            addFirst(e);
        } else if (idx == (size - 1)) {
            addLast(e);
        }

        final SinglyNode<E> newNode = new SinglyNode<>(e);
        // TODO

        /* se empieza a recorrer desde idx = 1 */
        int i = 1;
        for (SinglyNode<E> n = first.getNext();
             n != null;
             n = n.getNext(), ++i) {
            if (idx == i) {

            }
        }

        ++size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (first == null || last == null) {
            return null;
        } else if (first == last) {
            first = last = null;
        }

        final E oldVal = first.getData();
        final SinglyNode<E> next = first.getNext();
        first.setData(null);
        first = next;
        if (next == null) { last = null; }

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     * <p>
     * NOTA:
     * <p>
     * Complejidad: O(n) por {@link #getPrevious(SinglyNode)}
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (first == last) {
            first = last = null;
        }

        final SinglyNode<E> prevNode = getPrevious(last);
        if (prevNode == null) { return null; }

        last = prevNode;
        last.setNext(null);

        return last.getData();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E remove(final int idx) {
        // TODO
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        checkRange(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            return first != null ? first.getData() : null;
        } else if (idx == (size - 1)) {
            return last != null ? last.getData() : null;
        }

        /* se empieza a recorrer desde idx = 1 */
        int i = 1;
        for (SinglyNode<E> n = first != null ? first.getNext() : null;
             n != null;
             n = n.getNext(), ++i) {
            if (idx == i) {
                return n.getData();
            }
        }

        return null;
    }

    @Override
    public final E set(final int idx, final E e) {
        // TODO
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final boolean keepOnly(final int from, final int to) {
        if (to < from) {
            return false;
        }

        int count = 1;
        for (SinglyNode<E> n = first; n != null; n = n.getNext(), ++count) {
            if (count == from) {
                first = n;
            } else if (count == to) {
                last = n;
                last.setNext(null);
            }
        }

        return true;
    }

    @Override
    public final void reverse() {
        // TODO
    }

    @Override
    public final List<E> insertAt(final List<E> xs, final int idx) {
        // TODO
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    public final String toString() {
        // TODO
        final StringBuilder str = new StringBuilder();

        str.append("[");
        if (!isEmpty()) {
            for (SinglyNode<E> n = first; n != null; n = n.getNext()) {
                if (n != last) {
                    str.append(n.getData()).append(", ");
                } else {
                    str.append(n.getData());
                }
            }
        }
        str.append("]");

        return str.toString();
    }

    /**
     * Retorna un Iterator de elementos de {@code E}.
     *
     * @return un Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private SinglyNode<E> cursor = first;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public E next() {
                final E elem = cursor.getData();
                cursor = cursor.getNext();

                return elem;
            }
        };
    }
}
