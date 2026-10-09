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
        Paquete paquete5 = new Paquete("PQ732", "Salento", 7.2, 3, 35);

        paquetes.add(paquete1);
        paquetes.add(paquete2);
        paquetes.add(paquete3);
        paquetes.add(paquete4);
        paquetes.add(paquete5);

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


        System.out.println("----- PRUEBA LISTA VACÍA -----");

        List<Paquete> paquetesVacios = new ArrayList<>();

        System.out.println("Peso total: " +
                AlgoritmosPaquetes.calcularPesoTotalPorMunicipio(
                        paquetesVacios, "Armenia"));

        System.out.println("Cantidad prioridad mínima 4: " +
                AlgoritmosPaquetes.contarPaquetesPorPrioridad(
                        paquetesVacios, 4));


        System.out.println("----- PRUEBA BÚSQUEDA BINARIA -----");

        List<Paquete> paquetesOrdenados = new ArrayList<>();

        paquetesOrdenados.add(paquete1);
        paquetesOrdenados.add(paquete2);
        paquetesOrdenados.add(paquete3);
        paquetesOrdenados.add(paquete4);

        int posicion203 = AlgoritmosPaquetes.busquedaBinaria(paquetesOrdenados, "PQ203");
        int posicion415= AlgoritmosPaquetes.busquedaBinaria(paquetesOrdenados, "PQ415");
        int posicionNoExistente = AlgoritmosPaquetes.busquedaBinaria(paquetesOrdenados, "PQ587");
        System.out.println("Posición PQ203 = " + posicion203);
        System.out.println("Posición PQ415 = " + posicion415);
        System.out.println("Posición de un paquete no existente = " + posicionNoExistente);

        int primerPaquete = AlgoritmosPaquetes.busquedaBinaria(paquetesOrdenados, "PQ101");
        int ultimoPaquete = AlgoritmosPaquetes.busquedaBinaria(paquetesOrdenados, "PQ730");
        System.out.println("Posición primer paquete = " + primerPaquete);
        System.out.println("Posición último paquete = " + ultimoPaquete);


        System.out.println("----- PRUEBA DIVIDE Y VENCERÁS -----");

        Paquete paqueteMayorPeso = AlgoritmosPaquetes.encontrarPaqueteMayorPeso(paquetes);

        System.out.println("Código: " + paqueteMayorPeso.getCodigo());
        System.out.println("Destino: " + paqueteMayorPeso.getDestino());
        System.out.println("Peso: " + paqueteMayorPeso.getPeso() + " kg");

        System.out.println("----- PRUEBA CON PAQUETES VACIOS -----");

        Paquete resultadoVacio = AlgoritmosPaquetes.encontrarPaqueteMayorPeso(paquetesVacios);

        System.out.println("Resultado de la lista vacía: " + resultadoVacio);

        System.out.println(" ----- PRUEBA CON UN SOLO PAQUETE DIVIDE Y VENCERÁS -----");
        List<Paquete> unSoloPaquete = new ArrayList<>();
        unSoloPaquete.add(paquete1);

        Paquete resultadoUnSoloPaquete= AlgoritmosPaquetes.encontrarPaqueteMayorPeso(unSoloPaquete);
        System.out.println("Único paquete: " + resultadoUnSoloPaquete.getCodigo());
        System.out.println("Peso del paquete: " + resultadoUnSoloPaquete.getPeso() + " kg");

        System.out.println(" ----- PRUEBA PAQUETES MISMO PESO DIVIDE Y VENCERÁS ----- " );
        List<Paquete> paquetesMismoPeso = new ArrayList<>();
        paquetesMismoPeso.add(paquete3);
        paquetesMismoPeso.add(paquete5);

        Paquete resultadoMismoPeso = AlgoritmosPaquetes.encontrarPaqueteMayorPeso(paquetesMismoPeso);
        System.out.println("Código del elegido: " + resultadoMismoPeso.getCodigo());
        System.out.println("Peso del elegido: " + resultadoMismoPeso.getPeso());
    }
}
