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
public class ArbolBinario <T extends Comparable> implements Iterable<T> {

    private class NodoBinario<T> {

        NodoBinario izq;
        NodoBinario der;
        T dato;

        public NodoBinario(T dato) {
            this.dato = dato;
        }
    }

    private class Iterador implements Iterator<T> {

        NodoBinario<T> aux;

        public Iterador() {
            aux = raiz;
        }

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            
            if (aux.izq !=null) {
                aux = aux.izq;
            }
            else{
                aux = aux.der;
            }           
            T dato = aux.dato;
            return dato;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    private NodoBinario<T> raiz;
    private Comparator<T> comparador = (T o1, T o2) -> o1.compareTo(o2);

    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public boolean insertarValor(T nuevo) {
        NodoBinario<T> nuevoNodo = new NodoBinario<>(nuevo);
        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            NodoBinario<T> aux = raiz;
            NodoBinario<T> ant = raiz;
            while (aux != null) {
                if (comparador.compare(nuevo, aux.dato) > 0) {
                    ant = aux;
                    aux = aux.der;
                } else if (comparador.compare(nuevo, aux.dato) < 0) {
                    ant = aux;
                    aux = aux.izq;
                } else if (comparador.compare(nuevo, aux.dato) == 0) {
                    return false;
                }
            }

            if (comparador.compare(nuevo, ant.dato) > 0) {
                ant.der = nuevoNodo;
            } else if (comparador.compare(nuevo, ant.dato) < 0) {
                ant.izq = nuevoNodo;
            }
        }
        return true;
    }

    private boolean insertarValorRecursivo(T nuevo, NodoBinario<T> subarbol) {
        NodoBinario<T> nuevoNodo = new NodoBinario<>(nuevo);
        if (raiz == null) {
            raiz = nuevoNodo;

        } else {
            if (comparador.compare(nuevo, subarbol.dato) < 0) {
                if (subarbol.izq == null) {
                    subarbol.izq = nuevoNodo;

                } else {
                    insertarValorRecursivo(nuevo, subarbol.izq);
                }
            } else {
                if (comparador.compare(nuevo, subarbol.dato) == 0) {
                    return false;
                } else {

                    if (subarbol.der == null) {
                        subarbol.der = nuevoNodo;

                    } else {
                        insertarValorRecursivo(nuevo, subarbol.der);
                    }
                }
            }
        }
        return true;
    }

    public boolean insertarValorR(T nuevo) {
        NodoBinario<T> aux = raiz;
        return insertarValorRecursivo(nuevo, aux);

    }

    public void preOrden() {
        NodoBinario<T> aux = raiz;
        preOrdenRecursivo(aux);
    }

    public void inOrden() {
        NodoBinario<T> aux = raiz;
        inOrdenRecursivo(aux);
    }

    public void postOrden() {
        NodoBinario<T> aux = raiz;
        postOrdenRecursivo(aux);
    }

    private void preOrdenRecursivo(NodoBinario<T> subarbol) {
        if (subarbol != null) {
            System.out.println(subarbol.dato);
            preOrdenRecursivo(subarbol.izq);
            preOrdenRecursivo(subarbol.der);
        }
    }

    private void inOrdenRecursivo(NodoBinario<T> subarbol) {
        if (subarbol != null) {
            inOrdenRecursivo(subarbol.izq);
            System.out.println(subarbol.dato);
            inOrdenRecursivo(subarbol.der);
        }
    }

    private void postOrdenRecursivo(NodoBinario<T> subarbol) {
        if (subarbol != null) {
            postOrdenRecursivo(subarbol.izq);
            postOrdenRecursivo(subarbol.der);
            System.out.println(subarbol.dato);
        }
    }

    

}
