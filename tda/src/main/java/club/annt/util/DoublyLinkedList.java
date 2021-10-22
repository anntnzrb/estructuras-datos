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

    @Override
    public int size() {
        return 0;
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
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        if (e == null) {
            return false;
        }

        final Node<E> newNode = new Node<>(e);
        first.prev = newNode;
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
        if (e == null) {
            return false;
        }

        final Node<E> newNode = new Node<>(e);
        newNode.prev = last;
        last.next = newNode;
        last = newNode;

        return true;
    }

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
        for (int i = 0; i < idx; ++i) {
            p = p.next;
        }

        newNode.prev = p;
        newNode.next = p.next;
        p.next = newNode;
        newNode.next.prev = newNode;

    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E remove(int idx) {
        return null;
    }

    @Override
    public E get(int idx) {
        return null;
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
