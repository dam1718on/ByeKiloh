package com.example.byekiloh;

public class Ejercicio {

    private int distancia;
    private int tiempo;
    private float ms;

    public Ejercicio() {
    }

    public Ejercicio(int distancia, int tiempo) {
        this.distancia = distancia;
        this.tiempo = tiempo;
        ms=(distancia/tiempo)/60;
    }

    public float getMs(){
        return ms;
    }

    public void setMs(int distancia, int tiempo){
        ms=(distancia/tiempo)/60;
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
        //return distancia+"-"+tiempo+"-"+String.format("%.2f", ms)+"-"+String.format("%.2f", kmh);
        return distancia + " metros recorridos en "+tiempo+" minutos{Velocidad: "+ms+" m/s}.";
    }

}