package club.annt.fibonacci;

import java.util.stream.LongStream;

import static club.annt.fibonacci.Fibonacci.fibonacciNaive;
import static java.lang.System.out;

public class Main {
    public static void main(final String... argv) {
        final long n = 10;
        out.printf("El fibonacci de %d es %d\n\n", n, fibonacciNaive(n));

        out.println("\nFibonacci de los 40 primeros números:");
        LongStream.rangeClosed(1, 40)
                  .map(Fibonacci::fibonacciNaive)
                  .forEach(out::println);
    }
}