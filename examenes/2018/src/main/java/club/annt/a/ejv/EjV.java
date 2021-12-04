package club.annt.a.ejv;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class EjV {
    public static <T> Deque<T> sortStack(final Deque<T> input,
                                         final Comparator<T> cmp) {
        final Deque<T> tmp = new ArrayDeque<>();

        while (!input.isEmpty()) {
            if (tmp.isEmpty()) {
                tmp.push(input.pop());
            } else {
                final T elem = input.pop();
                while (!tmp.isEmpty() && cmp.compare(elem, tmp.peek()) > 0) {
                    input.push(tmp.pop());
                }
                tmp.push(elem);
            }
        }

        return tmp;
    }
}
