package com.example.byekiloh.utilidades;

import java.util.Calendar;

public class Ejercicio {

    private int idUsuario;
    private String fecha;
    private int distancia;
    private int tiempo;
    private float velocidad;

    public Ejercicio() {  }

    public Ejercicio(int idUsuario, String fecha, int distancia, int tiempo) {

        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.distancia = distancia;
        this.tiempo = tiempo;
        setVelocidad(distancia, tiempo);

    }
    //Método para generar un id a través de una Fecha. en desuso
    /*public void setId(){
        Calendar today = Calendar.getInstance();
        int año = today.get(Calendar.YEAR);
        int mes = today.get(Calendar.MONTH)+1;
        int dia = today.get(Calendar.DATE);
        String num = String.valueOf(año).substring(2)+String.format("%02d" , mes)+String.format("%02d" , dia);
        this.idUsuario =Integer.parseInt(num);
    }*/

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int distancia, int tiempo) {
        float ms = (float) distancia / (tiempo * 60);
        velocidad = ms;
    }

    public int getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getDistancia() { return distancia; }

    public void setDistancia(int distancia) { this.distancia = distancia; }

    public int getTiempo() { return tiempo; }

    public void setTiempo(int tiempo) { this.tiempo = tiempo; }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "idUsuario: " + idUsuario +
                ", fecha: " + fecha +
                ", distancia: " + distancia +
                ", tiempo: " + tiempo +
                ", velocidad: " + velocidad +
                '}';
    }

}