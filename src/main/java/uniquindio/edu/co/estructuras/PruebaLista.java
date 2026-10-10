package uniquindio.edu.co.estructuras;

public class PruebaLista {

    public static void main(String[] args) {

        ListaSimplementeEnlazada<Integer> lista =
                new ListaSimplementeEnlazada<>();

        // 1. Verificar lista vacía
        System.out.println("===== PRUEBA INICIAL =====");
        System.out.println("¿Está vacía?: " + lista.estaVacia());
        System.out.println("Tamaño: " + lista.tamanio());

        // 2. Agregar elementos
        System.out.println("\n===== AGREGAR ELEMENTOS =====");
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        lista.mostrarLista();
        System.out.println("Tamaño esperado: 3");
        System.out.println("Tamaño obtenido: " + lista.tamanio());

        // 3. Consultar elementos por posición
        System.out.println("\n===== CONSULTAR POSICIONES =====");
        System.out.println("Posición 0, esperado 10: " + lista.obtener(0));
        System.out.println("Posición 1, esperado 20: " + lista.obtener(1));
        System.out.println("Posición 2, esperado 30: " + lista.obtener(2));

        // 4. Recorrer usando for-each
        System.out.println("\n===== RECORRER CON FOR-EACH =====");
        for (Integer elemento : lista) {
            System.out.println(elemento);
        }

        // 5. Eliminar elemento intermedio
        System.out.println("\n===== ELIMINAR ELEMENTO INTERMEDIO =====");
        System.out.println("Eliminar 20, esperado true: "
                + lista.eliminar(20));

        lista.mostrarLista();
        System.out.println("Tamaño esperado: 2, obtenido: "
                + lista.tamanio());

        // 6. Eliminar último elemento
        System.out.println("\n===== ELIMINAR ÚLTIMO =====");
        System.out.println("Eliminar 30, esperado true: "
                + lista.eliminar(30));

        lista.mostrarLista();
        System.out.println("Tamaño esperado: 1, obtenido: "
                + lista.tamanio());

        // 7. Eliminar primer y único elemento
        System.out.println("\n===== ELIMINAR ÚNICO ELEMENTO =====");
        System.out.println("Eliminar 10, esperado true: "
                + lista.eliminar(10));

        lista.mostrarLista();
        System.out.println("¿Está vacía?: " + lista.estaVacia());
        System.out.println("Tamaño esperado: 0, obtenido: "
                + lista.tamanio());

        // 8. Eliminar de una lista vacía
        System.out.println("\n===== ELIMINAR EN LISTA VACÍA =====");
        System.out.println("Esperado false: " + lista.eliminar(50));

        // 9. Agregar después de vaciar la lista
        System.out.println("\n===== AGREGAR DESPUÉS DE VACIAR =====");
        lista.agregar(40);
        lista.agregar(50);

        lista.mostrarLista();
        System.out.println("Tamaño esperado: 2, obtenido: "
                + lista.tamanio());

        // 10. Intentar eliminar un elemento inexistente
        System.out.println("\n===== ELEMENTO INEXISTENTE =====");
        System.out.println("Esperado false: " + lista.eliminar(100));
        lista.mostrarLista();

        // 11. Probar índices inválidos
        System.out.println("\n===== ÍNDICES INVÁLIDOS =====");

        try {
            lista.obtener(-1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Índice negativo controlado: "
                    + excepcion.getMessage());
        }

        try {
            lista.obtener(5);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Índice fuera del tamaño controlado: "
                    + excepcion.getMessage());
        }

        // 12. Consultar una lista vacía
        System.out.println("\n===== CONSULTA EN LISTA VACÍA =====");
        lista.eliminar(40);
        lista.eliminar(50);

        try {
            lista.obtener(0);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Consulta en lista vacía controlada: "
                    + excepcion.getMessage());
        }

        // 13. Intentar agregar null
        System.out.println("\n===== AGREGAR NULL =====");

        try {
            lista.agregar(null);
        } catch (IllegalArgumentException excepcion) {
            System.out.println("Error controlado: "
                    + excepcion.getMessage());
        }

        // 14. Resultado final
        System.out.println("\n===== RESULTADO FINAL =====");
        lista.mostrarLista();
        System.out.println("Tamaño esperado: 0, obtenido: "
                + lista.tamanio());
        System.out.println("¿Está vacía?: " + lista.estaVacia());
    }
}
