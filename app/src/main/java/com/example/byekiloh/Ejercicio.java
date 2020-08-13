package com.example.byekiloh;

import java.util.Calendar;

public class Ejercicio {

    private int id;
    private int fecha;
    private int distancia;
    private int tiempo;

    public Ejercicio() {  }

    public Ejercicio(int distancia, int tiempo) {
        this.distancia = distancia;
        this.tiempo = tiempo;
        fecha();
    }

    public Ejercicio(int fecha, int distancia, int tiempo) {
        this.fecha = fecha;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public void fecha(){
        Calendar today = Calendar.getInstance();
        int año = today.get(Calendar.YEAR);
        int mes = today.get(Calendar.MONTH)+1;
        int dia = today.get(Calendar.DATE);
        String num = String.valueOf(año).substring(2)+String.format("%02d" , mes)+String.format("%02d" , dia);
        this.fecha=Integer.parseInt(num);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getFecha(){
        return fecha;
    }

    public void setFecha(int fecha) { this.fecha = fecha; }

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
    public String toString() { return "Día "+fecha+", con "+distancia+" metros en "+tiempo+" segundos"; }

}