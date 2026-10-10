
package uniquindio.edu.co;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;
import uniquindio.edu.co.servicio.SistemaQuindioExpress;

public class Main {

    public static void main(String[] args) {

        SistemaQuindioExpress sistema = new SistemaQuindioExpress();

        System.out.println("====================================");
        System.out.println("     SISTEMA QUINDIO EXPRESS");
        System.out.println("====================================");

        // 1. Registrar 30 paquetes con codigos no consecutivos
        System.out.println("\n1. REGISTRO DE PAQUETES");

        String[] codigos = {
                "PQ731", "PQ105", "PQ942", "PQ318", "PQ567",
                "PQ829", "PQ214", "PQ684", "PQ450", "PQ913",
                "PQ126", "PQ875", "PQ392", "PQ641", "PQ258",
                "PQ704", "PQ183", "PQ956", "PQ327", "PQ510",
                "PQ842", "PQ469", "PQ137", "PQ625", "PQ298",
                "PQ781", "PQ354", "PQ906", "PQ172", "PQ538"
        };

        String[] municipios = {
                "Armenia", "Salento", "Calarca", "Circasia", "Montenegro"
        };

        int registrados = 0;

        for (int i = 0; i < codigos.length; i++) {

            Paquete paquete = new Paquete(
                    codigos[i],
                    municipios[i % municipios.length],
                    1.0 + i,
                    (i % 5) + 1,
                    20 + i
            );

            if (sistema.registrarPaquete(paquete)) {
                registrados++;
            }
        }

        System.out.println("Paquetes registrados: " + registrados);
        System.out.println("Paquetes pendientes: "
                + sistema.cantidadPaquetesPendientes());

        // 2. Validar codigo duplicado
        System.out.println("\n2. VALIDACION DE CODIGO DUPLICADO");

        Paquete duplicado = new Paquete(
                "PQ731", "Armenia", 4.0, 3, 30
        );

        System.out.println("Registrar PQ731 nuevamente: "
                + sistema.registrarPaquete(duplicado));

        // 3. Consultar un paquete
        System.out.println("\n3. CONSULTA POR CODIGO");

        System.out.println("Paquete PQ731: "
                + sistema.consultarPaquete("PQ731"));

        System.out.println("Paquete inexistente PQ999: "
                + sistema.consultarPaquete("PQ999"));

        // 4. Consultar municipios
        System.out.println("\n4. MUNICIPIOS");

        System.out.println("Municipios sin ordenar: "
                + sistema.obtenerMunicipios());

        System.out.println("Municipios ordenados: "
                + sistema.obtenerMunicipiosOrdenados());

        System.out.println("Paquetes destinados a Armenia: "
                + sistema.obtenerPaquetesPorMunicipio("Armenia"));

        // 5. Registrar seis repartidores
        System.out.println("\n5. REGISTRO DE REPARTIDORES");

        for (int i = 1; i <= 6; i++) {

            String identificacion = String.format("R%03d", i);

            Repartidor repartidor = new Repartidor(
                    identificacion,
                    "Repartidor " + i,
                    municipios[(i - 1) % municipios.length],
                    true
            );

            System.out.println("Registrar " + identificacion + ": "
                    + sistema.registrarRepartidor(repartidor));
        }

        System.out.println("Total de repartidores iniciales: 6");

        // 6. Consultar orden de llegada y prioridad
        System.out.println("\n6. CONSULTA DE COLAS");

        System.out.println("Siguiente por llegada: "
                + sistema.consultarSiguientePorLlegada());

        System.out.println("Siguiente por prioridad: "
                + sistema.consultarSiguientePorPrioridad());

        // 7. Despachar por prioridad usando un repartidor compatible
        System.out.println("\n7. DESPACHO POR PRIORIDAD");

        boolean despachoRealizado = false;
        String repartidorAsignado = null;

        Paquete paquetePrioritario =
                sistema.consultarSiguientePorPrioridad();

        if (paquetePrioritario != null) {

            for (int i = 1; i <= 6; i++) {

                String identificacion = String.format("R%03d", i);
                Repartidor repartidor =
                        sistema.consultarRepartidor(identificacion);

                if (repartidor != null
                        && repartidor.isDisponible()
                        && repartidor.getZona().equalsIgnoreCase(
                        paquetePrioritario.getDestino())) {

                    despachoRealizado =
                            sistema.despacharPorPrioridad(identificacion);

                    if (despachoRealizado) {
                        repartidorAsignado = identificacion;
                    }

                    break;
                }
            }
        }

        System.out.println("Despacho realizado: " + despachoRealizado);

        if (despachoRealizado) {

            System.out.println("Repartidor asignado: "
                    + repartidorAsignado);

            System.out.println("Paquete asignado: "
                    + sistema.consultarPaqueteAsignado(
                    repartidorAsignado));

            System.out.println("Repartidor disponible: "
                    + sistema.consultarRepartidor(
                    repartidorAsignado).isDisponible());

            // 8. Confirmar entrega
            System.out.println("\n8. CONFIRMAR ENTREGA");

            boolean entrega = sistema.registrarEntrega(repartidorAsignado);

            System.out.println("Entrega registrada: " + entrega);

            System.out.println("Repartidor disponible nuevamente: "
                    + sistema.consultarRepartidor(
                    repartidorAsignado).isDisponible());
        } else {
            System.out.println(
                    "No se encontro un repartidor disponible "
                            + "para el paquete prioritario."
            );
        }

        // 9. Mostrar estado final
        System.out.println("\n9. ESTADO FINAL");

        System.out.println("Paquetes pendientes: "
                + sistema.cantidadPaquetesPendientes());

        System.out.println("Historial de entregas: "
                + sistema.consultarHistorialEntregas());

        System.out.println("\n====================================");
        System.out.println("          FIN DEL PROGRAMA");
        System.out.println("====================================");
    }
}
