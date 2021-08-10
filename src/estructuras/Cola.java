/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author sonia
 */
public class Cola<T> implements Iterable<T> {

    class Nodo<T> {

        Nodo sig;
        T dato;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }
    Nodo<T> cabeza;
    Nodo<T> ultimo;

    public void encolar(T elementoNuevo) {
        Nodo<T> nuevo = new Nodo<>(elementoNuevo);
        Nodo aux;

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            ultimo.sig = nuevo;
        }
        ultimo = nuevo;

    }

    public void decolar() {
        if (cabeza == null) {
            JOptionPane.showMessageDialog(null, "Error");
        } else {
            cabeza = cabeza.sig;

        }
    }

    public String mostrarElementos() {
        Nodo<T> aux = cabeza;
        String represenCad = "";
        while (aux != null) {
            represenCad += aux.dato + ", ";
            aux = aux.sig;
        }
        return represenCad;
    }

    @Override
    public String toString() {
        return "Pila{" + "cima=" + cabeza + '}';
    }

    class IteradorCola implements Iterator<T> {

        Nodo<T> aux = cabeza;

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
        return new IteradorCola();
    }
    
    public int size(){
        Nodo<T> p = cabeza;
        int cont = 0;
        while(p!=null){
            cont++;
            p = p.sig;
        }
        return cont;
    }
    
    public T get(int i){
        Nodo<T> p = cabeza;
        int cont = 0;
        while(p!=null && cont != i){
            cont++;
            p = p.sig;
        }
        return p.dato;
    }

}
