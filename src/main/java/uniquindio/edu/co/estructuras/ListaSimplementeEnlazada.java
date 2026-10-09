package uniquindio.edu.co.estructuras;

import java.util.Iterator;

public class ListaSimplementeEnlazada<T> implements Coleccion<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamanio;

    public ListaSimplementeEnlazada(){
        this.inicio= null;
        this.fin= null;
        this.tamanio= 0;
    }
    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);

        if(estaVacia()){
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        tamanio++;
    }

    @Override
    public boolean eliminar(T elemento) {
        if( estaVacia()){
            return false;
        }
        if(inicio.getDato().equals(elemento)){
            inicio=inicio.getSiguiente();
            tamanio--;

            if(inicio== null){
                fin = null;
            }
            return true;
        }
        Nodo<T> actual = inicio.getSiguiente();
        Nodo<T> anterior = inicio;

        while(actual != null){
            if(actual.getDato().equals(elemento)){
                if(actual == fin){
                    fin = anterior;
                    actual=actual.getSiguiente();
                    anterior.setSiguiente(actual);
                }else {
                    actual = actual.getSiguiente();
                    anterior.setSiguiente(actual);
                }

                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public T obtener(int posicion) {
        return null;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }

    @Override
    public boolean estaVacia() {
        return tamanio == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
