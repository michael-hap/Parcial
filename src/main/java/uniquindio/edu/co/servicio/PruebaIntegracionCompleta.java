package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;
import uniquindio.edu.co.modelo.Repartidor;

public class PruebaIntegracionCompleta {

    public static void main(String[] args) {

        SistemaQuindioExpress sistema = new SistemaQuindioExpress();

        System.out.println("===== PRUEBA DE INTEGRACION =====");

        // 1. Registrar 30 paquetes en 5 municipios
        System.out.println("\n1. REGISTRO DE PAQUETES");

        String[] municipios = {
                "Armenia", "Salento", "Calarca", "Circasia", "Montenegro"
        };

        int paquetesRegistrados = 0;

        for (int i = 1; i <= 30; i++) {

            String codigo = String.format("PQ%03d", i);
            String destino = municipios[(i - 1) % municipios.length];

            Paquete paquete = new Paquete(
                    codigo,
                    destino,
                    1.0 + i,
                    (i % 5) + 1,
                    20 + i
            );

            if (sistema.registrarPaquete(paquete)) {
                paquetesRegistrados++;
            }
        }

        System.out.println("Paquetes registrados: "
                + paquetesRegistrados);
        System.out.println("Total esperado: 30");
        System.out.println("Pendientes: "
                + sistema.cantidadPaquetesPendientes());
        System.out.println("Municipios registrados: "
                + sistema.obtenerMunicipiosOrdenados());

        // 2. Intentar registrar un paquete duplicado
        System.out.println("\n2. CODIGO DUPLICADO");

        Paquete duplicado = new Paquete(
                "PQ001", "Armenia", 3.0, 3, 25
        );

        System.out.println("Debe ser false: "
                + sistema.registrarPaquete(duplicado));

        System.out.println("Cantidad esperada: 30");
        System.out.println("Cantidad obtenida: "
                + sistema.cantidadPaquetesPendientes());

        // 3. Registrar seis repartidores
        System.out.println("\n3. REGISTRO DE REPARTIDORES");

        for (int i = 1; i <= 6; i++) {

            String identificacion = String.format("R%03d", i);
            String nombre = "Repartidor " + i;
            String zona = municipios[(i - 1) % municipios.length];

            Repartidor repartidor = new Repartidor(
                    identificacion,
                    nombre,
                    zona,
                    true
            );

            System.out.println("Registrar " + identificacion
                    + ": " + sistema.registrarRepartidor(repartidor));
        }

        System.out.println("Repartidor R001: "
                + sistema.consultarRepartidor("R001"));
        System.out.println("Identificación inexistente: "
                + sistema.consultarRepartidor("R999"));

        // 4. Probar una identificación duplicada
        System.out.println("\n4. IDENTIFICACION DUPLICADA");

        Repartidor repetido = new Repartidor(
                "R001", "Otro repartidor", "Armenia", true
        );

        System.out.println("Debe ser false: "
                + sistema.registrarRepartidor(repetido));

        // 5. Probar un despacho con zona incorrecta
        System.out.println("\n5. DESPACHO CON ZONA INCORRECTA");

        Repartidor repartidorIncorrecto = new Repartidor(
                "R007", "Repartidor de prueba", "Pereira", true
        );

        sistema.registrarRepartidor(repartidorIncorrecto);

        System.out.println("Paquete siguiente por llegada: "
                + sistema.consultarSiguientePorLlegada());

        System.out.println("Despacho esperado false: "
                + sistema.despacharPorLlegada("R007"));

        System.out.println("Pendientes esperados: 30");
        System.out.println("Pendientes obtenidos: "
                + sistema.cantidadPaquetesPendientes());

        // 6. Despachar correctamente según la zona del primer paquete
        System.out.println("\n6. DESPACHO CORRECTO");

        Paquete primero = sistema.consultarSiguientePorLlegada();

        Repartidor repartidorCorrecto = new Repartidor(
                "R008",
                "Repartidor compatible",
                primero.getDestino(),
                true
        );

        sistema.registrarRepartidor(repartidorCorrecto);

        boolean despacho = sistema.despacharPorLlegada("R008");

        System.out.println("Despacho esperado true: " + despacho);
        System.out.println("Paquete asignado: "
                + sistema.consultarPaqueteAsignado("R008"));

        System.out.println("Repartidor disponible esperado false: "
                + repartidorCorrecto.isDisponible());

        // 7. Intentar despachar otra vez al mismo repartidor
        System.out.println("\n7. REPARTIDOR OCUPADO");

        System.out.println("Segundo despacho esperado false: "
                + sistema.despacharPorLlegada("R008"));

        // 8. Confirmar entrega
        System.out.println("\n8. CONFIRMAR ENTREGA");

        System.out.println("Entrega esperada true: "
                + sistema.registrarEntrega("R008"));

        System.out.println("Repartidor disponible esperado true: "
                + repartidorCorrecto.isDisponible());

        System.out.println("Pendientes esperados: 29");
        System.out.println("Pendientes obtenidos: "
                + sistema.cantidadPaquetesPendientes());

        System.out.println("Entregas en historial esperado: 1");
        System.out.println("Entregas en historial obtenidas: "
                + sistema.consultarHistorialEntregas().size());

        // 9. Consultar paquete por código
        System.out.println("\n9. CONSULTA POR CODIGO");

        System.out.println("Paquete existente PQ001: "
                + sistema.consultarPaquete("PQ001"));

        System.out.println("Paquete inexistente PQ999: "
                + sistema.consultarPaquete("PQ999"));

        // 10. Resultado final
        System.out.println("\n===== FIN DE LA PRUEBA =====");
    }
}