
package uniquindio.edu.co.servicio;

import uniquindio.edu.co.comparadores.ComparadorPrioridad;
import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;
import uniquindio.edu.co.estructuras.ListaSimplementeEnlazada;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class SistemaQuindioExpress {

    // Paquetes y repartidores
    private List<Paquete> paquetesRegistrados;
    private List<Paquete> paquetesPendientes;
    private Map<String, Paquete> paquetesPorCodigo;
    private Map<String, Repartidor> repartidoresPorIdentificacion;

    // Municipios
    private Set<String> municipiosDestino;
    private Set<String> municipiosOrdenados;
    private Map<String, List<Paquete>> paquetesPorMunicipio;

    // Colas de atención
    private Queue<Paquete> colaLlegada;
    private PriorityQueue<Paquete> colaPrioridad;

    // Historial y asignaciones
    private ListaSimplementeEnlazada<Paquete> historialEntregas;
    private Map<String, Paquete> paquetesAsignados;

    public SistemaQuindioExpress() {

        paquetesRegistrados = new ArrayList<>();
        paquetesPendientes = new ArrayList<>();
        paquetesPorCodigo = new HashMap<>();
        repartidoresPorIdentificacion = new HashMap<>();

        municipiosDestino = new HashSet<>();
        municipiosOrdenados = new TreeSet<>();
        paquetesPorMunicipio = new HashMap<>();

        colaLlegada = new ArrayDeque<>();
        colaPrioridad = new PriorityQueue<>(
                new ComparadorPrioridad()
        );

        historialEntregas = new ListaSimplementeEnlazada<>();
        paquetesAsignados = new HashMap<>();
    }

    // GESTIÓN DE PAQUETES

    public boolean registrarPaquete(Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        if (paquetesPorCodigo.containsKey(paquete.getCodigo())) {
            return false;
        }


        paquetesRegistrados.add(paquete);

        paquetesPendientes.add(paquete);
        paquetesPorCodigo.put(paquete.getCodigo(), paquete);


        municipiosDestino.add(paquete.getDestino());
        municipiosOrdenados.add(paquete.getDestino());

        // Agrupar paquetes por municipio.
        String municipio = paquete.getDestino();

        if (!paquetesPorMunicipio.containsKey(municipio)) {
            paquetesPorMunicipio.put(municipio, new ArrayList<>());
        }

        paquetesPorMunicipio.get(municipio).add(paquete);

        // Incorporar el paquete a las dos colas.
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

    public boolean hayPaquetesPendientes() {
        return !paquetesPendientes.isEmpty();
    }

    public List<Paquete> obtenerPaquetesRegistrados() {
        return new ArrayList<>(paquetesRegistrados);
    }

    public List<Paquete> obtenerPaquetesPendientes() {
        return new ArrayList<>(paquetesPendientes);
    }

    public int cantidadPaquetesPorDespachar() {
        return colaLlegada.size();
    }

    // GESTIÓN DE MUNICIPIOS

    public Set<String> obtenerMunicipios() {
        return new HashSet<>(municipiosDestino);
    }

    public Set<String> obtenerMunicipiosOrdenados() {
        return new TreeSet<>(municipiosOrdenados);
    }

    public List<Paquete> obtenerPaquetesPorMunicipio(
            String municipio) {

        if (municipio == null
                || !paquetesPorMunicipio.containsKey(municipio)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(
                paquetesPorMunicipio.get(municipio)
        );
    }

    // CONSULTA DE COLAS

    public Paquete consultarSiguientePorLlegada() {
        return colaLlegada.peek();
    }

    public Paquete consultarSiguientePorPrioridad() {
        return colaPrioridad.peek();
    }

    // GESTIÓN DE REPARTIDORES

    public boolean registrarRepartidor(Repartidor repartidor) {

        if (repartidor == null) {
            return false;
        }

        if (repartidoresPorIdentificacion.containsKey(
                repartidor.getIdentificacion())) {
            return false;
        }

        repartidoresPorIdentificacion.put(
                repartidor.getIdentificacion(), repartidor);

        return true;
    }

    public Repartidor consultarRepartidor(String identificacion) {
        return repartidoresPorIdentificacion.get(identificacion);
    }

    public boolean puedeAtender(
            Repartidor repartidor,
            Paquete paquete) {

        if (repartidor == null || paquete == null) {
            return false;
        }

        return repartidor.isDisponible()
                && repartidor.getZona().equalsIgnoreCase(
                paquete.getDestino()
        );
    }

    // DESPACHO POR ORDEN DE LLEGADA

    public boolean despacharPorLlegada(String identificacionRepartidor) {

        Repartidor repartidor = repartidoresPorIdentificacion.get(identificacionRepartidor);

        Paquete paquete = colaLlegada.peek();

        if (paquete == null) {
            return false;
        }

        if (!puedeAtender(repartidor, paquete)) {
            return false;
        }

        if (paquetesAsignados.containsKey(
                identificacionRepartidor)) {
            return false;
        }

        // Registrar la asignación.
        paquetesAsignados.put(
                identificacionRepartidor,
                paquete
        );

        repartidor.setDisponible(false);

        // Retirar el paquete de las colas de despacho.
        colaLlegada.poll();
        colaPrioridad.remove(paquete);

        // Se conserva en el registro de pendientes hasta
        // que se confirme la entrega.
        return true;
    }

    // DESPACHO POR PRIORIDAD

    public boolean despacharPorPrioridad(String identificacionRepartidor) {

        Repartidor repartidor = repartidoresPorIdentificacion.get(identificacionRepartidor);

        Paquete paquete = colaPrioridad.peek();

        if (paquete == null) {
            return false;
        }

        if (!puedeAtender(repartidor, paquete)) {
            return false;
        }

        if (paquetesAsignados.containsKey(
                identificacionRepartidor)) {
            return false;
        }


        paquetesAsignados.put(
                identificacionRepartidor,
                paquete
        );

        repartidor.setDisponible(false);

        // Retirar el paquete de las colas de despacho.
        colaPrioridad.poll();
        colaLlegada.remove(paquete);

        return true;
    }

    // CONSULTA DE ASIGNACIONES

    public Paquete consultarPaqueteAsignado(
            String identificacionRepartidor) {

        return paquetesAsignados.get(
                identificacionRepartidor
        );
    }

    // REGISTRO DE ENTREGA

    public boolean registrarEntrega(String identificacionRepartidor) {

        Repartidor repartidor =
                repartidoresPorIdentificacion.get(identificacionRepartidor);

        Paquete paquete = paquetesAsignados.get(identificacionRepartidor);


        if (repartidor == null || paquete == null) {
            return false;
        }


        historialEntregas.agregar(paquete);

        paquetesPendientes.remove(paquete);

        String municipio = paquete.getDestino();

        List<Paquete> paquetesDelMunicipio =
                paquetesPorMunicipio.get(municipio);

        if (paquetesDelMunicipio != null) {
            paquetesDelMunicipio.remove(paquete);
        }

        paquetesAsignados.remove(identificacionRepartidor);
        repartidor.setDisponible(true);

        return true;
    }

    // CONSULTA DEL HISTORIAL

    public List<Paquete> consultarHistorialEntregas() {

        List<Paquete> historial = new ArrayList<>();

        for (Paquete paquete : historialEntregas) {
            historial.add(paquete);
        }

        return historial;
    }

    public List<Paquete> consultarRegistradosPorMunicipio(String municipio) {

        List<Paquete> resultado = new ArrayList<>();

        for (Paquete paquete : paquetesRegistrados) {

            if (paquete.getDestino().equalsIgnoreCase(municipio)) {
                resultado.add(paquete);
            }
        }

        return resultado;
    }
}

