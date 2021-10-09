package club.annt.reversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static club.annt.reversion.Reversion.revertirImperativa;
import static club.annt.reversion.Reversion.revertirRecursiva;
import static java.lang.System.out;

public class Main {
    public static void main(final String... argv) {
        final List<Integer> xs1 = Arrays.asList(1, 2, 3);
        final List<Character> xs2 = Arrays.asList('x', 'y', 'z');

        out.println("Listas originales:");
        out.println(xs1);
        out.println(xs2);

        out.println("\nListas revertidas:");
        out.println("\nVersión imperativa:");
        out.println(revertirImperativa(xs1));
        out.println(revertirImperativa(xs2));

        out.println("\nVersión recursiva:");
        final List<Integer> xs1a = new ArrayList<>(xs1);
        final List<Character> xs2a = new ArrayList<>(xs2);
        revertirRecursiva(xs1a);
        revertirRecursiva(xs2a);
        out.println(xs1a);
        out.println(xs2a);

    }
}
