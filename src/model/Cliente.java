package model;



import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
    int numerocuenta;
    

   
    
    
    
    public Cliente(){
    }

    public int getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(int numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

   
    
}
