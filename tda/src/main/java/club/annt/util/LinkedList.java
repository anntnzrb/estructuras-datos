package club.annt.util;

public class LinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private Node<E> first;
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /* constructores */
    public LinkedList() {
        first = last = null;
    }

    /**
     * Enlaza {@code e} como primer elemento del arreglo.
     *
     * @param e elemento a agregar
     */
    private void linkFirst(final E e) {
    }

    @Override
    public final int size() {
        int count = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            ++count;
        }

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
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        Node<E> newNode = new Node<>(e);

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

    @Override
    public final void add(final int idx, final E e) {

    }

    @Override
    public final E removeFirst() {
        return null;
    }

    @Override
    public final E removeLast() {
        return null;
    }

    @Override
    public final E remove(final int idx) {
        return null;
    }

    @Override
    public final E get(final int idx) {
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
        for (Node<E> n = first; n != null; n = n.next) {
            if (n.next != null) {
                str.append(n.item).append(", ");
            } else {
                str.append(n.item);
            }
        }
        str.append("]");

        return str.toString();
    }

    /**
     * Clase Node para Single LinkedList.
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

        public Node(final E item) {
            this(item, null);
        }

        public Node(final E item, final Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
