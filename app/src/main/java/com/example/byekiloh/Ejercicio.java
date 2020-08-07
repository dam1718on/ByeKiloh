package com.example.byekiloh;

public class Ejercicio {

    private int distancia;
    private int tiempo;

    public Ejercicio() {
    }

    public Ejercicio(int distancia, int tiempo) {
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "distancia=" + distancia +
                ", tiempo=" + tiempo +
                '}';
    }

}