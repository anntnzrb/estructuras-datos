package club.annt.util;

/**
 * Clase SinglyNode para Singly LinkedList.
 *
 * @param <E> tipo de dato para el SinglyNode
 */
@SuppressWarnings("ClassHasNoToStringMethod")
public final class SinglyNode<E> {
    private E             data;
    private SinglyNode<E> next;

    /* constructores */
    public SinglyNode() {
        this(null, null);
    }

    public SinglyNode(final E data) {
        this(data, null);
    }

    public SinglyNode(final E data, final SinglyNode<E> next) {
        this.next = next;
        this.data = data;
    }

    /* getters & setters */
    public E getData() {
        return data;
    }

    public void setData(final E data) {
        this.data = data;
    }

    public SinglyNode<E> getNext() {
        return next;
    }

    public void setNext(final SinglyNode<E> next) {
        this.next = next;
    }
}
