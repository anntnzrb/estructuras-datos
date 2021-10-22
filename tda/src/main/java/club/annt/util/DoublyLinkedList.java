package club.annt.util;

public class DoublyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private Node<E> first;
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /* constructores */
    public DoublyLinkedList() {
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
    public void clear() {

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
        } else if (idx == (this.size() - 1)) {
            return last;
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> n;
        final int size = this.size();
        if (idx < (size >> 1)) {
            n = first;
            for (int i = 0; i < idx; ++i) {
                n = n != null ? n.next : null;
            }
        } else {
            n = last;
            for (int i = (size - 1); i > idx; --i) {
                n = n != null ? n.prev : null;
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
            return true;
        }

        newNode.next = first;
        //isEmpty() se encarga si first == null
        //noinspection ConstantConditions
        first.prev = newNode;
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
        if (e == null) {
            return false;
        }

        /* newNode === newLast */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            first = last = newNode;
            return true;
        }

        newNode.prev = last;
        last.next = newNode;
        last = newNode;

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

        if (e == null) {
            return;
        }

        /* addFirst() y addLast() tienen complejidad O(1) */
        if (idx == 0) {
            addFirst(e);
        } else if (idx == (this.size() - 1)) {
            addLast(e);
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = first;
        for (int i = 1; i < idx; ++i) {
            p = p.next;
        }

        newNode.prev = p;
        newNode.next = p.next;
        p.next = newNode;
        newNode.next.prev = newNode;
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
            first.item = null;
            first = last = null;

            return null;
        }

        final E oldVal = first.item;
        final Node<E> newFirst = first.next;
        first.item = null;
        first.next = null;
        first = newFirst;
        newFirst.prev = null;

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
            first.item = null;
            first = last = null;

            return null;
        }

        final E oldVal = last.item;
        final Node<E> newLast = last.prev;
        last.item = null;
        last.prev = null;
        last = newLast;
        newLast.next = null;

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
        } else if (idx == (this.size() - 1)) {
            removeLast();
        }

        Node<E> p = first;
        for (int i = 0; i < idx; ++i) {
            p = p.next;
        }
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.prev = null;
        p.next = null;

        return p.item;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        checkRange(idx);

        return node(idx).item;
    }

    @Override
    public E set(int idx, E e) {
        return null;
    }

    @Override
    public boolean keepOnly(int from, int to) {
        return false;
    }

    @Override
    public void reverse() {

    }

    @Override
    public List<E> insertAt(List<E> xs, int idx) {
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
     * Clase Node para Doubly LinkedList.
     *
     * @param <E> tipo de dato para el Node
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        /* constructores */
        public Node() {
            this(null, null, null);
        }

        public Node(final E elem) {
            this(null, elem, null);
        }

        public Node(final Node<E> prev, final E item, final Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

}
