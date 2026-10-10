package uniquindio.edu.co.servicio;

import uniquindio.edu.co.comparadores.ComparadorPrioridad;
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
    private Map<String, Paquete> paquetesAsignados;

    public SistemaQuindioExpress() {

        paquetesPendientes = new ArrayList<>();
        paquetesAsignados = new HashMap<>();
        paquetesPorCodigo = new HashMap<>();
        repartidoresPorIdentificacion = new HashMap<>();

        municipiosDestino = new HashSet<>();
        paquetesPorMunicipio = new HashMap<>();

        colaLlegada = new ArrayDeque<>();

        // Se configurará cuando ComparadorPrioridad esté implementado.
        colaPrioridad = new PriorityQueue<>(new ComparadorPrioridad());

        historialEntregas = new ListaSimplementeEnlazada<>();
    }

    //PAQUETES

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


        //Insertando paquetes en la cola por orden de llegada y por prioridad
        colaLlegada.offer(paquete);
        colaPrioridad.offer(paquete);


        return true;
    }


    public Paquete consultarPaquete(String codigo) {
        return paquetesPorCodigo.get(codigo);
    }

    public int cantidadPaquetesPendientes() {
        return paquetesPendientes.size();
    }

    public Set<String> obtenerMunicipios() {
        return new HashSet<>(municipiosDestino);
    }

    public List<Paquete> obtenerPaquetesPorMunicipio(String municipio) {

        if (!paquetesPorMunicipio.containsKey(municipio)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(paquetesPorMunicipio.get(municipio));
    }

    public Paquete consultarSiguientePorLlegada() {
        return colaLlegada.peek();
    }

    public Paquete consultarSiguientePorPrioridad() {
        return colaPrioridad.peek();
    }

    public boolean hayPaquetesPendientes() {
        return !paquetesPendientes.isEmpty();
    }


    // REPARTIDORES

    public boolean registrarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            return false;
        }

        if(repartidoresPorIdentificacion.containsKey(repartidor.getIdentificacion())){
            return false;
        }
        repartidoresPorIdentificacion.put(repartidor.getIdentificacion(), repartidor);
        return true;
    }

    public Repartidor consultarRepartidor(String identificacion) {
        return repartidoresPorIdentificacion.get(identificacion);
    }

    public boolean puedeAtender(Repartidor repartidor, Paquete paquete){
        if(repartidor == null || paquete == null){
            return false;
        }

        if(repartidor.isDisponible() && repartidor.getZona().equalsIgnoreCase(paquete.getDestino())){
            return true;
        }

        return false;
    }


    public boolean despacharPorLlegada(String identificacionRepartidor) {

        Repartidor repartidor = repartidoresPorIdentificacion.get(identificacionRepartidor);

        Paquete paquete = colaLlegada.peek();
        if(paquete==null){
            return false;
        }

        if (!puedeAtender(repartidor, paquete)) {
            return false;
        }

        // Evitar asignar otro paquete a un repartidor ocupado.
        if (paquetesAsignados.containsKey(identificacionRepartidor)) {
            return false;
        }

        paquetesAsignados.put(identificacionRepartidor, paquete);
        repartidor.setDisponible(false);
        colaLlegada.poll();
        colaPrioridad.remove(paquete);
        paquetesPendientes.remove(paquete);

        //Eliminando paquete del municipio
        String municipio = paquete.getDestino();
        List<Paquete> paquetesDelMunicipio = paquetesPorMunicipio.get(municipio);
        paquetesDelMunicipio.remove(paquete);

        return true;
    }


    public boolean despacharPorPrioridad(String identificacionRepartidor) {

        Repartidor repartidor = repartidoresPorIdentificacion.get(identificacionRepartidor);

        Paquete paquete = colaPrioridad.peek();

        if(paquete==null){
            return false;
        }
        if (!puedeAtender(repartidor, paquete)) {
            return false;
        }

        // Evitar asignar otro paquete a un repartidor ocupado.
        if (paquetesAsignados.containsKey(identificacionRepartidor)) {
            return false;
        }

        paquetesAsignados.put(identificacionRepartidor, paquete);
        repartidor.setDisponible(false);

        colaPrioridad.poll();
        colaLlegada.remove(paquete);
        paquetesPendientes.remove(paquete);

        // Eliminando paquete del municipio.
        String municipio = paquete.getDestino();
        List<Paquete> paquetesDelMunicipio = paquetesPorMunicipio.get(municipio);
        paquetesDelMunicipio.remove(paquete);

        return true;
    }

    public Paquete consultarPaqueteAsignado(String identificacionRepartidor) {
        return paquetesAsignados.get(identificacionRepartidor);
    }

}
