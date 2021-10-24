package club.annt.util;

import java.util.Iterator;

public class SimplyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private Node<E> first;
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /* constructores */
    public SimplyLinkedList() {
        first = last = null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final int size() {
        int count = 0;
        for (Node<E> n = first; n != null; n = n.next, ++count) ;

        return count;
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

    }

    /**
     * Verifica si el índice se encuentra en el rango de la colección.
     * Este método se emplea previamente a cualquier función que accede a
     * los índices de una colección.
     *
     * @param idx índice a verificar
     */
    private void checkRange(final int idx) {
        if (idx >= this.size()) {
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
    private Node<E> getPrevious(final Node<E> node) {
        if (isEmpty()) {
            return null;
        } else if (first == node) {
            return null;
        }

        for (Node<E> n = first; n != null; n = n.next) {
            if (n.next == node) { return n; }
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
        final Node<E> newNode = new Node<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
        }

        newNode.next = first;
        first = newNode;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addLast(final E e) {
        Node<E> newNode = new Node<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
        }

        last.next = newNode;
        last = newNode;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: ???
     */
    @Override
    public final void add(final int idx, final E e) {

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

        final E oldVal = first.item;
        final Node<E> next = first.next;
        first.item = null;
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
     * Complejidad: O(n) por {@link #getPrevious(Node)}
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (first == last) {
            first = last = null;
        }

        final Node<E> prevNode = getPrevious(last);
        if (prevNode == null) { return null; }

        last = prevNode;
        last.next = null;

        return last.item;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E remove(final int idx) {
        checkRange(idx);

        if (idx == 0) {
            final E oldVal = first.item;
            this.removeFirst();
            return oldVal;
        } else if (idx == (this.size() - 1)) {
            final E oldVal = last.item;
            this.removeLast();
            return oldVal;
        }


        /* crear un nuevo nodo que tenga el valor del nodo siguiente al que
         * se va a eliminar
         */
        Node<E> newLastNode = first.next;
        for (int i = 1; i < (idx - 1); ++i) {
            newLastNode = newLastNode.next;
        }

        final Node<E> oldNode = newLastNode.next;
        newLastNode.next = oldNode.next;
        oldNode.next = null;

        return oldNode.item;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        checkRange(idx);

        int jdx = 0; /* índice secundario */
        for (Node<E> n = first; n != null; n = n.next, ++jdx) {
            if (idx == jdx) {
                return n.item;
            }
        }

        return null;
    }

    @Override
    public final E set(final int idx, final E e) {
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
        for (Node<E> n = first; n != null; n = n.next, ++count) {
            if (count == from) {
                first = n;
            } else if (count == to) {
                last = n;
                last.next = null;
            }
        }

        return true;
    }

    @Override
    public final void reverse() {
        if (!isEmpty()) {
            final SimplyLinkedList<E> tmpLList = new SimplyLinkedList<>();
            while (!this.isEmpty()) {
                tmpLList.addFirst(this.removeFirst());
            }

            this.first = tmpLList.first;
            this.last = tmpLList.last;
        }
    }

    @Override
    public final List<E> insertAt(final List<E> xs, final int idx) {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    public final String toString() {
        final StringBuilder str = new StringBuilder();

        str.append("[");
        if (!isEmpty()) {
            for (Node<E> n = first; n != null; n = n.next) {
                if (n != last) {
                    str.append(n.item).append(", ");
                } else {
                    str.append(n.item);
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
            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: ???
             */
            @Override
            public boolean hasNext() {
                return false;
            }

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: ???
             */
            @Override
            public E next() {
                return null;
            }
        };
    }

    /**
     * Clase Node para Single SimplyLinkedList.
     *
     * @param <E> tipo de dato para el Node
     */
    private static class Node<E> {
        E item;
        Node<E> next;

        /* constructores */
        public Node() {
            this(null, null);
        }

        public Node(final E elem) {
            this(elem, null);
        }

        public Node(final E item, final Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
