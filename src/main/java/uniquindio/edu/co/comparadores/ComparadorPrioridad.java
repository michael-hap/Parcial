
package uniquindio.edu.co.comparadores;

import uniquindio.edu.co.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPrioridad implements Comparator<Paquete> {

    @Override
    public int compare(Paquete primerPaquete, Paquete segundoPaquete) {

        // Primero: prioridad de mayor a menor.
        int comparacionPrioridad = Integer.compare(
                segundoPaquete.getPrioridad(),
                primerPaquete.getPrioridad()
        );

        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }

        // Segundo: tiempo estimado de menor a mayor.
        int comparacionTiempo = Integer.compare(
                primerPaquete.getTiempoEstimado(),
                segundoPaquete.getTiempoEstimado()
        );

        if (comparacionTiempo != 0) {
            return comparacionTiempo;
        }

        // Tercero: código en orden ascendente.
        return primerPaquete.getCodigo().compareTo(
                segundoPaquete.getCodigo()
        );
    }
}
