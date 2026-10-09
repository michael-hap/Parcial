package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;

import java.util.ArrayList;
import java.util.List;

public class PruebaAlgoritmosPaquetes {
    public static void main(String[] args) {
        List<Paquete> paquetes = new ArrayList<>();

        Paquete paquete1= new Paquete("PQ101", "Armenia", 4.5, 5, 30);
        Paquete paquete2= new Paquete("PQ203", "Salento", 3.0, 2, 40);
        Paquete paquete3= new Paquete("PQ415", "Armenia", 7.2, 4, 25);
        Paquete paquete4= new Paquete("PQ730", "Armenia", 2.3, 3, 35);

        paquetes.add(paquete1);
        paquetes.add(paquete2);
        paquetes.add(paquete3);
        paquetes.add(paquete4);

        System.out.println("----- PRUEBA DE PESO -----");

        double pesoArmenia =
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetes, "Armenia");

        double pesoSalento =
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetes, "Salento");

        System.out.println("Peso Armenia: " + pesoArmenia + " kg");
        System.out.println("Peso Salento: " + pesoSalento + " kg");


        System.out.println("----- PRUEBA DE PRIORIDAD -----");

        int cantidadPrioridad4 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(paquetes, 4);

        int cantidadPrioridad5 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(paquetes, 5);

        int cantidadPrioridad1 =
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(paquetes, 1);

        System.out.println("Prioridad mínima 4: " + cantidadPrioridad4);
        System.out.println("Prioridad mínima 5: " + cantidadPrioridad5);
        System.out.println("Prioridad mínima 1: " + cantidadPrioridad1);
    }
}
