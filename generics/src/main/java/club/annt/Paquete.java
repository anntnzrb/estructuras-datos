package club.annt;

/**
 * Contenedor (caja) que almacena distintos tipos de items.
 *
 * @param <T> Item
 */
public class Paquete<T> {
    /* vars */
    private T item;

    /* constructores */
    public Paquete() {
    }

    Paquete(final T item) {
        this.item = item;
    }

    /* boilerplate */
    public final T getItem() {
        return item;
    }

    public final void setItem(final T item) {
        this.item = item;
    }
}
