package com.example.byeKiloh.objects;

import java.text.DecimalFormat;

public class Ejercicio {

    private int idEjercicio;
    private String fecha;
    private int distancia;
    private int tiempo;
    private String velocidad;
    private float consumoE;
    private String inclinacion;
    private int idUsuario;

    public Ejercicio() {  }

    public Ejercicio(String fecha, int distancia, int tiempo, String inclinacion, int idUsuario) {

        this.fecha = fecha;
        this.distancia = distancia;
        this.tiempo = tiempo;
        setVelocidad();
        this.inclinacion = inclinacion;
        this.idUsuario = idUsuario;
        //IMPLEMENTAR setConsumoE;

    }

    public Ejercicio(Ejercicio ejercicio) {

        this.idEjercicio = ejercicio.getIdEjercicio();
        this.fecha = ejercicio.getFecha();
        this.distancia = ejercicio.getDistancia();
        this.tiempo = ejercicio.getTiempo();
        this.velocidad = ejercicio.getVelocidad();
        this.consumoE = ejercicio.getConsumoE();
        this.inclinacion = ejercicio.getInclinacion();
        this.idUsuario = ejercicio.getIdUsuario();

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

    public String getVelocidad() { return velocidad; }

    public void setVelocidad() {

        DecimalFormat df = new DecimalFormat("0.0000");
        velocidad = df.format((float) distancia / (tiempo * 60));

    }

    public String getInclinacion() { return inclinacion; }

    public void setInclinacion(String inclinacion) { this.inclinacion = inclinacion; }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {

        return "- Ejercicio " + idEjercicio +
                " del " + fecha +
                " con " + distancia +
                " m en " + tiempo +
                " min\n    velocidad " + velocidad +
               // " consumoE " + consumoE +
                " inclinación " + inclinacion;

    }

}