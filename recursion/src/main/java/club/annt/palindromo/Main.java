package club.annt.palindromo;

import static club.annt.palindromo.Palindromo.*;
import static java.lang.System.out;

public class Main {
    public static void main(final String... argv) {
        out.println(esPalindromo("madam"));
        out.println(esPalindromoRecursivo("poop"));
        out.println(esPalindromoFuncional("abba"));

    }
}
