package club.annt.util;

public class CircularDoublyLinkedList<E> implements List<E> {
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public CircularDoublyLinkedList() {
        last = null;
        size = 0;
    }

    /* getters & setters */
    private Node<E> getFirst() {
        return last.next;
    }

    private void setFirst(final Node<E> n) {
        last.next = n;
    }

    private Node<E> getLast() {
        return last;
    }

    private void setLast(final Node<E> n) {
        last = n;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public boolean isEmpty() {
        return getLast() == null;
    }

    @Override
    public void clear() {
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
            return getFirst();
        } else if (idx == (size - 1)) {
            return getLast();
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> n;
        if (idx < (size >> 1)) {
            n = getFirst();
            for (int i = 0; i < idx; ++i) {
                n = n != null ? n.next : null;
            }
        } else {
            n = getLast();
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
            setLast(newNode);
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.prev = getLast();
        newNode.next = getFirst();
        getFirst().prev = newNode;
        setFirst(newNode);

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
            setLast(newNode);
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.prev = getLast();
        newNode.next = getFirst();
        getFirst().prev = newNode;
        setFirst(newNode);
        setLast(newNode);

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

        if (e == null) {
            return;
        }

        /* addFirst() y addLast() tienen complejidad O(1) */
        if (idx == 0) {
            addFirst(e);
        } else if (idx == (size - 1)) {
            addLast(e);
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = getFirst();
        for (int i = 1; i < idx; i++) {
            p = p.next;
        }

        newNode.prev = p;
        newNode.next = p.next;
        p.next = newNode;
        newNode.next.prev = newNode;

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
        } else if (getFirst() == getLast()) {
            final E oldVal = getFirst().item;
            getFirst().item = null;
            setFirst(null);
            setLast(null);

            --size;

            return oldVal;
        }

        final E oldVal = getFirst().item;
        final Node<E> newFirst = getFirst().next;
        getFirst().item = null;
        getFirst().next = null;
        setFirst(newFirst);
        getLast().next = newFirst;
        newFirst.prev = getLast();

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
        } else if (getFirst() == getLast()) {
            final E oldVal = getLast().item;
            getLast().item = null;
            setFirst(null);
            setLast(null);

            --size;

            return oldVal;
        }

        final E oldVal = getLast().item;
        final Node<E> newLast = getLast().prev;
        getLast().item = null;
        getLast().next = null;
        setLast(newLast);
        getFirst().prev = newLast;
        newLast.next = getFirst();

        --size;

        return oldVal;
    }

    @Override
    public E remove(int idx) {
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
        return node(idx).item;
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
            return "[" + getLast().item + "]";
        }

        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (Node<E> n = getFirst(); n != getLast(); n = n.next) {
            str.append(n.item).append(", ");
        }
        str.append(getLast().item);
        str.append("]");

        return str.toString();
    }

    /**
     * Clase Node para Doubly CircularList.
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
