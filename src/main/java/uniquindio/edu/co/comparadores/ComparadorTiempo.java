
package uniquindio.edu.co.comparadores;

import uniquindio.edu.co.modelo.Paquete;
import java.util.Comparator;

public class ComparadorTiempo implements Comparator<Paquete> {

    @Override
    public int compare(Paquete primerPaquete, Paquete segundoPaquete) {
        return Integer.compare(
                primerPaquete.getTiempoEstimado(),
                segundoPaquete.getTiempoEstimado()
        );
    }
}
