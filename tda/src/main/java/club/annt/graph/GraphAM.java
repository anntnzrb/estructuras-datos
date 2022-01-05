package club.annt.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public final class GraphAM<V extends Comparable<V>> {
    /**
     * Capacidad inicial del arreglo.
     */
    public static final int INIT_CAPACITY = 10;

    /**
     * Valor inicial de los elementos de la matriz.
     */
    public static final int DEFAULT_INIT_VAL = Integer.MAX_VALUE;

    /**
     * Arreglo de vértices (elementos del grafo).
     */
    private V[] vertices;

    /**
     * Representación de las conexiones entre los vértices.
     */
    private int[][] adjMatrix;

    /**
     * Cantidad de elementos que puede almacenar el arreglo.
     */
    private int capacity;

    /**
     * Cantidad de elementos que tiene el arreglo.
     */
    private int size;

    /**
     * Define si el grafo es dirigido.
     */
    private final boolean isDirected;

    /**
     * Filtro para ordernar los elementos.
     */
    private final Comparator<V> cmp;

    /* constructores */
    @SuppressWarnings("unchecked")
    public GraphAM(final boolean isDirected, final Comparator<V> cmp) {
        this.isDirected = isDirected;
        this.cmp = cmp;

        vertices = (V[]) new Comparable[capacity = INIT_CAPACITY];
        adjMatrix = new int[capacity][capacity];
        initMatrix(adjMatrix);

        size = 0;
    }

    public GraphAM() {
        this(false, Comparator.naturalOrder());
    }

    public GraphAM(final Comparator<V> cmp) {
        this(false, cmp);
    }

    /**
     * Verifica si el arreglo está lleno.
     *
     * @return {@code true} si el arreglo está lleno,
     * {@code false} caso contrario
     */
    private boolean isFull() {
        return capacity == size;
    }

    /**
     * Inicializa la matriz bi-dimensional con un valor predeterminado.
     */
    private void initMatrix(final int[][] arr) {
        Arrays.stream(arr)
              .forEachOrdered(row -> Arrays.fill(row, DEFAULT_INIT_VAL));
    }

    /**
     * Verifica la existencia de un vértice un un grafo.
     *
     * @param data vértica a verificar
     * @return {@code true} si la data está en el grafo,
     * {@code false} caso contrario
     */
    private boolean contains(final V data) {
        return data != null && Arrays.stream(vertices)
                                     .filter(Objects::nonNull)
                                     .anyMatch(v -> cmp.compare(v, data) == 0);
    }

    /**
     * Retorna el índice del vertice pasado por parámetro.
     *
     * @param vert vértice al cual se consulta el índice
     * @return índice del vértice consultado
     */
    private int indexOf(final V vert) {
        return IntStream.range(0, size)
                        .filter(i -> cmp.compare(vertices[i], vert) == 0)
                        .findFirst()
                        .orElse(-1);
    }

    /**
     * Aumenta la capacidad del arreglo, multiplica su capacidad 1.5 veces.
     */
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private void grow() {
        final int newCap = size + (size >> 1);
        System.arraycopy(vertices, 0, vertices = (V[]) new Comparable[newCap], 0, size);

        capacity = newCap;

        growMatrix(newCap);
    }

    private int[] growVector(final int[] ints, final int capacity) {
        final int[] tmp = new int[capacity];
        System.arraycopy(ints, 0, tmp, 0, size);

        return tmp;
    }

    private void growMatrix(final int capacity) {
        final int[][] tmp = new int[capacity][capacity];
        initMatrix(tmp);

        for (int i = 0, matrixLen = adjMatrix.length; i < matrixLen; ++i) {
            tmp[i] = growVector(adjMatrix[i], capacity);
        }

        adjMatrix = tmp;
    }

    /**
     * Desplaza los nodos de la colección hacia la izquierda.
     *
     * @param idx índice del elemento del arreglo desde donde empezar a mover
     * @param arr arreglo a modificar
     * @param <T> tipo de dato del arreglo
     */
    private <T> void shiftLeft(final T[] arr, final int idx) {
        System.arraycopy(arr, idx + 1, arr, idx, size - idx);
        arr[size - 1] = null;
    }

    private void shiftLeft(final int[] arr, final int idx) {
        System.arraycopy(arr, idx + 1, arr, idx, size - idx);
        arr[size - 1] = DEFAULT_INIT_VAL;
    }

    /**
     * Agrega un vertice nuevo al grafo.
     *
     * @param vert vértice a agregar
     * @return este objeto
     */
    public GraphAM<V> add(final V vert) {
        if (vert == null || contains(vert)) { return this; }
        if (isFull()) { grow(); }

        vertices[size++] = vert;

        return this;
    }

    /**
     * Conecta dos vértices del grafo.
     *
     * @param vert1  primer vértice a conectar
     * @param vert2  segundo vértice a conectar
     * @param weight factor de peso del arco
     * @return este objeto
     */
    public GraphAM<V> connect(final V vert1,
                              final V vert2,
                              final int weight) {
        final int vert1Idx = indexOf(vert1);
        final int vert2Idx = indexOf(vert2);

        if (vert1Idx == -1 || vert2Idx == -1) { return this; }

        adjMatrix[vert1Idx][vert2Idx] = weight;

        if (!isDirected) {
            adjMatrix[vert2Idx][vert1Idx] = weight;
        }

        return this;
    }

    public GraphAM<V> connect(final V vert1, final V vert2) {
        return connect(vert1, vert2, 1);
    }

    /**
     * Desconecta dos vértices del grafo.
     *
     * @param vert1 primer vértice a desconectar
     * @param vert2 segundo vértice a desconectar
     * @return este objeto
     */
    public GraphAM<V> disconnect(final V vert1, final V vert2) {
        return connect(vert1, vert2, DEFAULT_INIT_VAL);
    }

    /**
     * Verifica si dos vérticos pasados por parámetro son adyacentes.
     *
     * @param vert1 primer vértice a comprobar
     * @param vert1 segundo vértice a comprobar
     * @return
     */
    public boolean isAdjancent(final V vert1, final V vert2) {
        final int vert1Idx = indexOf(vert1);
        final int vert2Idx = indexOf(vert2);

        return vert1Idx != -1
               && vert2Idx != -1
               && adjMatrix[vert1Idx][vert2Idx] != DEFAULT_INIT_VAL;
    }

    /**
     * Elimina un vértice existente del grafo.
     *
     * @param vert vértice a eliminar
     * @return {@code true} si los vértices son adyancetes,
     * {@code false} caso contrario
     */
    public V remove(final V vert) {
        final int vertIdx = indexOf(vert);
        if (vertIdx == -1) { return null; }

        final V oldVal = vertices[vertIdx];
        shiftLeft(vertices, vertIdx);
        shiftLeft(adjMatrix, vertIdx);

        --size;
        for (int i = 0; i < size; ++i) {
            shiftLeft(adjMatrix[i], vertIdx);
        }

        return oldVal;
    }

    /**
     * Retorna el número de conexiones de entrada que tiene un vértice.
     *
     * @param vert vértice a analizar
     * @return número de de conexiones de entrada del vértice
     */
    public int inDegree(final V vert) {
        final int vertIdx = indexOf(vert);
        if (vertIdx == -1) { return -1; }

        return (int) IntStream.range(0, size)
                              .filter(i -> adjMatrix[i][vertIdx] != DEFAULT_INIT_VAL)
                              .count();
    }

    /**
     * Retorna el número de conexiones de salida que tiene un vértice.
     *
     * @param vert vértice a analizar
     * @return número de de conexiones de salida del vértice
     */
    public int outDegree(final V vert) {
        final int vertIdx = indexOf(vert);
        if (vertIdx == -1) { return -1; }

        return (int) Arrays.stream(adjMatrix[vertIdx])
                           .filter(i -> i != DEFAULT_INIT_VAL)
                           .count();
    }

    /**
     * Imprime los vértices del grafo.
     */
    public void print() {
        Arrays.stream(vertices)
              .filter(Objects::nonNull)
              .forEach(System.out::println);
    }
}