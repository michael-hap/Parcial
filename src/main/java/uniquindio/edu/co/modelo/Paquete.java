
package uniquindio.edu.co.modelo;

public class Paquete implements Comparable<Paquete> {

    private String codigo;
    private String destino;
    private double peso;
    private int prioridad;
    private int tiempoEstimado;

    public Paquete(String codigo, String destino, double peso,
                   int prioridad, int tiempoEstimado) {
        setCodigo(codigo);
        setDestino(destino);
        setPeso(peso);
        setPrioridad(prioridad);
        setTiempoEstimado(tiempoEstimado);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException(
                    "El código no puede ser nulo ni vacío");
        }
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException(
                    "El destino no puede ser nulo ni vacío");
        }
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0 || Double.isNaN(peso)
                || Double.isInfinite(peso)) {
            throw new IllegalArgumentException(
                    "El peso debe ser positivo y finito");
        }
        this.peso = peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException(
                    "La prioridad debe estar entre 1 y 5");
        }
        this.prioridad = prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException(
                    "El tiempo estimado debe ser positivo");
        }
        this.tiempoEstimado = tiempoEstimado;
    }

    @Override
    public int compareTo(Paquete otro) {
        return codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "codigo='" + codigo + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }
}