package club.annt.leccion.ejii;

public final class Distancia {
    private final String ciudadOrigin;
    private final int    distancia;

    public Distancia(final String ciudadOrigin, final int distancia) {
        this.ciudadOrigin = ciudadOrigin;
        this.distancia = distancia;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getCiudadOrigin() {
        return ciudadOrigin;
    }

    @Override
    public String toString() {
        return ciudadOrigin + " " + distancia;
    }
}
