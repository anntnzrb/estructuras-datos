package club.annt.util;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("ClassWithTooManyMethods")
public final class DoublyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private Node<E> first;

    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public DoublyLinkedList() {
        first = last = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
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
        return first == null && last == null;
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

        for (Node<E> node = first; node != null; ) {
            final Node<E> nextNode = node.getNext();
            node.setData(null);
            node.setPrev(null);
            node.setNext(null);
            node = nextNode;
        }
        first = last = null;
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
            return first;
        } else if (idx == (size - 1)) {
            return last;
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> node;
        if (idx < (size >> 1)) {
            node = first;
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
            first = last = newNode;
            ++size;

            return true;
        }

        newNode.setNext(first);
        //isEmpty() se encarga si first == null
        //noinspection ConstantConditions
        first.setPrev(newNode);
        first = newNode;

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
            first = last = newNode;
            ++size;

            return true;
        }

        newNode.setPrev(last);
        last.setNext(newNode);
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

        /* reducir complejidad */
        if (e == null) {
            return;
        }
        if (idx == 0) {
            addFirst(e);
            return;
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = first;
        for (int i = 1; i < idx; ++i) {
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
        } else if (first == last) {
            final E oldVal = first.getData();
            first.setData(null);
            first = last = null;
            --size;

            return oldVal;
        }

        //isEmpty() se encarga si first == null
        //noinspection ConstantConditions
        final E oldVal = first.getData();
        final Node<E> newFirst = first.getNext();
        first.setData(null);
        first.setNext(null);
        first = newFirst;
        newFirst.setPrev(null);

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
        } else if (first == last) {
            final E oldVal = last.getData();
            last.setData(null);
            first = last = null;
            --size;

            return oldVal;
        }

        final E oldVal = last.getData();
        final Node<E> newLast = last.getPrev();
        last.setData(null);
        last.setPrev(null);
        last = newLast;
        newLast.setNext(null);

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

        if (isEmpty()) {
            return null;
            /* removeFirst() y removeLast() tienen complejidad O(1) */
        } else if (idx == 0) {
            removeFirst();
        } else if (idx == (size - 1)) {
            removeLast();
        }

        Node<E> p = first;
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

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public boolean sortedInsert(final E e, final Comparator<E> cmp) {
        if (e == null) {
            return false;
        }

        int i = 0;
        for (Node<E> ptr = first; ptr != null; ++i) {
            /* si se ha comparado todo y estamos al final, agregar al final */
            if (ptr == last) {
                addLast(e);
                return true;

                /* si el valor actual es mayor al elemento pasado, agregar */
            } else if (cmp.compare(ptr.getData(), e) >= 0) {
                add(i, e);
                return true;

                /* si el valor actual es menor al elemento pasado, avanzar */
            } else {
                ptr = ptr.getNext();
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iteratorStep(final int start, final int step) {
        // TODO
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n/2)
     */
    @Override
    public boolean isReverse(final List<E> xs) {
        if (size != xs.size()) {
            return false;
        }

        Node<E> ptrStart = first;
        Node<E> ptrEnd = last;

        E firstElem;
        E lastElem;

        while (!xs.isEmpty()) {
            firstElem = xs.removeFirst();
            lastElem = xs.isEmpty() ? firstElem : xs.removeLast();

            if (ptrStart.getData().equals(lastElem)
                && ptrEnd.getData().equals(firstElem)) {
                ptrStart = ptrStart.getNext();
                ptrEnd = ptrEnd.getPrev();
            } else {
                return false;
            }
        }

        return true;
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
            if (first != null) {
                return "[" + first.getData() + "]";
            }
        }

        final StringBuilder strBld = new StringBuilder(size);

        strBld.append("[");
        for (Node<E> node = first; node != null; node = node.getNext()) {
            if (node == last) {
                strBld.append(node.getData());
            } else {
                strBld.append(node.getData()).append(", ");
            }
        }
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
            private Node<E> ptr = first;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                return ptr != null;
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