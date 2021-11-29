package club.annt.util;

/**
 * Clase Node para Doubly LinkedList.
 *
 * @param <E> tipo de dato para el Node
 */
@SuppressWarnings("ClassHasNoToStringMethod")
public final class Node<E> {
    private E       data;
    private Node<E> next;
    private Node<E> prev;

    /* constructores */
    public Node() {
        this(null, null, null);
    }

    public Node(final E data) {
        this(null, data, null);
    }

    public Node(final Node<E> prev, final E data, final Node<E> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    /* getters & setters */
    public E getData() {
        return data;
    }

    public void setData(final E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(final Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(final Node<E> prev) {
        this.prev = prev;
    }
}
