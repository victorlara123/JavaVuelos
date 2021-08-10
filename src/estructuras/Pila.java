/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.Iterator;

public class Pila <T> implements Iterable<T>{
    class Nodo<T> {

        Nodo sig;
        T dato;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }
    
    Nodo<T> cima;

    public void apilar(T elementoNuevo) {
        Nodo<T> nuevo = new Nodo<>(elementoNuevo);
        nuevo.sig = cima;
        cima = nuevo;
    }

    public T desapilar() {
        T aux = cima.dato;
        cima = cima.sig;
        return aux;
    }

    public String mostrarElementos() {
        Nodo<T> aux = cima;
        String represenCad = "";
        while (aux != null) {
            represenCad += aux.dato + ", ";
            aux = aux.sig;
        }
        return represenCad;
    }

    @Override
    public String toString() {
        return "Pila{" + "cima=" + cima + '}';
    }
    
    
    class IteradorPila implements Iterator<T> {

        Nodo<T> aux = cima;
        
        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            T dato = aux.dato;
            aux = aux.sig;
            return dato;
        }

    }

    @Override
    public Iterator<T> iterator() {
      return new IteradorPila();
    }
    
    
    
    
    

}
