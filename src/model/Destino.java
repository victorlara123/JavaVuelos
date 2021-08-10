package model;

public class Destino {

    private String lugar;

    public Destino() {
    }

    public Destino(String punto) {

        this.lugar = punto;

    }

    public String getPunto() {
        return lugar;
    }

    public void setPunto(String punto) {
        this.lugar = punto;
    }

    @Override
    public String toString() {

        return this.lugar;

    }

}
