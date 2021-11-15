package club.annt.queue;

import java.util.LinkedList;
import java.util.List;

public class Repartidor implements Comparable<Repartidor> {
    private final String nombre;
    private final List<Orden> ordenes;


    public Repartidor(String nombre) {
        this.nombre = nombre;
        ordenes = new LinkedList<>();
    }

    public int sizeOrder() {
        return ordenes.size();
    }

    public void addOrder(Orden o) {
        ordenes.add(o);
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    @Override
    public int compareTo(Repartidor o) {
        return sizeOrder() - o.sizeOrder();
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("%s tiene %d órden%s",
                nombre, ordenes.size(), ordenes.size() == 1 ? "" : "es");
    }
}
