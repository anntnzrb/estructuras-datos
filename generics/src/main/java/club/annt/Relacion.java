package club.annt;

/**
 * Crear una clase que permita representar la relación entre dos
 * entidades cualesquiera.
 *
 * @param <T> Entidad 1
 * @param <U> Entidad 2
 */
public class Relacion<T, U> {
    /* vars */
    private T      e1; /* entidad 1 */
    private U      e2; /* entidad 1 */
    private String desc;

    /* constructores */
    private Relacion() { }

    Relacion(final T e1, final U e2, final String desc) {
        this.e1 = e1;
        this.e2 = e2;
        this.desc = desc;
    }

    /* boilerplate */
    public final T getE1() {
        return e1;
    }

    public final void setE1(final T e1) {
        this.e1 = e1;
    }

    public final U getE2() {
        return e2;
    }

    public final void setE2(final U e2) {
        this.e2 = e2;
    }

    public final String getDesc() {
        return desc;
    }

    public final void setDesc(final String desc) {
        this.desc = desc;
    }
}
