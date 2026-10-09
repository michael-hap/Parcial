
package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;
import uniquindio.edu.co.estructuras.ListaSimplementeEnlazada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SistemaQuindioExpress {

    private List<Paquete> paquetesPendientes;
    private Map<String, Paquete> paquetesPorCodigo;
    private Map<String, Repartidor> repartidoresPorIdentificacion;

    private Set<String> municipiosDestino;
    private Map<String, List<Paquete>> paquetesPorMunicipio;

    private Queue<Paquete> colaLlegada;
    private PriorityQueue<Paquete> colaPrioridad;

    private ListaSimplementeEnlazada<Paquete> historialEntregas;

    public SistemaQuindioExpress() {

        paquetesPendientes = new ArrayList<>();

        paquetesPorCodigo = new HashMap<>();
        repartidoresPorIdentificacion = new HashMap<>();

        municipiosDestino = new HashSet<>();
        paquetesPorMunicipio = new HashMap<>();

        colaLlegada = new ArrayDeque<>();

        // Se configurará cuando ComparadorPrioridad esté implementado.
        colaPrioridad = null;

        historialEntregas = new ListaSimplementeEnlazada<>();
    }

    public boolean registrarPaquete(Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        if (paquetesPorCodigo.containsKey(paquete.getCodigo())) {
            return false;
        }

        paquetesPendientes.add(paquete);

        paquetesPorCodigo.put(paquete.getCodigo(), paquete);

        municipiosDestino.add(paquete.getDestino());



        //Poniendo un municipio como clave en caso de que no exista esa clave
        // si existe solo se agrega el paquete
        String municipio = paquete.getDestino();
        if (!paquetesPorMunicipio.containsKey(municipio)) {
            paquetesPorMunicipio.put(municipio, new ArrayList<>());
        }
        paquetesPorMunicipio.get(municipio).add(paquete);

        /*
        //Insertando elementos en la cola
        colaLlegada.offer(paquete);
        colaPrioridad.offer(paquete);

         */
        return true;
    }
}
