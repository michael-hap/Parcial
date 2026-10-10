package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;

public class PruebaEstadosPaquetes {
    public static void main(String[] args) {
        SistemaQuindioExpress sistema = new SistemaQuindioExpress();
        Paquete p1 = new Paquete("PQ731", "Armenia", 4.5, 3, 30);
        Paquete p2 = new Paquete("PQ105", "Salento", 3.0, 5, 25);
        sistema.registrarPaquete(p1);
        sistema.registrarPaquete(p2);
        sistema.registrarRepartidor(new Repartidor("R101", "Carlos", "Armenia", true));

        verificar("Inicial: dos pendientes de entrega", sistema.cantidadPaquetesPendientes() == 2);
        verificar("Inicial: dos por despachar", sistema.cantidadPaquetesPorDespachar() == 2);
        verificar("Despacho PQ731 por llegada", sistema.despacharPorLlegada("R101"));
        verificar("Despachado: aun pendiente de entrega", sistema.cantidadPaquetesPendientes() == 2);
        verificar("Despachado: solo uno por despachar", sistema.cantidadPaquetesPorDespachar() == 1);
        verificar("Asignacion a Carlos", sistema.consultarPaqueteAsignado("R101") == p1);
        verificar("Historial vacio antes de entregar", sistema.consultarHistorialEntregas().isEmpty());
        verificar("Entrega confirmada", sistema.registrarEntrega("R101"));
        verificar("Despues de entregar: un pendiente", sistema.cantidadPaquetesPendientes() == 1);
        verificar("Historial contiene PQ731", sistema.consultarHistorialEntregas().get(0) == p1);
        verificar("El codigo no se reutiliza", !sistema.registrarPaquete(new Paquete("PQ731", "Armenia", 5, 2, 20)));
        verificar("Orden original de auditoria", sistema.obtenerPaquetesRegistrados().size() == 2
                && sistema.obtenerPaquetesRegistrados().get(0) == p1
                && sistema.obtenerPaquetesRegistrados().get(1) == p2);
        System.out.println("TODAS LAS PRUEBAS DE ESTADO PASARON");
    }

    private static void verificar(String descripcion, boolean correcto) {
        if (!correcto) {
            throw new AssertionError("FALLO: " + descripcion);
        }
        System.out.println("OK: " + descripcion);
    }
}
