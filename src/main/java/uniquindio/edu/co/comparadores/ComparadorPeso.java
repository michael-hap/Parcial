package uniquindio.edu.co.comparadores;

import uniquindio.edu.co.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPeso implements Comparator<Paquete> {

    @Override
    public int compare(Paquete primerPaquete, Paquete segundoPaquete) {
        return Double.compare(
                segundoPaquete.getPeso(),
                primerPaquete.getPeso()
        );
    }
}
