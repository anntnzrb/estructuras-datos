package club.annt.leccion;

public class Persona {
    String nombre;
    int edad;

    public Persona(final String nombre, final int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public final String getNombre() {
        return nombre;
    }

    public final int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return String.format("{%s, %d}", nombre, edad);
    }
}
