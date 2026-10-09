package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;

import java.util.List;

public class AlgoritmosPaquetes {
    public static double calcularPesoTotalPorMunicipio(
            List<Paquete> paquetes, String municipio){
        return calcularPesoRecursivo(paquetes, municipio, 0);
    }

    private static double calcularPesoRecursivo(List<Paquete> paquetes, String municipio, int posicion){
        //Caso base
        if(posicion == paquetes.size()){
            return 0;
        }

        Paquete paqueteActual = paquetes.get(posicion);

        //Caso recursivo
        if(paqueteActual.getDestino().equals(municipio)){
            return paqueteActual.getPeso() + calcularPesoRecursivo(paquetes, municipio, posicion+1);
        }
        //Caso recursivo si el destino no coincide
        return calcularPesoRecursivo(paquetes, municipio, posicion+1);
    }

    public static int contarPaquetesPorPrioridad(
            List<Paquete>paquetes, int prioridadMinima){
        return contarPrioridadRecursivo(paquetes, prioridadMinima,0, 0);
    }

    private static int contarPrioridadRecursivo(List<Paquete>paquetes, int prioridadMinima, int posicion, int acc){
        //caso base
        if(paquetes.size()== posicion){
            return acc;
        }

        Paquete paqueteActual = paquetes.get(posicion);
        if(paqueteActual.getPrioridad()>=prioridadMinima){
            return contarPrioridadRecursivo(paquetes, prioridadMinima, posicion + 1, acc + 1);
        }else {
           return contarPrioridadRecursivo(paquetes, prioridadMinima, posicion + 1, acc);
        }
    }

    public static int busquedaBinaria(List<Paquete> paquetes, String codigoBuscado){
        int izquierda = 0;
        int derecha = paquetes.size() - 1;

        while(izquierda <= derecha){
            int mitad = izquierda + (derecha-izquierda)/2;
            Paquete paqueteCentral = paquetes.get(mitad);

            int comparacion= paqueteCentral.getCodigo().compareTo(codigoBuscado);

            if(comparacion==0){
                return mitad;
            }else if(comparacion<0){
                izquierda = mitad + 1;
            }else{
                derecha = mitad - 1;
            }
        }
        return -1;
    }

    public static Paquete encontrarPaqueteMayorPeso(List<Paquete> paquetes){

        if(paquetes==null || paquetes.isEmpty()){
            return null;
        }

        return encontrarMayorPesoRecursivo(paquetes, 0, paquetes.size()-1);
    }

    private static Paquete encontrarMayorPesoRecursivo(List<Paquete> paquetes, int izquierda, int derecha){
        //Caso base
        if(izquierda==derecha){
            return paquetes.get(izquierda);
        }

        int mitad= izquierda + (derecha-izquierda)/2;

        Paquete mayorIzquierda = encontrarMayorPesoRecursivo(paquetes, izquierda,mitad);
        Paquete mayorDerecha = encontrarMayorPesoRecursivo(paquetes, mitad + 1, derecha);
        if(mayorIzquierda.getPeso()>=mayorDerecha.getPeso()){
            return mayorIzquierda;
        }else{
            return mayorDerecha;
        }
    }
}
