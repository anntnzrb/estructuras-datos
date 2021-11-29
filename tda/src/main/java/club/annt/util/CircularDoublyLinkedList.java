package club.annt.util;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("ClassWithTooManyMethods")
public final class CircularDoublyLinkedList<E> implements List<E> {
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
        return last.getNext();
    }

    private void setFirst(final Node<E> node) {
        last.setNext(node);
    }

    private Node<E> getLast() {
        return last;
    }

    private void setLast(final Node<E> node) {
        last = node;
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

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        // FIXME :: NPE
        for (Node<E> ptr = getFirst(); ptr != last; ptr = ptr.getNext()) {
            final Node<E> nextNode = ptr.getNext();
            ptr.setData(null);
            ptr.setPrev(null);
            ptr.setNext(null);
            ptr = nextNode;
        }
        last = null;
    }

    /**
     * Verifica si el índice se encuentra en el rango de la colección.
     * Este método se emplea previamente a cualquier función que accede a
     * los índices de una colección.
     * <p>
     * Complejidad: O(1)
     *
     * @param idx índice a verificar
     */
    private void rangeCheck(final int idx) {
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
        rangeCheck(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            return getFirst();
        } else if (idx == (size - 1)) {
            return last;
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> node;
        if (idx < (size >> 1)) {
            node = getFirst();
            for (int i = 0; i < idx; ++i) {
                node = node != null ? node.getNext() : null;
            }
        } else {
            node = last;
            for (int i = (size - 1); i > idx; --i) {
                node = node != null ? node.getPrev() : null;
            }
        }

        return node;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public boolean addFirst(final E e) {
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

        newNode.setPrev(last);
        newNode.setNext(getFirst());
        getFirst().setPrev(newNode);
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
    public boolean addLast(final E e) {
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

        newNode.setPrev(last);
        newNode.setNext(getFirst());
        getFirst().setPrev(newNode);
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
    public void add(final int idx, final E e) {
        rangeCheck(idx);

        if (e == null) {
            return;
        }

        /* addFirst() y addLast() tienen complejidad O(1) */
        if (idx == 0) {
            addFirst(e);
            return;
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = getFirst();
        for (int i = 1; i < idx; i++) {
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
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (getFirst() == last) {
            final E oldVal = getFirst().getData();
            getFirst().setData(null);
            setFirst(null);
            last = null;

            --size;

            return oldVal;
        }

        final E oldVal = getFirst().getData();
        final Node<E> newFirst = getFirst().getNext();
        getFirst().setData(null);
        getFirst().setNext(null);
        setFirst(newFirst);
        last.setNext(newFirst);
        newFirst.setPrev(last);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (getFirst() == last) {
            final E oldVal = last.getData();
            last.setData(null);
            setFirst(null);
            last = null;

            --size;

            return oldVal;
        }

        final E oldVal = last.getData();
        final Node<E> newLast = last.getPrev();
        newLast.setNext(last.getNext());
        last.getNext().setPrev(newLast);
        last.setData(null);
        last.setNext(null);
        last.setPrev(null);
        last = newLast;

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public E remove(final int idx) {
        rangeCheck(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            removeFirst();
        } else if (idx == (size - 1)) {
            removeLast();
        }

        Node<E> p = getFirst();
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
    public E get(final int idx) {
        return node(idx).getData();
    }

    @Override
    public E set(final int idx, final E e) {
        // TODO

        return null;
    }

    @Override
    public boolean keepOnly(final int from, final int to) {
        // TODO

        return false;
    }

    @Override
    public void reverse() {
        // TODO
    }

    @Override
    public List<E> insertAt(final List<E> xs, final int idx) {
        // TODO

        return null;
    }

    @Override
    public boolean sortedInsert(final E e, final Comparator<E> cmp) {
        // TODO
        return false;
    }

    @Override
    public Iterator<E> iteratorStep(final int start, final int step) {
        // TODO
        return null;
    }

    @Override
    public boolean isReverse(final List<E> xs) {
        // TODO
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (size == 1) {
            return "[" + last.getData() + "]";
        }

        final StringBuilder strBld = new StringBuilder(size);

        strBld.append("[");
        for (Node<E> ptr = getFirst(); ptr != last; ptr = ptr.getNext()) {
            strBld.append(ptr.getData()).append(", ");
        }
        strBld.append(last.getData());
        strBld.append("]");

        return strBld.toString();
    }

    /**
     * Retorna un Iterator de elementos de {@code E}.
     * <p>
     * Complejidad: O(1)
     *
     * @return un Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            private Node<E> ptr = getFirst();
            private boolean isStarted;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                isStarted = (!isEmpty() && !isStarted) || ptr != getFirst();

                return isStarted;
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
