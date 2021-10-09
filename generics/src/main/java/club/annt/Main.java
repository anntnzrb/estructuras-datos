package club.annt;

import club.annt.items.Laptop;
import club.annt.items.Persona;

import static java.lang.StrictMath.log10;
import static java.lang.StrictMath.pow;

public final class Main {

    private Main() {}

    public static void main(final String... argv) {
        /* ********************************************************************
         * # 1
         * ***************************************************************** */
        final Paquete<Integer> pkg1 = new Paquete<>(16);
        final Paquete<Persona> pkg2 = new Paquete<>(new Persona("Elco", 33));
        final Paquete<Laptop> pkg3 = new Paquete<>(new Laptop("Dell", 2014));
        final Paquete<Double> pkg4 = new Paquete<>(log10(pow(2.0, 3.0)));
        final Paquete<String> pkg5 = new Paquete<>("Foo");

        /* ********************************************************************
         * # 2
         *
         * Crear una clase que permita representar la relación entre dos
         * entidades cualesquiera.
         * ***************************************************************** */

        /* tipos iguales */
        final Relacion<String, String> rel1 = new Relacion<>("Redbull", "Monster", "Bebidas energéticas");
        final Relacion<Integer, Integer> rel2 = new Relacion<>(2, 4, "Primeros 2 números pares");

        /* tipos distintos */
        final Relacion<Integer, String> rel3 = new Relacion<>(100, "100", "El número '100' de forma numérica y escrita en español");
        final Relacion<Integer, Boolean> rel4 = new Relacion<>(4, true, "No hay relación");
    }
}
