package club.annt.ejercicios;

import java.util.List;

public class Main {
    /**
     * Contar el número de ocurrencias de un elemento en un arreglo.
     * <p>
     * [1,2,2,3,4,2,2]
     * len = 7
     * <p>
     * - ocurrencias de 2 => 4
     * - ocurrencias de 5 => 0
     * - ocurrencias de 1 => 1
     * <p>
     * Casos Base:
     * [] o al final del arreglo regreso... 0
     * [1] buscas 1 regreso... 1
     * [2] buscas 1 regreso... 0
     * final del arreglo... 0
     *
     * @param xs  arreglo de enteros
     * @param num número a ser buscado
     * @param idx índice por el cual empezar la búsqueda
     * @return cantidad de ocurrencias del número solicitado
     */
    public static int contarOcurrencias(final List<Integer> xs, final int num,
                                        int idx) {
        if (idx >= xs.size()) {
            return 0;
        } else if (xs.get(idx).equals(num)) {
            return 1 + contarOcurrencias(xs, num, ++idx);
        } else {
            return contarOcurrencias(xs, num, ++idx);
        }
    }

    /**
     * Wrapper de {@link #contarOcurrencias(List, int, int)}
     */
    public static int contarOcurrencias(final List<Integer> xs, final int num) {
        return contarOcurrencias(xs, num, 0);
    }

    /**
     * Calcula la potencia de un número de forma recursiva, tanto para exponentes
     * negativos como positivos.
     * <p>
     * 2^3 => 2^2*2
     * 2^2 => 2^1 *2
     * 2^1 => 2
     * 2^3 => 2*2*2
     * <p>
     * 2^-3 => 2^-2 /2
     * 2^-2 => 2^-1 /2
     * 2^-1 => 2^0/2
     * 2^0 => 1
     * 2^-3 => 1/2/2/2
     * <p>
     * Casos base: n^exp
     * n^1 = n
     * n^0 = 1
     *
     * @param num número base
     * @param exp número exponente
     * @return resultado de la operación {@code num}<sup>{@code exp}</sup>
     */
    public static float potencia(final float num, int exp) {
        if (exp == 1) {
            return num;
        } else if (exp == 0) {
            return 1;
        } else if (exp < 0) {
            return potencia(num, ++exp) / num;
        }

        return potencia(num, --exp) * num;
    }

    /**
     * Arreglo que cambia cada número a 0 si es menor al valor
     * dado y 1 si es mayor o igual al valor dado.
     *
     * @param xs  arreglo de enteros
     * @param num número a ser buscado
     * @param idx índice por el cual se empieza la búsqueda
     */
    public static void booleanArray(final List<Integer> xs, final int num,
                                    int idx) {
        if (xs.size() > 0 && xs.size() > idx) {
            if (xs.get(idx) < num) {
                xs.set(idx, 0);
            } else {
                xs.set(idx, 1);
            }

            booleanArray(xs, num, ++idx);
        }
    }

    /**
     * Wrapper de {@link #booleanArray(List, int, int)}
     */
    public static void booleanArray(final List<Integer> xs, final int num) {
        booleanArray(xs, num, 0);
    }

    /*
     * Buscar si un arreglo contiene un número.
     * [1,2,3,4]
     * 0 1 2 3
     * \ i j
     * j i -> parar el algoritmo
     * [1,2,3,4,5]
     * \0 1 2 3 4
     * \    ij
     * <p>
     * Contiene al 1 -> true
     * contiene al 4 -> false
     * Casos Base:
     * [] y se acabó el arreglo -> falso
     * [1] busca 1 -> true
     * [2] busca 1 -> false
     * [2,1,1,1,1] busca 1 -> true
     */

    /**
     * Buscar si un arreglo contiene un número.
     * [1,2,3,4]
     * 0 1 2 3
     * \ i j
     * j i -> parar el algoritmo
     * [1,2,3,4,5]
     * \0 1 2 3 4
     * \    ij
     * <p>
     * Contiene al 1 -> true
     * contiene al 4 -> false
     * Casos Base:
     * [] y se acabó el arreglo -> falso
     * [1] busca 1 -> true
     * [2] busca 1 -> false
     * [2,1,1,1,1] busca 1 -> true
     * <p>
     * NOTAS:
     * - Complejidad: O(n)
     *
     * @param xs  arreglo de enteros
     * @param num número a ser buscado
     * @param idx índice por el cual se empieza la búsqueda
     * @return {@code true} sí el número está presente en el arreglo
     */
    public static boolean buscarNumV1(final List<Integer> xs, final int num,
                                      int idx) {
        if (xs.size() == 0 || xs.size() <= idx) {
            return false;
        } else if (xs.get(idx) == num) {
            return true;
        }

        return buscarNumV1(xs, num, ++idx);
    }

    /**
     * Wrapper de {@link #buscarNumV1(List, int, int)}
     */
    public static boolean buscarNumV1(final List<Integer> xs, final int num) {
        return buscarNumV1(xs, num, 0);
    }

    /**
     * {@link #buscarNumV1(List, int)}
     * <p>
     * NOTAS:
     * - Complejidad: O(n/2)
     */
    public static boolean buscarNumV2(final List<Integer> xs, final int num,
                                      int idx) {
        int fdx = xs.size() - 1 - idx; /* 2do índice */
        if (fdx >= idx) {
            return xs.get(idx) == num
                    || xs.get(fdx) == num
                    || buscarNumV2(xs, num, ++idx);
        }

        return false;
    }

    /**
     * Wrapper de {@link #buscarNumV2(List, int, int)}
     */
    public static boolean buscarNumV2(final List<Integer> xs, final int num) {
        return buscarNumV2(xs, num, 0);
    }
}
