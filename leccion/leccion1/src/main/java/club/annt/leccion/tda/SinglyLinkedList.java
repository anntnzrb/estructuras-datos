package club.annt.leccion.tda;

import java.util.Comparator;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
    /**
     * Puntero al primer nodo.
     */
    private SinglyNode<E> first;

    /**
     * Puntero al último nodo.
     */
    private SinglyNode<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public SinglyLinkedList() {
        first = last = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final int size() {
        return size;
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

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final void clear() {
        if (isEmpty()) { return; }

        for (SinglyNode<E> n = first; n != null; ) {
            final SinglyNode<E> nextNode = n.getNext();
            n.setData(null);
            n.setNext(null);
            n = nextNode;
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
    private void checkRange(final int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException(idx);
        }
    }

    /**
     * Retorna el node anterior al solicitado.
     * <p>
     * Complejidad: O(n)
     *
     * @param node nodo cual se solicitará el nodo anterior
     * @return nodo en la posición anterior al solicitado
     */
    private SinglyNode<E> getPrevious(final SinglyNode<E> node) {
        if (isEmpty()) {
            return null;
        } else if (first == node) {
            return null;
        }

        for (SinglyNode<E> n = first; n != null; n = n.getNext()) {
            if (n.getNext() == node) { return n; }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        final SinglyNode<E> newNode = new SinglyNode<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
            ++size;
            return true;
        }

        newNode.setNext(first);
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
    public final boolean addLast(final E e) {
        SinglyNode<E> newNode = new SinglyNode<>(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            first = last = newNode;
            ++size;

            return true;
        }

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
    public final void add(final int idx, final E e) {
        /* reducir complejidad */
        if (e == null) {
            return;
        }
        if (idx == 0) {
            addFirst(e);
            return;
        }

        final SinglyNode<E> newNode = new SinglyNode<>(e);

        /* se empieza a recorrer desde idx = 1 */
        int i = 1;
        for (SinglyNode<E> n = first;
             n != null;
             n = n.getNext(), ++i) {
            if (idx == i) {
                newNode.setNext(n.getNext());
                n.setNext(newNode);
            }
        }

        ++size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeFirst() {
        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (first == last) {
            final E oldVal = first.getData();
            first = last = null;
            --size;

            return oldVal;
        }

        final E oldVal = first.getData();
        final SinglyNode<E> newFirst = first.getNext();
        if (newFirst == null) { last = null; }
        first.setData(null);
        first.setNext(null);
        first = newFirst;

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n) por {@link #getPrevious(SinglyNode)}
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (first == last) {
            final E oldVal = last.getData();
            first = last = null;
            --size;

            return oldVal;
        }

        final SinglyNode<E> prevNode = getPrevious(last);
        if (prevNode == null) { return null; }

        final E oldVal = last.getData();
        last = prevNode;
        last.setNext(null);

        --size;

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

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            removeFirst();
        } else if (idx == (size - 1)) {
            removeLast();
        }

        SinglyNode<E> ptr = first;
        for (int i = 1; i < idx; ++i) {
            ptr = ptr.getNext();
        }

        final SinglyNode<E> rmNode = ptr.getNext();
        final E oldVal = rmNode.getData();
        ptr.setNext(rmNode.getNext());

        --size;

        /* limpieza */
        rmNode.setNext(null);
        rmNode.setData(null);

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        checkRange(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            return first != null ? first.getData() : null;
        } else if (idx == (size - 1)) {
            return last != null ? last.getData() : null;
        }

        /* se empieza a recorrer desde idx = 1 */
        int i = 1;
        for (SinglyNode<E> n = first != null ? first.getNext() : null;
             n != null;
             n = n.getNext(), ++i) {
            if (idx == i) {
                return n.getData();
            }
        }

        return null;
    }

    @Override
    public final E set(final int idx, final E e) {
        // TODO
        return null;
    }

    @Override
    public final boolean keepOnly(final int from, final int to) {
        // TODO
        return false;
    }

    @Override
    public final void reverse() {
        // TODO
    }

    @Override
    public final List<E> insertAt(final List<E> xs, final int idx) {
        // TODO
        return null;
    }

    @Override
    public final void insertarOrdenado(final E elem, final Comparator<E> cmp) {
        if (elem == null) {
            return;
        } else if (size == 0) {
            addLast(elem);
            return;
        }

        int idx = 0;
        for (SinglyNode<E> n = first; n != null; n = n.getNext(), ++idx) {
            if (cmp.compare(n.getData(), elem) >= 0) {
                add(idx, elem);
                return;
            }
        }

        addLast(elem);
    }

    @Override
    public void removerElementos(List<Integer> xs) {
        //
    }

    /**
         * {@inheritDoc}
         * <p>
         * Complejidad: O(n)
         */
        @Override
        public final String toString () {
            if (isEmpty()) {
                return "[]";
            } else if (size == 1) {
                if (first != null) {
                    return "[" + first.getData() + "]";
                }
            }

            final StringBuilder str = new StringBuilder();

            str.append("[");
            for (SinglyNode<E> n = first; n != null; n = n.getNext()) {
                if (n != last) {
                    str.append(n.getData()).append(", ");
                } else {
                    str.append(n.getData());
                }
            }
            str.append("]");

            return str.toString();
        }

        /**
         * Retorna un Iterator de elementos de {@code E}.
         * <p>
         * Complejidad: O(1)
         *
         * @return un Iterator.
         */
        @Override
        public Iterator<E> iterator () {
            return new Iterator<E>() {
                private SinglyNode<E> cursor = first;

                /**
                 * {@inheritDoc}
                 * <p>
                 * Complejidad: O(1)
                 */
                @Override
                public boolean hasNext() {
                    return cursor != null;
                }

                /**
                 * {@inheritDoc}
                 * <p>
                 * Complejidad: O(1)
                 */
                @Override
                public E next() {
                    final E elem = cursor.getData();
                    cursor = cursor.getNext();

                    return elem;
                }
            };
        }
    }
