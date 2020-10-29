package com.example.byekiloh.utilidades;

public class Ejercicio {

    private int idEjercicio;
    private String fecha;
    private int distancia;
    private int tiempo;
    private float velocidad;
    private float consumoE;
    private int idUsuario;

    public Ejercicio() {  }

    public Ejercicio(String fecha, int distancia, int tiempo, int idUsuario) {

        this.fecha = fecha;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.idUsuario = idUsuario;
        setVelocidad(distancia, tiempo);
        //IMPLEMENTAR setConsumoE;

    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int distancia, int tiempo) {
        float ms = (float) distancia / (tiempo * 60);
        velocidad = ms;
    }

    //Getters y Setters de Consumo HAN DE IMPLEMENTARSE CORRECTAMENTE
    public float getConsumoE() { return consumoE; }

    public void setConsumoE(float consumoE) { this.consumoE = consumoE; }



    public int getIdEjercicio() {return idEjercicio; }

    public void setIdEjercicio(int idEjercicio) { this.idEjercicio = idEjercicio; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getDistancia() { return distancia; }

    public void setDistancia(int distancia) { this.distancia = distancia; }

    public int getTiempo() { return tiempo; }

    public void setTiempo(int tiempo) { this.tiempo = tiempo; }

    public int getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {

        return "Ejercicio{" +
                "idEjercicio: " + idEjercicio +
                ", fecha: " + fecha +
                ", distancia: " + distancia +
                ", tiempo: " + tiempo +
                ", velocidad: " + velocidad +
                ", consumoE: " + consumoE +
                ", idUsuario: " + idUsuario +
                '}';

    }

}