
package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Paquete;

import java.util.List;

public class AlgoritmosPaquetes {

    // SUMAR RECURSIVAMENTE EL PESO DE LOS PAQUETES DE UN MUNICIPIO

    public static double calcularPesoTotalPorMunicipio(
            List<Paquete> paquetes, String municipio) {

        if (paquetes == null || municipio == null) {
            return 0;
        }

        return calcularPesoRecursivo(paquetes, municipio, 0);
    }

    private static double calcularPesoRecursivo(
            List<Paquete> paquetes, String municipio, int posicion) {

        // Caso base: se recorrieron todos los paquetes.
        if (posicion == paquetes.size()) {
            return 0;
        }

        Paquete paqueteActual = paquetes.get(posicion);

        // Caso recursivo: el destino coincide.
        if (paqueteActual != null
                && paqueteActual.getDestino().equals(municipio)) {

            return paqueteActual.getPeso()
                    + calcularPesoRecursivo(
                    paquetes, municipio, posicion + 1);
        }

        // Caso recursivo: el destino no coincide.
        return calcularPesoRecursivo(
                paquetes, municipio, posicion + 1);
    }

    // CONTAR RECURSIVAMENTE LOS PAQUETES SEGÚN SU PRIORIDAD

    public static int contarPaquetesPorPrioridad(
            List<Paquete> paquetes, int prioridadMinima) {

        if (paquetes == null) {
            return 0;
        }

        return contarPrioridadRecursivo(
                paquetes, prioridadMinima, 0, 0);
    }

    private static int contarPrioridadRecursivo(
            List<Paquete> paquetes,
            int prioridadMinima,
            int posicion,
            int acumulador) {

        // Caso base: se recorrieron todos los paquetes.
        if (posicion == paquetes.size()) {
            return acumulador;
        }

        Paquete paqueteActual = paquetes.get(posicion);

        // Contar si la prioridad alcanza el mínimo.
        if (paqueteActual != null
                && paqueteActual.getPrioridad() >= prioridadMinima) {

            return contarPrioridadRecursivo(
                    paquetes,
                    prioridadMinima,
                    posicion + 1,
                    acumulador + 1);
        }

        // Continuar sin aumentar el contador.
        return contarPrioridadRecursivo(
                paquetes,
                prioridadMinima,
                posicion + 1,
                acumulador);
    }

    // BÚSQUEDA BINARIA POR CÓDIGO

    public static int busquedaBinaria(
            List<Paquete> paquetes, String codigoBuscado) {

        if (paquetes == null || codigoBuscado == null) {
            return -1;
        }

        int izquierda = 0;
        int derecha = paquetes.size() - 1;

        while (izquierda <= derecha) {

            int mitad = izquierda + (derecha - izquierda) / 2;
            Paquete paqueteCentral = paquetes.get(mitad);

            if (paqueteCentral == null) {
                return -1;
            }

            int comparacion =
                    paqueteCentral.getCodigo().compareTo(codigoBuscado);

            if (comparacion == 0) {
                return mitad;
            } else if (comparacion < 0) {
                izquierda = mitad + 1;
            } else {
                derecha = mitad - 1;
            }
        }

        return -1;
    }

    // ENCONTRAR EL PAQUETE DE MAYOR PESO
    // MEDIANTE DIVIDE Y VENCERÁS

    public static Paquete encontrarPaqueteMayorPeso(
            List<Paquete> paquetes) {

        if (paquetes == null || paquetes.isEmpty()) {
            return null;
        }

        return encontrarMayorPesoRecursivo(
                paquetes, 0, paquetes.size() - 1);
    }

    private static Paquete encontrarMayorPesoRecursivo(
            List<Paquete> paquetes, int izquierda, int derecha) {

        // Caso base: solo queda un elemento.
        if (izquierda == derecha) {
            return paquetes.get(izquierda);
        }

        // Dividir la lista en dos partes.
        int mitad = izquierda + (derecha - izquierda) / 2;

        Paquete mayorIzquierda = encontrarMayorPesoRecursivo(
                paquetes, izquierda, mitad);

        Paquete mayorDerecha = encontrarMayorPesoRecursivo(
                paquetes, mitad + 1, derecha);

        // Comparar los resultados de ambas partes.
        if (mayorIzquierda == null) {
            return mayorDerecha;
        }

        if (mayorDerecha == null) {
            return mayorIzquierda;
        }

        if (mayorIzquierda.getPeso() >= mayorDerecha.getPeso()) {
            return mayorIzquierda;
        }

        return mayorDerecha;
    }
}
