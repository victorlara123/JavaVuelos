
package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Boleto implements Serializable {
    private String usuariocliente;
    private int codvuelo;

    public Boleto() {
    }

    public String getUsuariocliente() {
        return usuariocliente;
    }

    public void setUsuariocliente(String usuariocliente) {
        this.usuariocliente = usuariocliente;
    }

    public int getCodvuelo() {
        return codvuelo;
    }

    public void setCodvuelo(int codvuelo) {
        this.codvuelo = codvuelo;
    }
    
    

 
}
