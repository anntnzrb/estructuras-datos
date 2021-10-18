package club.annt.util;

public interface List<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean addFirst(final E e);

    boolean addLast(final E e);

    void add(final int idx, final E e);

    E removeFirst();

    E removeLast();

    E remove(final int idx);

    E get(final int idx);

    E set(final int idx, final E e);

    boolean keepOnly(final int from, final int to);

    void reverse();

    void insertAt(final List<E> xs, final int idx);
}
