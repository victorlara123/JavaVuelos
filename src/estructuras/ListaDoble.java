/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author sonia
 */
public class ListaDoble<T> implements Iterable<T> {

    class Nodo<T> {

        Nodo sig;
        Nodo ant;
        T dato;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }

    class Iterador implements Iterator<T> {

        Nodo<T> aux = cabecera;

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
        return new Iterador();
    }
    
    private Nodo<T> cabecera;

    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        Nodo<T> temp = cabecera;
        if (cabecera == null) {
            cabecera = nuevo;
        } else {
            while (temp.sig != null) {
                temp = temp.sig;
            }
            temp.sig = nuevo;
            nuevo.ant = temp;
        }
    }
    
    public void mostrar(ListaDoble<T> a){
        while(a.cabecera.sig != null){
            System.out.println(a.cabecera.dato.toString());
            a.cabecera = a.cabecera.sig;
        }
    }
}
