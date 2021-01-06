package com.example.byeKiloh.objects;

import java.text.DecimalFormat;

public class Ejercicio {

    private int idEjercicio, distancia, tiempo, idUsuario;
    private String fecha, velocidad, inclinacion;
    private float consumoE;

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

    public int getIdEjercicio() { return idEjercicio; }

    public void setIdEjercicio(int idEjercicio) { this.idEjercicio = idEjercicio; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getDistancia() { return distancia; }

    public void setDistancia(int distancia) { this.distancia = distancia; }

    public int getTiempo() { return tiempo; }

    public void setTiempo(int tiempo) { this.tiempo = tiempo; }

    public String getVelocidad() { return velocidad; }

    public void setVelocidad() {
        //convertimos los int en float
        float distanciaVel = new Float(distancia);
        float tiempoVel = new Float(tiempo);
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        velocidad = df.format((float) (distanciaVel / 1000) / (tiempoVel / 60));
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
                " km/h inclinación " + inclinacion;
    }

}