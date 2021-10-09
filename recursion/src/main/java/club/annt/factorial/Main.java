package club.annt.factorial;

import java.util.stream.LongStream;

import static club.annt.factorial.Factorial.factorialNaive;
import static java.lang.System.out;

public class Main {
    public static void main(final String... argv) {
        out.println("Factorial de 2 es " + factorialNaive(2));
        out.println("Factorial de 5 es " + factorialNaive(5));

        out.println("\nFactorial de los 10 primeros números:");
        LongStream.rangeClosed(1, 10)
                  .map(Factorial::factorialNaive)
                  .forEach(out::println);
    }
}
