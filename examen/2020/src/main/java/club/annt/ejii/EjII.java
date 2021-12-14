package club.annt.ejii;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EjII<E> {
    public List<E> getPartialIntersection(final List<List<E>> lists,
                                          final int degree,
                                          final Comparator<E> cmp) {

        final Map<E, Integer> map = new TreeMap<>(cmp);

        for (final List<E> l : lists) {
            l.forEach(e -> map.put(e, map.getOrDefault(e, 0) + 1));
        }

        return map.entrySet()
                  .stream()
                  .filter(k -> k.getValue() >= degree)
                  .map(Map.Entry::getKey)
                  .collect(Collectors.toList());
    }
}
