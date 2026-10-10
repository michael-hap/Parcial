package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;

public class PruebaSistemaQuindioExpress {

    public static void main(String[] args) {

        SistemaQuindioExpress sistema = new SistemaQuindioExpress();

        Paquete paquete1 = new Paquete("PQ101", "Armenia", 4.5, 3, 30);

        Paquete paquete2 = new Paquete("PQ203", "Salento", 3.0, 5, 40);

        Paquete paquete3 = new Paquete("PQ415", "Armenia", 7.2, 5, 20);

        Paquete paqueteRepetido = new Paquete("PQ101", "Calarcá", 6.0, 4, 25);

        System.out.println("----- REGISTRO DE PAQUETES -----");

        System.out.println("Registrar PQ101: " + sistema.registrarPaquete(paquete1));

        System.out.println("Registrar PQ203: " + sistema.registrarPaquete(paquete2));

        System.out.println("Registrar PQ415: " + sistema.registrarPaquete(paquete3));

        System.out.println("Registrar código repetido: " + sistema.registrarPaquete(paqueteRepetido));

        System.out.println("Cantidad de pendientes: " + sistema.cantidadPaquetesPendientes());

        System.out.println("----- CONSULTA POR CÓDIGO -----");

        Paquete encontrado = sistema.consultarPaquete("PQ203");

        System.out.println("Paquete encontrado: " + encontrado);

        System.out.println("----- MUNICIPIOS -----");

        System.out.println(sistema.obtenerMunicipios());

        System.out.println("Paquetes de Armenia: " +
                sistema.obtenerPaquetesPorMunicipio("Armenia"));

        System.out.println("----- COLAS -----");

        System.out.println("Siguiente por llegada: " + sistema.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Siguiente por prioridad: " + sistema.consultarSiguientePorPrioridad().getCodigo());


        System.out.println("----- PRUEBAS DE REPARTIDORES -----");

        Repartidor repartidor1 = new Repartidor("R101", "Carlos", "Armenia", true);
        Repartidor repartidor2 = new Repartidor("R202", "Laura", "Salento", false);
        Repartidor repartidor3 = new Repartidor("R303", "Andrés", "Armenia", true);

        System.out.println("Registrar Carlos: " + sistema.registrarRepartidor(repartidor1));

        System.out.println("Registrar Laura: " + sistema.registrarRepartidor(repartidor2));

        System.out.println("Registrar Andrés " + sistema.registrarRepartidor(repartidor3));

        System.out.println("Carlos puede atender PQ101: " + sistema.puedeAtender(repartidor1, paquete1));

        System.out.println("Laura puede atender PQ203: " + sistema.puedeAtender(repartidor2, paquete2));

        System.out.println("Carlos puede atender PQ203: " + sistema.puedeAtender(repartidor1, paquete2));

        System.out.println("Repartidor nulo: " + sistema.puedeAtender(null, paquete1));



        System.out.println("----- PRUEBA DE DESPACHO POR LLEGADA -----");

        // Verificamos el estado antes de despachar.
        System.out.println("Pendientes antes: " +
                sistema.cantidadPaquetesPendientes());

        System.out.println("Primero por llegada: " +
                sistema.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Primero por prioridad: " +
                sistema.consultarSiguientePorPrioridad().getCodigo());

        // Despachamos el primer paquete a Carlos.
        boolean primerDespacho = sistema.despacharPorLlegada("R101");

        System.out.println("Primer despacho: " + primerDespacho);

        // Comprobamos la asignación.
        Paquete paqueteAsignado =
                sistema.consultarPaqueteAsignado("R101");

        System.out.println("Paquete asignado a Carlos: " +
                paqueteAsignado.getCodigo());

        System.out.println("Carlos disponible: " +
                repartidor1.isDisponible());

        // Comprobamos las estructuras de pendientes.
        System.out.println("Pendientes después: " +
                sistema.cantidadPaquetesPendientes());

        System.out.println("Primero por llegada después: " +
                sistema.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Primero por prioridad después: " +
                sistema.consultarSiguientePorPrioridad().getCodigo());

        // Intentamos asignarle otro paquete a Carlos.
        boolean segundoDespacho = sistema.despacharPorLlegada("R101");

        System.out.println("Segundo despacho a Carlos: " +
                segundoDespacho);

        // El código despachado debe seguir registrado.
        System.out.println("PQ101 continúa registrado: " +
                (sistema.consultarPaquete("PQ101") != null));




        System.out.println("----- PRUEBA ZONA INCORRECTA -----");

        System.out.println("Despacho a Andrés: " +
                sistema.despacharPorLlegada("R303"));

        System.out.println("Pendientes: " +
                sistema.cantidadPaquetesPendientes());

        System.out.println("Primero por llegada: " +
                sistema.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Andrés disponible: " +
                repartidor3.isDisponible());



        System.out.println("----- PRUEBA DESPACHO POR PRIORIDAD -----");

        SistemaQuindioExpress sistema2 = new SistemaQuindioExpress();

        Paquete p1 = new Paquete("PQ101", "Armenia", 4.5, 3, 30);
        Paquete p2 = new Paquete("PQ203", "Salento", 3.0, 5, 40);
        Paquete p3 = new Paquete("PQ415", "Armenia", 7.2, 5, 20);

        sistema2.registrarPaquete(p1);
        sistema2.registrarPaquete(p2);
        sistema2.registrarPaquete(p3);

        Repartidor carlos2 = new Repartidor(
                "R101", "Carlos", "Armenia", true);

        sistema2.registrarRepartidor(carlos2);

        // Estado antes del despacho
        System.out.println("Primero por llegada: " +
                sistema2.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Primero por prioridad: " +
                sistema2.consultarSiguientePorPrioridad().getCodigo());

        // Despachar por prioridad
        boolean resultado = sistema2.despacharPorPrioridad("R101");

        System.out.println("Despacho exitoso: " + resultado);

        Paquete asignado = sistema2.consultarPaqueteAsignado("R101");

        System.out.println("Paquete asignado: " +
                (asignado != null ? asignado.getCodigo() : "Ninguno"));

        System.out.println("Carlos disponible: " + carlos2.isDisponible());

        // Verificar las colas después del despacho
        System.out.println("Pendientes: " +
                sistema2.cantidadPaquetesPendientes());

        System.out.println("Primero por llegada después: " +
                sistema2.consultarSiguientePorLlegada().getCodigo());

        System.out.println("Primero por prioridad después: " +
                sistema2.consultarSiguientePorPrioridad().getCodigo());

        System.out.println("Pendientes de Armenia: " +
                sistema2.obtenerPaquetesPorMunicipio("Armenia"));

        System.out.println(" ------- AHORA CON COLA VACIA -------");

        SistemaQuindioExpress sistemaVacio = new SistemaQuindioExpress();

        Repartidor repartidor = new Repartidor(
                "R500", "Pedro", "Armenia", true);

        sistemaVacio.registrarRepartidor(repartidor);

        System.out.println("Despacho con cola vacía: " +
                sistemaVacio.despacharPorPrioridad("R500"));

    }
}
