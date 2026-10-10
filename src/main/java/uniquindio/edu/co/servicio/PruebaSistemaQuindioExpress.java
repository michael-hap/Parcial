
package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;

public class PruebaSistemaQuindioExpress {

    public static void main(String[] args) {

        SistemaQuindioExpress sistema = new SistemaQuindioExpress();

        Paquete paquete1 = new Paquete("PQ101", "Armenia", 4.5, 3, 30);
        Paquete paquete2 = new Paquete("PQ203", "Salento", 3.0, 5, 40);
        Paquete paquete3 = new Paquete("PQ415", "Armenia", 7.2, 5, 20);

        Paquete paqueteRepetido =
                new Paquete("PQ101", "Calarcá", 6.0, 4, 25);

        System.out.println("===== SISTEMA QUINDIO EXPRESS =====");

        // REGISTRO DE PAQUETES
        System.out.println("\n----- REGISTRO DE PAQUETES -----");

        System.out.println("Registrar PQ101: "
                + sistema.registrarPaquete(paquete1));

        System.out.println("Registrar PQ203: "
                + sistema.registrarPaquete(paquete2));

        System.out.println("Registrar PQ415: "
                + sistema.registrarPaquete(paquete3));

        System.out.println("Registrar código repetido: "
                + sistema.registrarPaquete(paqueteRepetido));

        System.out.println("Cantidad de pendientes: "
                + sistema.cantidadPaquetesPendientes());

        // CONSULTA POR CÓDIGO
        System.out.println("\n----- CONSULTA POR CÓDIGO -----");

        Paquete encontrado = sistema.consultarPaquete("PQ203");

        System.out.println("Paquete encontrado: "
                + (encontrado != null ? encontrado : "No encontrado"));

        System.out.println("Código inexistente: "
                + sistema.consultarPaquete("PQ999"));

        // MUNICIPIOS
        System.out.println("\n----- MUNICIPIOS -----");

        System.out.println("Municipios registrados: "
                + sistema.obtenerMunicipios());

        System.out.println("Paquetes de Armenia: "
                + sistema.obtenerPaquetesPorMunicipio("Armenia"));

        System.out.println("Paquetes de municipio inexistente: "
                + sistema.obtenerPaquetesPorMunicipio("Bogotá"));

        // COLAS
        System.out.println("\n----- COLAS -----");

        Paquete primeroLlegada = sistema.consultarSiguientePorLlegada();
        Paquete primeroPrioridad = sistema.consultarSiguientePorPrioridad();

        System.out.println("Siguiente por llegada: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        System.out.println("Siguiente por prioridad: "
                + (primeroPrioridad != null
                ? primeroPrioridad.getCodigo() : "Cola vacía"));

        // REGISTRO DE REPARTIDORES
        System.out.println("\n----- PRUEBAS DE REPARTIDORES -----");

        Repartidor repartidor1 =
                new Repartidor("R101", "Carlos", "Armenia", true);

        Repartidor repartidor2 =
                new Repartidor("R202", "Laura", "Salento", false);

        Repartidor repartidor3 =
                new Repartidor("R303", "Andrés", "Armenia", true);

        System.out.println("Registrar Carlos: "
                + sistema.registrarRepartidor(repartidor1));

        System.out.println("Registrar Laura: "
                + sistema.registrarRepartidor(repartidor2));

        System.out.println("Registrar Andrés: "
                + sistema.registrarRepartidor(repartidor3));

        System.out.println("Carlos puede atender PQ101: "
                + sistema.puedeAtender(repartidor1, paquete1));

        System.out.println("Laura puede atender PQ203: "
                + sistema.puedeAtender(repartidor2, paquete2));

        System.out.println("Carlos puede atender PQ203: "
                + sistema.puedeAtender(repartidor1, paquete2));

        System.out.println("Repartidor nulo: "
                + sistema.puedeAtender(null, paquete1));

        // COMPROBAR ZONA CON UN REPARTIDOR DISPONIBLE
        Repartidor repartidorSalento =
                new Repartidor("R404", "María", "Salento", true);

        System.out.println("María puede atender PQ203: "
                + sistema.puedeAtender(repartidorSalento, paquete2));

        // DESPACHO POR LLEGADA
        System.out.println("\n----- PRUEBA DE DESPACHO POR LLEGADA -----");

        System.out.println("Pendientes antes: "
                + sistema.cantidadPaquetesPendientes());

        primeroLlegada = sistema.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        primeroPrioridad = sistema.consultarSiguientePorPrioridad();

        System.out.println("Primero por prioridad: "
                + (primeroPrioridad != null
                ? primeroPrioridad.getCodigo() : "Cola vacía"));

        boolean primerDespacho =
                sistema.despacharPorLlegada("R101");

        System.out.println("Primer despacho: " + primerDespacho);

        Paquete paqueteAsignado =
                sistema.consultarPaqueteAsignado("R101");

        System.out.println("Paquete asignado a Carlos: "
                + (paqueteAsignado != null
                ? paqueteAsignado.getCodigo() : "Ninguno"));

        System.out.println("Carlos disponible: "
                + repartidor1.isDisponible());

        System.out.println("Pendientes después del despacho: "
                + sistema.cantidadPaquetesPendientes());

        primeroLlegada = sistema.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada después: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        primeroPrioridad = sistema.consultarSiguientePorPrioridad();

        System.out.println("Primero por prioridad después: "
                + (primeroPrioridad != null
                ? primeroPrioridad.getCodigo() : "Cola vacía"));

        // INTENTAR DESPACHAR A UN REPARTIDOR OCUPADO
        boolean segundoDespacho =
                sistema.despacharPorLlegada("R101");

        System.out.println("Segundo despacho a Carlos: "
                + segundoDespacho);

        System.out.println("PQ101 continúa registrado: "
                + (sistema.consultarPaquete("PQ101") != null));

        // CONFIRMAR ENTREGA
        System.out.println("\n----- PRUEBA DE ENTREGA -----");

        boolean entrega = sistema.registrarEntrega("R101");

        System.out.println("Entrega registrada: " + entrega);

        System.out.println("Pendientes después de la entrega: "
                + sistema.cantidadPaquetesPendientes());

        System.out.println("Carlos disponible nuevamente: "
                + sistema.consultarRepartidor("R101").isDisponible());

        System.out.println("Historial de entregas: "
                + sistema.consultarHistorialEntregas());

        // DESPACHO CON ZONA INCORRECTA
        System.out.println("\n----- PRUEBA DE ZONA INCORRECTA -----");

        primeroLlegada = sistema.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        System.out.println("Despacho a Andrés: "
                + sistema.despacharPorLlegada("R303"));

        System.out.println("Pendientes: "
                + sistema.cantidadPaquetesPendientes());

        primeroLlegada = sistema.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada después del intento: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        System.out.println("Andrés disponible: "
                + repartidor3.isDisponible());

        // DESPACHO POR PRIORIDAD EN OTRO SISTEMA
        System.out.println("\n----- PRUEBA DE DESPACHO POR PRIORIDAD -----");

        SistemaQuindioExpress sistema2 = new SistemaQuindioExpress();

        Paquete p1 = new Paquete("PQ101", "Armenia", 4.5, 3, 30);
        Paquete p2 = new Paquete("PQ203", "Salento", 3.0, 5, 40);
        Paquete p3 = new Paquete("PQ415", "Armenia", 7.2, 5, 20);

        sistema2.registrarPaquete(p1);
        sistema2.registrarPaquete(p2);
        sistema2.registrarPaquete(p3);

        Repartidor carlos2 =
                new Repartidor("R101", "Carlos", "Armenia", true);

        sistema2.registrarRepartidor(carlos2);

        primeroLlegada = sistema2.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        primeroPrioridad = sistema2.consultarSiguientePorPrioridad();

        System.out.println("Primero por prioridad: "
                + (primeroPrioridad != null
                ? primeroPrioridad.getCodigo() : "Cola vacía"));

        boolean resultado =
                sistema2.despacharPorPrioridad("R101");

        System.out.println("Despacho exitoso: " + resultado);

        Paquete asignado =
                sistema2.consultarPaqueteAsignado("R101");

        System.out.println("Paquete asignado: "
                + (asignado != null
                ? asignado.getCodigo() : "Ninguno"));

        System.out.println("Carlos disponible: "
                + carlos2.isDisponible());

        System.out.println("Pendientes: "
                + sistema2.cantidadPaquetesPendientes());

        primeroLlegada = sistema2.consultarSiguientePorLlegada();

        System.out.println("Primero por llegada después: "
                + (primeroLlegada != null
                ? primeroLlegada.getCodigo() : "Cola vacía"));

        primeroPrioridad = sistema2.consultarSiguientePorPrioridad();

        System.out.println("Primero por prioridad después: "
                + (primeroPrioridad != null
                ? primeroPrioridad.getCodigo() : "Cola vacía"));

        System.out.println("Pendientes de Armenia: "
                + sistema2.obtenerPaquetesPorMunicipio("Armenia"));

        // COLA VACÍA
        System.out.println("\n----- PRUEBA CON COLA VACÍA -----");

        SistemaQuindioExpress sistemaVacio =
                new SistemaQuindioExpress();

        Repartidor repartidorVacio =
                new Repartidor("R500", "Pedro", "Armenia", true);

        sistemaVacio.registrarRepartidor(repartidorVacio);

        System.out.println("Despacho con cola vacía: "
                + sistemaVacio.despacharPorPrioridad("R500"));

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }
}
