package club.annt;


import club.annt.util.ArrayList;
import club.annt.util.List;

import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        List<Integer> as = new ArrayList<>();
        List<Integer> bs = new ArrayList<>();

        out.println("Lista de los primeros 30 números pares (ascendente)");
        IntStream.rangeClosed(0, 30)
                 .filter(n -> (n & 1) == 0)
                 .forEachOrdered(as::addLast);
        out.println("as = " + as + "\n");

        out.println("Lista de los primeros 30 números impares (descendente)");
        IntStream.rangeClosed(0, 30)
                 .filter(n -> (n & 1) == 1)
                 .forEachOrdered(bs::addFirst);
        out.println("bs = " + bs + "\n");

        out.println("Remover (y mostrar) el último elemento de as");
        int oldVal = as.removeLast();
        out.println("as = " + as);
        out.println("Elemento eliminado: " + oldVal + "\n");

        out.println("Remover (y mostrar) el primer elemento de bs");
        oldVal = bs.removeFirst();
        out.println("bs = " + bs);
        out.println("Elemento eliminado: " + oldVal + "\n");

        out.println("Remover (y mostrar) el elemento del índice 3 de as");
        oldVal = as.remove(3);
        out.println("as = " + as);
        out.println("Elemento eliminado: " + oldVal + "\n");

        out.println("Agregar un elemento en el índice 3 del arreglo bs");
        bs.add(3, 111);
        out.println("bs = " + bs);
    }
}
