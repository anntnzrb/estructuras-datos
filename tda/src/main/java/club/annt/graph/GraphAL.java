package club.annt.graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class GraphAL<V extends Comparable<V>, E> {
    private List<Vertex<V, E>> vertices;
    private boolean            isDirected;
    private Comparator<V>      cmp;

    /* constructores */
    private GraphAL() { }

    public GraphAL(final Comparator<V> cmp, final boolean isDirected) {
        vertices = new LinkedList<>();
        this.isDirected = isDirected;
        this.cmp = cmp;
    }

    public GraphAL(final Comparator<V> cmp) {
        this(cmp, false);
    }

    private Vertex<V, E> getVertexByContent(final V vert) {
        return vertices.stream()
                       .filter(v -> cmp.compare(v.getData(), vert) == 0)
                       .findFirst()
                       .orElse(null);
    }

    public GraphAL<V, E> add(final V vert) {
        if (vert == null || getVertexByContent(vert) != null) { return this; }

        vertices.add(new Vertex<V, E>(vert));

        return this;
    }

    public GraphAL<V, E> connect(final V vert1,
                                 final V vert2,
                                 final E data,
                                 final int weight) {
        if (vert1 == null || vert2 == null) { return this; }

        final Vertex<V, E> newVert1 = getVertexByContent(vert1);
        final Vertex<V, E> newVert2 = getVertexByContent(vert2);
        if (newVert1 == null || newVert2 == null) { return this; }

        newVert1.getEdges().add(new Edge<>(newVert1, newVert2, data, weight));

        if (!isDirected) {
            newVert2.getEdges().add(new Edge<>(newVert2, newVert1, data, weight));
        }

        return this;
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
        private List<Edge<V, E>> edges;
        private V                data;

        /* constructores */
        Vertex() { }

        Vertex(final V data) {
            this.data = data;
            edges = new LinkedList<>();
        }

        public V getData() {
            return data;
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
        private Vertex<V, E> source;
        private Vertex<V, E> target;
        private E            data;
        private int          weight;

        /* constructores */
        Edge() { }

        Edge(final Vertex<V, E> source,
             final Vertex<V, E> target,
             final E data,
             final int weight) {
            this.source = source;
            this.target = target;
            this.data = data;
            this.weight = weight;
        }
    }
}
