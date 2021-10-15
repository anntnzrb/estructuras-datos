package club.annt;


import club.annt.util.ArrayList;
import club.annt.util.List;

import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        List<Integer> as = new ArrayList<>();
        List<Integer> bs = new ArrayList<>();

        out.println("Lista de los primeros 20 números pares (ascendente)");
        IntStream.rangeClosed(0, 20)
                 .filter(n -> (n & 1) == 0)
                 .forEachOrdered(as::addLast);
        out.println("as = " + as + "\n");

        out.println("Lista de los primeros 20 números impares (descendente)");
        IntStream.rangeClosed(0, 20)
                 .filter(n -> (n & 1) == 1)
                 .forEachOrdered(bs::addFirst);
        out.println("bs = " + bs + "\n");

        out.println("Remover (y mostrar) el último elemento de as");
        int oldVal1 = as.removeLast();
        out.println("as = " + as);
        out.println("Elemento eliminado: " + oldVal1 + "\n");

        out.println("Remover (y mostrar) el primer elemento de bs");
        int oldVal2 = bs.removeFirst();
        out.println("bs = " + bs);
        out.println("Elemento eliminado: " + oldVal2 + "\n");

    }
}
