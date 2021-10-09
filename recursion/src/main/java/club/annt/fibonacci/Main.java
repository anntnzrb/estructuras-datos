package club.annt.fibonacci;

import java.util.stream.LongStream;

import static club.annt.fibonacci.Fibonacci.fibonacci;
import static java.lang.System.out;

public class Main {
    public static void main(final String... argv) {
        out.println("El fibonacci de 10 es " + fibonacci(10));

        out.println("\nFibonacci de los 40 primeros números:");
        LongStream.rangeClosed(1, 40)
                  .map(Fibonacci::fibonacci)
                  .forEach(out::println);
    }
}