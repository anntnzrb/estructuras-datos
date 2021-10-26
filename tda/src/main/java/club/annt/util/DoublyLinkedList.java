package club.annt.util;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private Node<E> first;

    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public DoublyLinkedList() {
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

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public void clear() {
        for (Node<E> n = first; n != null; ) {
            final Node<E> nextNode = n.getNext();
            n.setData(null);
            n.setPrev(null);
            n.setNext(null);
            n = nextNode;
        }
        first = last = null;
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
     * Retorna el nodo en el índice indicado.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx índice del nodo a buscar
     * @return nodo en el índice indicado
     */
    private Node<E> node(final int idx) {
        checkRange(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            return first;
        } else if (idx == (size - 1)) {
            return last;
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> n;
        if (idx < (size >> 1)) {
            n = first;
            for (int i = 0; i < idx; ++i) {
                n = n != null ? n.getNext() : null;
            }
        } else {
            n = last;
            for (int i = (size - 1); i > idx; --i) {
                n = n != null ? n.getPrev() : null;
            }
        }

        return n;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        if (e == null) {
            return false;
        }

        /* newNode === newFirst */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            first = last = newNode;
            ++size;

            return true;
        }

        newNode.setNext(first);
        //isEmpty() se encarga si first == null
        //noinspection ConstantConditions
        first.setPrev(newNode);
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
        if (e == null) {
            return false;
        }

        /* newNode === newLast */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            first = last = newNode;
            ++size;

            return true;
        }

        newNode.setPrev(last);
        last.setNext(newNode);
        last = newNode;

        ++size;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final void add(final int idx, final E e) {
        checkRange(idx);

        /* reducir complejidad */
        if (e == null) {
            return;
        }
        if (idx == 0) {
            addFirst(e);
        } else if (idx == (size - 1)) {
            addLast(e);
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = first;
        for (int i = 1; i < idx; ++i) {
            p = p.getNext();
        }

        newNode.setPrev(p);
        newNode.setNext(p.getNext());
        p.setNext(newNode);
        newNode.getNext().setPrev(newNode);

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
        } else if (first == last) {
            final E oldVal = first.getData();
            first.setData(null);
            first = last = null;
            --size;

            return oldVal;
        }

        //isEmpty() se encarga si first == null
        //noinspection ConstantConditions
        final E oldVal = first.getData();
        final Node<E> newFirst = first.getNext();
        last.setData(null);
        first.setNext(null);
        first = newFirst;
        newFirst.setPrev(null);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (first == last) {
            final E oldVal = last.getData();
            last.setData(null);
            first = last = null;
            --size;

            return oldVal;
        }

        final E oldVal = last.getData();
        final Node<E> newLast = last.getPrev();
        last.setData(null);
        last.setPrev(null);
        last = newLast;
        newLast.setNext(null);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E remove(final int idx) {
        checkRange(idx);

        if (isEmpty()) {
            return null;
            /* removeFirst() y removeLast() tienen complejidad O(1) */
        } else if (idx == 0) {
            removeFirst();
        } else if (idx == (size - 1)) {
            removeLast();
        }

        Node<E> p = first;
        for (int i = 0; i < idx; ++i) {
            p = p.getNext();
        }
        p.getPrev().setNext(p.getNext());
        p.getNext().setPrev(p.getPrev());

        final E oldVal = p.getData();
        p.setData(null);
        p.setPrev(null);
        p.setNext(null);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        return node(idx).getData();
    }

    @Override
    public E set(int idx, E e) {
        // TODO

        return null;
    }

    @Override
    public boolean keepOnly(int from, int to) {
        // TODO

        return false;
    }

    @Override
    public void reverse() {
        // TODO
    }

    @Override
    public List<E> insertAt(List<E> xs, int idx) {
        // TODO

        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    public final String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (size == 1) {
            return "[" + last.getData() + "]";
        }

        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (Node<E> n = first; n != null; n = n.getNext()) {
            if (n != last) {
                str.append(n.getData()).append(", ");
            } else {
                str.append(n.getData());
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
            private Node<E> ptr = first;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public E next() {
                final E elem = ptr.getData();
                ptr = ptr.getNext();

                return elem;
            }
        };
    }
}