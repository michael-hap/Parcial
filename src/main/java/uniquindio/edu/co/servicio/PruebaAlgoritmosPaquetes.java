
package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;

import java.util.ArrayList;
import java.util.List;

public class PruebaAlgoritmosPaquetes {

    public static void main(String[] args) {

        List<Paquete> paquetes = new ArrayList<>();

        Paquete paquete1 = new Paquete(
                "PQ101", "Armenia", 4.5, 5, 30);

        Paquete paquete2 = new Paquete(
                "PQ203", "Salento", 3.0, 2, 40);

        Paquete paquete3 = new Paquete(
                "PQ415", "Armenia", 7.2, 4, 25);

        Paquete paquete4 = new Paquete(
                "PQ730", "Armenia", 2.3, 3, 35);

        Paquete paquete5 = new Paquete(
                "PQ732", "Salento", 7.2, 3, 35);

        paquetes.add(paquete1);
        paquetes.add(paquete2);
        paquetes.add(paquete3);
        paquetes.add(paquete4);
        paquetes.add(paquete5);

        // PRUEBA 1: PESO TOTAL POR MUNICIPIO

        System.out.println("===== PRUEBA DE PESO POR MUNICIPIO =====");

        double pesoArmenia =
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetes, "Armenia");

        double pesoSalento =
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetes, "Salento");

        double pesoMunicipioInexistente =
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetes, "Bogotá");

        System.out.println("Peso Armenia: " + pesoArmenia + " kg");
        System.out.println("Peso Salento: " + pesoSalento + " kg");
        System.out.println("Peso Bogotá: "
                + pesoMunicipioInexistente + " kg");

        // PRUEBA 2: CONTEO POR PRIORIDAD

        System.out.println("\n===== PRUEBA DE PRIORIDAD =====");

        int cantidadPrioridad4 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                        paquetes, 4);

        int cantidadPrioridad5 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                        paquetes, 5);

        int cantidadPrioridad1 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                        paquetes, 1);

        int cantidadPrioridad3 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                        paquetes, 3);

        System.out.println("Prioridad mínima 4: "
                + cantidadPrioridad4);

        System.out.println("Prioridad mínima 5: "
                + cantidadPrioridad5);

        System.out.println("Prioridad mínima 1: "
                + cantidadPrioridad1);

        System.out.println("Prioridad mínima 3: "
                + cantidadPrioridad3);

        // PRUEBA 3: LISTA VACÍA

        System.out.println("\n===== PRUEBA CON LISTA VACÍA =====");

        List<Paquete> paquetesVacios = new ArrayList<>();

        System.out.println("Peso total: "
                + AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                paquetesVacios, "Armenia"));

        System.out.println("Cantidad con prioridad mínima 4: "
                + AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                paquetesVacios, 4));

        // PRUEBA 4: BÚSQUEDA BINARIA

        System.out.println("\n===== PRUEBA DE BÚSQUEDA BINARIA =====");

        // Los códigos deben estar ordenados ascendentemente.
        List<Paquete> paquetesOrdenados = new ArrayList<>();

        paquetesOrdenados.add(paquete1); // PQ101
        paquetesOrdenados.add(paquete2); // PQ203
        paquetesOrdenados.add(paquete3); // PQ415
        paquetesOrdenados.add(paquete4); // PQ730

        int posicion101 =
                AlgoritmosPaquetes.busquedaBinaria(
                        paquetesOrdenados, "PQ101");

        int posicion203 =
                AlgoritmosPaquetes.busquedaBinaria(
                        paquetesOrdenados, "PQ203");

        int posicion415 =
                AlgoritmosPaquetes.busquedaBinaria(
                        paquetesOrdenados, "PQ415");

        int posicion730 =
                AlgoritmosPaquetes.busquedaBinaria(
                        paquetesOrdenados, "PQ730");

        int posicionNoExistente =
                AlgoritmosPaquetes.busquedaBinaria(
                        paquetesOrdenados, "PQ587");

        System.out.println("Posición PQ101: " + posicion101);
        System.out.println("Posición PQ203: " + posicion203);
        System.out.println("Posición PQ415: " + posicion415);
        System.out.println("Posición PQ730: " + posicion730);

        System.out.println("Posición PQ587 (no existe): "
                + posicionNoExistente);

        System.out.println("Búsqueda en lista vacía: "
                + AlgoritmosPaquetes.busquedaBinaria(
                paquetesVacios, "PQ101"));

        System.out.println("Búsqueda con código nulo: "
                + AlgoritmosPaquetes.busquedaBinaria(
                paquetesOrdenados, null));

        // PRUEBA 5: DIVIDE Y VENCERÁS

        System.out.println("\n===== PRUEBA DIVIDE Y VENCERÁS =====");

        Paquete paqueteMayorPeso =
                AlgoritmosPaquetes.encontrarPaqueteMayorPeso(
                        paquetes);

        if (paqueteMayorPeso != null) {
            System.out.println("Código: "
                    + paqueteMayorPeso.getCodigo());

            System.out.println("Destino: "
                    + paqueteMayorPeso.getDestino());

            System.out.println("Peso: "
                    + paqueteMayorPeso.getPeso() + " kg");
        } else {
            System.out.println("No hay paquetes registrados.");
        }

        // PRUEBA 6: LISTA VACÍA EN DIVIDE Y VENCERÁS

        System.out.println("\n===== PRUEBA DE MAYOR PESO CON LISTA VACÍA =====");

        Paquete resultadoVacio =
                AlgoritmosPaquetes.encontrarPaqueteMayorPeso(
                        paquetesVacios);

        System.out.println("Resultado: " + resultadoVacio);

        // PRUEBA 7: UN SOLO PAQUETE

        System.out.println("\n===== PRUEBA CON UN SOLO PAQUETE =====");

        List<Paquete> unSoloPaquete = new ArrayList<>();
        unSoloPaquete.add(paquete1);

        Paquete resultadoUnSoloPaquete =
                AlgoritmosPaquetes.encontrarPaqueteMayorPeso(
                        unSoloPaquete);

        if (resultadoUnSoloPaquete != null) {
            System.out.println("Único paquete: "
                    + resultadoUnSoloPaquete.getCodigo());

            System.out.println("Peso del paquete: "
                    + resultadoUnSoloPaquete.getPeso() + " kg");
        }

        // PRUEBA 8: DOS PAQUETES CON EL MISMO PESO

        System.out.println("\n===== PRUEBA DE PAQUETES CON EL MISMO PESO =====");

        List<Paquete> paquetesMismoPeso = new ArrayList<>();

        paquetesMismoPeso.add(paquete3);
        paquetesMismoPeso.add(paquete5);

        Paquete resultadoMismoPeso =
                AlgoritmosPaquetes.encontrarPaqueteMayorPeso(
                        paquetesMismoPeso);

        if (resultadoMismoPeso != null) {
            System.out.println("Código del elegido: "
                    + resultadoMismoPeso.getCodigo());

            System.out.println("Peso del elegido: "
                    + resultadoMismoPeso.getPeso() + " kg");
        }

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }
}
