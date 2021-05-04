package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class Ejercicio {

    private int idEjercicio, distanciaRecorrida, tiempoEmpleado;
    private float inclinacionTerreno;

    //Constructores, en orden: vacío, con todos los parámetros y copia
    public Ejercicio() {    }

    public Ejercicio(int distanciaRecorrida, int tiempoEmpleado, float inclinacionTerreno) {

        this.distanciaRecorrida = distanciaRecorrida;
        this.tiempoEmpleado = tiempoEmpleado;
        this.inclinacionTerreno = inclinacionTerreno;

    }

    public Ejercicio(Ejercicio ejercicio) {

        this.idEjercicio = ejercicio.getIdEjercicio();
        this.distanciaRecorrida = ejercicio.getDistanciaRecorrida();
        this.tiempoEmpleado = ejercicio.getTiempoEmpleado();
        this.inclinacionTerreno = ejercicio.getInclinacionTerreno();

    }

    //Getters and Setters
    public int getIdEjercicio() {  return idEjercicio;  }

    public void setIdEjercicio(int idEjercicio) {  this.idEjercicio = idEjercicio;  }

    public int getDistanciaRecorrida() {  return distanciaRecorrida;  }

    public void setDistanciaRecorrida(int distanciaRecorrida) {  this.distanciaRecorrida = distanciaRecorrida;  }

    public int getTiempoEmpleado() {  return tiempoEmpleado;  }

    public void setTiempoEmpleado(int tiempoEmpleado) {  this.tiempoEmpleado = tiempoEmpleado;  }

    public float getInclinacionTerreno() {  return inclinacionTerreno;  }

    public void setInclinacionTerreno(float inclinacionTerreno) {  this.inclinacionTerreno = inclinacionTerreno;  }

    //Método que calcula la velocidad media del Ejercicio
    public String velocidadMedia() {

        //convertimos los int en float
        float distanciaVel = (float) distanciaRecorrida;
        float tiempoVel = (float) tiempoEmpleado;
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        String format;
        format = df.format((float) (distanciaVel / 1000) / (tiempoVel / 60));
        return format;

    }

    @NotNull
    @Override
    public String toString() {

        return "Ejercicio{" +
                "idEjercicio=" + idEjercicio +
                ", distanciaRecorrida=" + distanciaRecorrida +
                ", tiempoEmpleado=" + tiempoEmpleado +
                ", inclinacionTerreno=" + inclinacionTerreno +
                '}';

    }

}