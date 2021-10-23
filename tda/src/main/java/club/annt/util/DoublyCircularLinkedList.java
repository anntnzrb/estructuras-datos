package club.annt.util;

public class DoublyCircularLinkedList<E> implements List<E> {
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public DoublyCircularLinkedList() {
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
        return last == null;
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
        if (idx >= size) {
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

        /* newNode === newFirst */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            last = newNode;
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.prev = last;
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
            last = newNode;
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.prev = last;
        newNode.next = getFirst();
        getFirst().prev = newNode;
        setFirst(newNode);
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
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    public final String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (size == 1) {
            return "[" + last.item + "]";
        }

        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (Node<E> n = getFirst(); n != last; n = n.next) {
            str.append(n.item).append(", ");
        }
        str.append(last.item);
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
