
package uniquindio.edu.co.comparadores;

import uniquindio.edu.co.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPrioridad implements Comparator<Paquete> {

    @Override
    public int compare(Paquete primerPaquete, Paquete segundoPaquete) {

        int comparacionPrioridad = Integer.compare(
                segundoPaquete.getPrioridad(),
                primerPaquete.getPrioridad()
        );

        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }

        int comparacionTiempo = Integer.compare(
                primerPaquete.getTiempoEstimado(),
                segundoPaquete.getTiempoEstimado()
        );

        if (comparacionTiempo != 0) {
            return comparacionTiempo;
        }

        return primerPaquete.getCodigo().compareTo(
                segundoPaquete.getCodigo()
        );
    }
}
