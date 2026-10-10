package uniquindio.edu.co.comparadores;

import uniquindio.edu.co.modelo.Paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PruebaComparadores {

    public static void main(String[] args) {

        // Crear paquetes para las pruebas
        Paquete paquete1 = new Paquete("PQ101", "Armenia", 8.0, 3, 40);
        Paquete paquete2 = new Paquete("PQ203", "Salento", 4.0, 5, 30);
        Paquete paquete3 = new Paquete("PQ415", "Calarcá", 7.0, 5, 20);

        List<Paquete> paquetes = new ArrayList<>();
        paquetes.add(paquete1);
        paquetes.add(paquete2);
        paquetes.add(paquete3);

        // Mostrar orden inicial
        System.out.println("===== ORDEN INICIAL =====");
        mostrarPaquetes(paquetes);

        // 1. Probar Comparable: código ascendente
        System.out.println("\n===== ORDEN POR CÓDIGO =====");
        Collections.sort(paquetes);
        mostrarPaquetes(paquetes);

        // 2. Probar ComparadorPrioridad
        System.out.println("\n===== ORDEN POR PRIORIDAD =====");
        paquetes.sort(new ComparadorPrioridad());
        mostrarPaquetes(paquetes);

        // 3. Probar ComparadorPeso
        System.out.println("\n===== ORDEN POR PESO =====");
        paquetes.sort(new ComparadorPeso());
        mostrarPaquetes(paquetes);

        // 4. Probar ComparadorTiempo
        System.out.println("\n===== ORDEN POR TIEMPO =====");
        paquetes.sort(new ComparadorTiempo());
        mostrarPaquetes(paquetes);
    }

    private static void mostrarPaquetes(List<Paquete> paquetes) {
        for (Paquete paquete : paquetes) {
            System.out.println(
                    "Código: " + paquete.getCodigo()
                            + " | Prioridad: " + paquete.getPrioridad()
                            + " | Peso: " + paquete.getPeso()
                            + " | Tiempo: " + paquete.getTiempoEstimado()
            );
        }
    }
}
