package club.annt.eji;

import java.util.LinkedList;
import java.util.List;

final public class EjI {
    public static List<String> getStringsShorterThan(final List<String> inputList,
                                                     final int k) {
        final List<String> res = new LinkedList<>();

        if (inputList.size() == 0) {
            return res;
        }

        final String str = inputList.remove(0);
        if (str.length() < k) {
            res.add(str);
        }

        res.addAll(getStringsShorterThan(inputList, k));

        return res;
    }
}
