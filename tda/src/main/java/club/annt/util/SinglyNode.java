package club.annt.util;

/**
 * Clase SinglyNode para Singly LinkedList.
 *
 * @param <E> tipo de dato para el SinglyNode
 */
public class SinglyNode<E> {
    private E data;
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
    public final E getData() {
        return data;
    }

    public final void setData(final E data) {
        this.data = data;
    }

    public final SinglyNode<E> getNext() {
        return next;
    }

    public final void setNext(final SinglyNode<E> next) {
        this.next = next;
    }
}
