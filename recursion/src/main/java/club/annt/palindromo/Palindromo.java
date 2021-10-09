package club.annt.palindromo;

public class Palindromo {
    /**
     * Verifica si el string pasado por argumento es palíndromo, versión
     * imperativa.
     *
     * @param str String a revisar si es palíndromo
     * @return Predicado si el string es palíndromo
     */
    static boolean esPalindromo(final String str) {
        for (int i = 0, j = str.length() - 1; i < j; ++i, --j) {
            if (str.charAt(i) != str.charAt(j)) { return false; }
        }

        return true;
    }

    /**
     * Verifica si el string pasado por argumento es palíndromo, versión
     * recursiva.
     *
     * @param str String a revisar si es palíndromo
     * @return Predicado si el string es palíndromo
     */
    static boolean esPalindromoRecursivo(final String str) {
        /* salida temprana */
        if (str.length() == 0 || str.length() == 1) {
            return true;
        }

        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return esPalindromoRecursivo(str.substring(1, str.length() - 1));
        }

        return false;
    }

    /**
     * Verifica si el string pasado por argumento es palíndromo, versión
     * funcional.
     *
     * @param str String a revisar si es palíndromo
     * @return Predicado si el string es palíndromo
     */
    static boolean esPalindromoFuncional(final String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
