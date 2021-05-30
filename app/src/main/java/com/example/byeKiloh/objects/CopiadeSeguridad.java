package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.Date;

public class CopiadeSeguridad {

    private int idCopiadeSeguridad;
    private Timestamp fechaSubida;
    private String pathCopia;
    private Cuenta esGuardada;


    //Constructores, en orden: vacío, con parámetros y copia
    public CopiadeSeguridad() {

        esGuardada = new Cuenta();

    }

    public CopiadeSeguridad(Timestamp fechaSubida, String pathCopia, Cuenta esGuardada) {

        this.fechaSubida = fechaSubida;
        this.pathCopia = pathCopia;
        this.esGuardada = esGuardada;

    }

    public CopiadeSeguridad(CopiadeSeguridad copiadeSeguridad) {

        this.idCopiadeSeguridad = copiadeSeguridad.getIdCopiadeSeguridad();
        this.fechaSubida = copiadeSeguridad.getFechaSubida();
        this.pathCopia = copiadeSeguridad.getPathCopia();
        //this.archivoSQLite = copiadeSeguridad.getArchivoSQLite();
        //this.esGuardada = copiadeSeguridad.getEsGuardada();

    }

    //Métodos propios

    //Inicializa la fechaSubida con el CURRENT_TIMESTAMP
    public void initFechaSubida() {

        Date date = new Date();
        fechaSubida = new Timestamp(date.getTime());

    }


    //Getters and Setters
    public int getIdCopiadeSeguridad() {  return idCopiadeSeguridad;  }

    public void setIdCopiadeSeguridad(int idCopiadeSeguridad) {
        this.idCopiadeSeguridad = idCopiadeSeguridad;
    }

    public Timestamp getFechaSubida() {  return fechaSubida;  }

    public void setFechaSubida(Timestamp fecha) {  fechaSubida = fecha;  }

    public String getPathCopia() {  return pathCopia;  }

    public void setPathCopia(String pathCopia) {  this.pathCopia = pathCopia;  }

    public Cuenta getEsGuardada() {  return esGuardada;  }

    public void setEsGuardada(Cuenta esGuardada) {  this.esGuardada = esGuardada;  }

    //toString
    @NotNull

    @Override
    public String toString() {

        return "CopiadeSeguridad{" +
                "idCopiadeSeguridad=" + idCopiadeSeguridad +
                ", fechaSubida=" + fechaSubida +
                ", pathCopia=" + pathCopia +
                ", esGuardada=" + esGuardada + "}";

    }

}