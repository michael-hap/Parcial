
package uniquindio.edu.co.modelo;

public class Repartidor {

    private String identificacion;
    private String nombre;
    private String zona;
    private boolean disponible;

    public Repartidor(String identificacion, String nombre,
                      String zona, boolean disponible) {
        setIdentificacion(identificacion);
        setNombre(nombre);
        setZona(zona);
        this.disponible = disponible;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null
                || identificacion.isEmpty()) {
            throw new IllegalArgumentException(
                    "La identificación no puede ser nula ni vacía");
        }
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede ser nulo ni vacío");
        }
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        if (zona == null || zona.isEmpty()) {
            throw new IllegalArgumentException(
                    "La zona no puede ser nula ni vacía");
        }
        this.zona = zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", zona='" + zona + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
