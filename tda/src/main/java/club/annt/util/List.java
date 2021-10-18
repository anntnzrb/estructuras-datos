package club.annt.util;

public interface List<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean addFirst(E e);

    boolean addLast(E e);

    void add(int idx, E e);

    E removeFirst();

    E removeLast();

    E remove(int idx);

    E get(int idx);

    E set(int idx, E e);

    boolean keepOnly(final int from, final int to);
}
