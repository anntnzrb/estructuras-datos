package club.annt.graph;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class GraphAL<V extends Comparable<V>, E> {
    private final List<Vertex<V, E>> vertices;
    private final boolean            isDirected;
    private final Comparator<V>      cmp;

    /* constructores */
    GraphAL(final Comparator<V> cmp, final boolean isDirected) {
        this.isDirected = isDirected;
        this.cmp = cmp;

        vertices = new LinkedList<>();
    }

    GraphAL(final Comparator<V> cmp) {
        this(cmp, false);
    }

    GraphAL(final boolean isDirected) {
        this(Comparator.naturalOrder(), isDirected);
    }

    GraphAL() {
        this(Comparator.naturalOrder(), false);
    }

    /**
     * Retorna el vértice que contiene la data del vértice pasado por parámetro.
     *
     * @param vert data del vértice pasado
     * @return el vértice de la data pasada
     */
    private Vertex<V, E> getVertexByContent(final V vert) {
        return vertices.stream()
                       .filter(Objects::nonNull) // TODO :: check
                       .filter(v -> cmp.compare(v.getContent(), vert) == 0)
                       .findFirst()
                       .orElse(null);
    }

    /**
     * Agrega un nuevo vértice al grafo.
     *
     * @param vert vértice a agregar
     * @return éste objeto
     */
    public GraphAL<V, E> add(final V vert) {
        if (vert == null || getVertexByContent(vert) != null) { return this; }

        vertices.add(new Vertex<>(vert));

        return this;
    }

    /**
     * Conecta dos vértices en el grafo.
     *
     * @param source primer vértice a conectar
     * @param target segundo vértice a conectar
     * @param data   valor del vértice
     * @param weight factor de peso del vértice
     * @return éste objeto
     */
    public GraphAL<V, E> connect(final V source,
                                 final V target,
                                 final E data,
                                 final int weight) {
        if (source == null || target == null) { return this; }

        final Vertex<V, E> sourceVert = getVertexByContent(source);
        final Vertex<V, E> targetVert = getVertexByContent(target);
        if (sourceVert == null || targetVert == null) { return this; }

        sourceVert.getEdges().add(new Edge<>(sourceVert, targetVert, data, weight));

        if (!isDirected) {
            targetVert.getEdges().add(new Edge<>(targetVert, sourceVert, data, weight));
        }

        return this;
    }

    /**
     * Wrapper de {@link #connect(Comparable, Comparable, Object, int)}.
     */
    public GraphAL<V, E> connect(final V vert1,
                                 final V vert2) {
        return connect(vert1, vert2, null, 1);
    }

    /**
     * Desconecta dos vértices del grafo.
     *
     * @param source primer vértice a descconectar
     * @param target segundo vértice a desconectar
     * @return éste objeto
     */
    public GraphAL<V, E> disconnect(final V source, final V target) {
        if (source == null || target == null) { return this; }

        final Vertex<V, E> sourceVertex = getVertexByContent(source);
        final Vertex<V, E> targetVertex = getVertexByContent(target);
        if (sourceVertex == null || targetVertex == null) { return this; }

        sourceVertex.getEdges()
                    .removeIf(e -> cmp.compare(e.getTarget().getContent(), targetVertex.getContent()) == 0);

        if (!isDirected) {
            targetVertex.getEdges()
                        .removeIf(e -> cmp.compare(e.getTarget().getContent(), sourceVertex.getContent()) == 0);
        }

        return this;
    }

    /**
     * Retorna una lista con las componentes conexas del grafo.
     *
     * @return lista de caminos del grafo
     */
    public List<List<V>> getPaths() {
        return vertices.stream()
                       .filter(v -> !v.isVisited())
                       .map(v -> bfs(v.getContent()))
                       .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean isRelated() {
        return getPaths().size() == 1;
    }

    /**
     * Remueve un vértice del grafo.
     *
     * @param vert vértice a eliminar
     * @return éste objeto
     */
    public GraphAL<V, E> remove(final V vert) {
        if (vert == null) { return this; }

        final Vertex<V, E> vertex = getVertexByContent(vert);
        if (vertex == null) { return this; }

        /*
         * recorrer la lista de adyacencia y remover las conexiones que tengan
         * como target el vértice que se va a remover.
         */
        vertices.forEach(v -> v.getEdges().removeIf(e -> cmp.compare(vert, e.getTarget().getContent()) == 0));

        /* remover vértice de la lista de vértices */
        vertices.remove(vertex);

        return this;
    }

    /**
     * "Breadth First Search" (BFS) -> "Búsqueda en anchura".
     *
     * @param vert vértice desde donde empezar la búsqueda
     * @return lista de vértices encontradados
     */
    private List<V> bfs(final V vert) {
        if (vert == null || getVertexByContent(vert) == null) { return null; }

        final List<V> listVertexContent = new LinkedList<>();
        final Queue<Vertex<V, E>> vertQueue = new ArrayDeque<>();
        vertQueue.offer(getVertexByContent(vert));

        while (true) {
            final boolean isQueueEmpty = vertQueue.isEmpty();
            if (isQueueEmpty) { break; }

            final Vertex<V, E> vertex = vertQueue.poll();
            if (!vertex.isVisited()) {
                vertex.setVisited(true);
                listVertexContent.add(vertex.getContent());
                vertex.getEdges()
                      .forEach(e -> vertQueue.offer(e.getTarget()));
            }
        }

        return listVertexContent;
    }

    /**
     * Imprime en pantalla el grafo usando el algoritmo "BFS"
     *
     * @param vert vértice desde donde imprimir
     */
    public void printBFS(final V vert) {
        bfs(vert).forEach(System.out::println);
        resetVisited();
    }

    /**
     * Wrapper de {@link #printBFS(Comparable)}.
     */
    public void printBFS() {
        printBFS(vertices.get(0).getContent());
    }

    /**
     * "Depth First Search" (DFS) -> "Búsqueda en profundidad".
     *
     * @param vert vértice desde donde empezar la búsqueda
     * @return lista de vértices encontradados
     */
    private List<V> dfs(final V vert) {
        if (vert == null || getVertexByContent(vert) == null) { return null; }

        final List<V> listVertexContent = new LinkedList<>();
        final Deque<Vertex<V, E>> stack = new ArrayDeque<>();
        stack.push(getVertexByContent(vert));

        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) { break; }

            final Vertex<V, E> vertex = stack.pop();
            if (!vertex.isVisited()) {
                vertex.setVisited(true);
                listVertexContent.add(vertex.getContent());
                vertex.getEdges()
                      .forEach(e -> stack.push(e.getTarget()));
            }
        }

        return listVertexContent;
    }

    /**
     * Imprime en pantalla el grafo usando el algoritmo "DFS".
     *
     * @param vert vértice desde donde imprimir
     */
    public void printDFS(final V vert) {
        dfs(vert).forEach(System.out::println);
        resetVisited();
    }

    /**
     * Wrapper de {@link #printDFS(Comparable)}.
     */
    public void printDFS() {
        printDFS(vertices.get(0).getContent());
    }

    /**
     * Re-establece vértices como no visitados.
     */
    private void resetVisited() {
        vertices.forEach(v -> v.setVisited(false));
    }

    /* ************************************************************************
     * clases extras
     * ********************************************************************* */

    /**
     * Clase "Vértice".
     * <p>
     * Un vértice tiene una lista de arcos y una data asociada
     *
     * @param <V>
     * @param <E>
     */
    private static final class Vertex<V, E> {
        private V                content;
        private boolean          isVisited;
        private List<Edge<V, E>> edges;

        /* constructores */
        Vertex() { }

        Vertex(final V data) {
            content = data;
            isVisited = false;
            edges = new LinkedList<>();
        }

        public V getContent() {
            return content;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(final boolean visited) {
            isVisited = visited;
        }

        public List<Edge<V, E>> getEdges() {
            return edges;
        }
    }

    /**
     * Clase "Arco".
     * <p>
     * Un arco tiene un vértice de origin y uno de destino, así mismo
     * contiene el factor de peso y un valor asociado.
     *
     * @param <V>
     * @param <E>
     */
    private static final class Edge<V, E> {
        private final Vertex<V, E> source;
        private final Vertex<V, E> target;
        private final E            data;
        private final int          weight;

        /* constructores */
        Edge(final Vertex<V, E> source,
             final Vertex<V, E> target) {
            this(source, target, null, 1);
        }

        Edge(final Vertex<V, E> source,
             final Vertex<V, E> target,
             final E data,
             final int weight) {
            this.source = source;
            this.target = target;
            this.data = data;
            this.weight = weight;
        }

        public Vertex<V, E> getTarget() {
            return target;
        }
    }
}