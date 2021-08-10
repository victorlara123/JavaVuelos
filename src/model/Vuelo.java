package model;

import java.io.Serializable;


public class Vuelo implements Serializable {

    String origen;
    String destino;
    int capacidad;
    int vacante;
    float precio;
    int codigo;
    String hllegada;
    String hpartida;

    public Vuelo() {
        this.vacante=this.capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getHllegada() {
        return hllegada;
    }

    public void setHllegada(String hllegada) {
        this.hllegada = hllegada;
    }

    public String getHpartida() {
        return hpartida;
    }

    public void setHpartida(String hpartida) {
        this.hpartida = hpartida;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getVacante() {
        return vacante;
    }

    public void setVacante(int vacante) {
        this.vacante = vacante;
    }

}
