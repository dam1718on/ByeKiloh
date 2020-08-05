package com.example.byekiloh;

public class Ejercicio {

    private int distancia;
    private int tiempo;
    private float ms;
    private float kmh;

    public Ejercicio() {
    }

    public Ejercicio(int distancia, int tiempo) {
        this.distancia = distancia;
        this.tiempo = tiempo;
        setMs(distancia, tiempo);
        setKmh(getMs());
    }

    public float getKmh() {
        return kmh;
    }

    public void setKmh(float ms) {
        kmh = ms*36/10;
    }

    public float getMs(){
        return ms;
    }

    public void setMs(int distancia, int tiempo){
        ms = (float) distancia/(tiempo*60);
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
        return "Has recorrido "+distancia+" metros en "+tiempo+" minutos haciendo una velocidad" +
                " media de "+String.format("%.2f", ms)+" m/s ó "+String.format("%.2f", kmh)+" km/h.";
    }

}