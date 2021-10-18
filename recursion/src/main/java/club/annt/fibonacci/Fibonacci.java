package club.annt.fibonacci;

public class Fibonacci {
    /**
     * Función que retorna el n-ésimo número de la secuencia de Fibonacci
     * usando recursión.
     *
     * @param n -ésimo elemento de la secuencia Fibonacci
     * @return El elemento n de la secuencia Fibonacci
     */
    static long fibonacciNaive(long n) {
        return n == 1
                ? 0
                : n == 2
                ? 1
                : fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }
}
