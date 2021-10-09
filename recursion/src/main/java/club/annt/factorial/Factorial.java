package club.annt.factorial;

public class Factorial {
    /**
     * Función factorial empleando recursión, versión ingenua.
     *
     * @param n número a aplicar el factorial
     * @return El resultado de la operación factorial
     */
    static long factorialNaive(long n) {
        return (n < 2) ? 1 : n * factorialNaive(--n);
    }
}
