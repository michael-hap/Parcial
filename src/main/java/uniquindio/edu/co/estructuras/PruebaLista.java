package uniquindio.edu.co.estructuras;

public class PruebaLista {
    public static void main(String[] args) {
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();

        System.out.println("¿Está vacía?: " + lista.estaVacia());

        System.out.println("-----Agregando elementos a la lista-----");
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);
        System.out.println("-----Elementos agregados-----");
        lista.mostrarLista();

        System.out.println("Tamaño: " + lista.tamanio());

        System.out.println("Primer elemento: " + lista.obtener(0));
        System.out.println("Último elemento: " + lista.obtener(2));

        System.out.println("Eliminar 20: "+ lista.eliminar(30));
        System.out.println("Tamaño: " + lista.tamanio());
        lista.mostrarLista();
        System.out.println("Primer elemento: " + lista.obtener(0));
        System.out.println("último elemento: " + lista.obtener(1));
        lista.eliminar(20);

        System.out.println("Eliminar elemento inexistente:");
        System.out.println(lista.eliminar(100));
        lista.mostrarLista();

        System.out.println("Eliminar único elemento:");
        lista.eliminar(10);
        lista.mostrarLista();

        System.out.println("Tamaño final: " + lista.tamanio());
        System.out.println("¿Está vacía?: " + lista.estaVacia());


        //En la clase ListaSimplementeEnlazada se tiene que no se puede agregar elementos nulos
        //o buscar cuando la posición es mayor al tamaño

        try {
            lista.obtener(5);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Error controlado: " + excepcion.getMessage());
        }


        try {
            lista.agregar(null);
        } catch (IllegalArgumentException excepcion) {
            System.out.println("Error controlado: " + excepcion.getMessage());
        }
    }
}
