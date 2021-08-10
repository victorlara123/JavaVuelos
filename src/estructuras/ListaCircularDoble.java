/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.Comparator;
import java.util.Iterator;

public class ListaCircularDoble<T> implements Iterable<T>{
    
    class Nodo<T> {

        Nodo sig;
        Nodo ant;
        T dato;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }
    
    class IteradorPila implements Iterator<T> {

        Nodo<T> aux = cabecera;
        
        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            T dato = aux.dato;
            aux = aux.ant;
            return dato;
        }

    }

    @Override
    public Iterator<T> iterator() {
      return new IteradorPila();
    }
  
    private Nodo<T> cabecera;
   
    
    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        Nodo<T> temp = cabecera;
        if (cabecera == null) {
            cabecera = nuevo;
        } else {
            while (temp.sig != cabecera) {
                temp = temp.sig;
            }
            nuevo.ant = temp;
            nuevo.sig = cabecera;
            cabecera.ant = nuevo;
            temp.sig = nuevo;            
        }
    }
}
