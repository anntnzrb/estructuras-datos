package club.annt.util;

public interface List<E> {
    int size();

    boolean addFirst(E e);

    boolean addLast(E e);

    void add(int idx, E e);

    E remove(int idx);

    E get(int idx);

    E set(int idx, E e);
}
