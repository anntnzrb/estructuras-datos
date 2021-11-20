package club.annt.paoi.ejiv;

import java.util.*;

public class Duo<E> {
    List<E> xs;
    List<E> ys;

    Duo() {
        xs = new ArrayList<>();
        ys = new ArrayList<>();
    }

    public void addTo(final E e, final int c, final Comparator<E> cmp) {
        final Queue<E> pq = new PriorityQueue<>(cmp);

        switch (c) {
        case 1:
            xs.add(e);
            pq.addAll(xs);

            xs = new ArrayList<>(pq);
            break;
        case 2:
            ys.add(e);
            pq.addAll(ys);

            ys = new ArrayList<>(pq);
            break;
        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void deleteFrom(final E e, final int c, final Comparator<E> cmp) {
        final Queue<E> pq = new PriorityQueue<>(cmp);

        switch (c) {
        case 1:
            xs.removeAll(List.of(e));
            pq.addAll(xs);

            xs = new ArrayList<>(pq);
            break;
        case 2:
            ys.removeAll(List.of(e));
            pq.addAll(ys);

            ys = new ArrayList<>(pq);
            break;
        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public List<E> getDuplicates(final Comparator<E> cmp) {
        final List<E> res = new ArrayList<>();

        for (int p1 = 0, p2 = 0; p1 < xs.size() && p2 < ys.size(); ) {
            final int cmpP1P2 = cmp.compare(xs.get(p1), ys.get(p2));

            if (cmpP1P2 == 0) {
                res.add(xs.get(p1));
                ++p1;
                ++p2;
            } else if (cmpP1P2 > 0) {
                ++p2;
            } else {
                ++p1;
            }
        }

        return res;
    }

    @Override
    public String toString() {
        return xs + " y " + ys;
    }
}