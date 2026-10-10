package uniquindio.edu.co.modelo;

public class PruebaModelos {

    public static void main(String[] args) {

        // 1. Probar el modelo Paquete
        System.out.println("===== PRUEBA DEL MODELO PAQUETE =====");

        Paquete paquete = new Paquete(
                "PQ101", "Armenia", 5.5, 3, 30
        );

        System.out.println("Código esperado PQ101: "
                + paquete.getCodigo());
        System.out.println("Destino esperado Armenia: "
                + paquete.getDestino());
        System.out.println("Peso esperado 5.5: "
                + paquete.getPeso());
        System.out.println("Prioridad esperada 3: "
                + paquete.getPrioridad());
        System.out.println("Tiempo esperado 30: "
                + paquete.getTiempoEstimado());

        // 2. Probar validaciones de Paquete
        System.out.println("\n===== VALIDACIONES DE PAQUETE =====");

        probarPaqueteInvalido(
                "Peso cero",
                () -> new Paquete("PQ102", "Armenia", 0, 3, 30)
        );

        probarPaqueteInvalido(
                "Prioridad cero",
                () -> new Paquete("PQ103", "Armenia", 5, 0, 30)
        );

        probarPaqueteInvalido(
                "Tiempo cero",
                () -> new Paquete("PQ104", "Armenia", 5, 3, 0)
        );

        probarPaqueteInvalido(
                "Destino vacío",
                () -> new Paquete("PQ105", "", 5, 3, 30)
        );

        // 3. Probar el modelo Repartidor
        System.out.println("\n===== PRUEBA DEL MODELO REPARTIDOR =====");

        Repartidor repartidor = new Repartidor(
                "R001", "Carlos Pérez", "Armenia", true
        );

        System.out.println("Identificación esperada R001: "
                + repartidor.getIdentificacion());
        System.out.println("Nombre esperado Carlos Pérez: "
                + repartidor.getNombre());
        System.out.println("Zona esperada Armenia: "
                + repartidor.getZona());
        System.out.println("Disponible esperado true: "
                + repartidor.isDisponible());

        // 4. Probar setters de Repartidor
        System.out.println("\n===== PRUEBA DE SETTERS =====");

        repartidor.setDisponible(false);

        System.out.println("Disponible esperado false: "
                + repartidor.isDisponible());

        repartidor.setNombre("Andrés Gómez");

        System.out.println("Nombre esperado Andrés Gómez: "
                + repartidor.getNombre());

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }

    private static void probarPaqueteInvalido(
            String prueba, Runnable operacion) {

        try {
            operacion.run();
            System.out.println(prueba + ": ERROR, debía rechazar los datos");
        } catch (IllegalArgumentException excepcion) {
            System.out.println(prueba + ": CORRECTO, datos rechazados");
        }
    }
}
