package club.annt.fibonacci;

public class Fibonacci {
    /**
     * Función que retorna el n-ésimo número de la secuencia de Fibonacci
     * usando recursión.
     *
     * @param n -ésimo elemento de la secuencia Fibonacci
     * @return El elemento n de la secuencia Fibonacci
     */
    static long fibonacci(long n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
