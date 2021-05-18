package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

public class CopiadeSeguridad {

    private int idCopiadeSeguridad;
    private Timestamp fechaSubida;
    private File archivoSQLite;
    private Cuenta esGuardada;


    //Constructores, en orden: vacío, con parámetros y copia
    public CopiadeSeguridad() {

        esGuardada = new Cuenta();

    }

    public CopiadeSeguridad(Timestamp fechaSubida, File archivoSQLite, Cuenta esGuardada) {

        this.fechaSubida = fechaSubida;
        this.archivoSQLite = archivoSQLite;
        this.esGuardada = esGuardada;

    }

    public CopiadeSeguridad(CopiadeSeguridad copiadeSeguridad) {

        this.idCopiadeSeguridad = copiadeSeguridad.getIdCopiadeSeguridad();
        this.fechaSubida = copiadeSeguridad.getFechaSubida();
        this.archivoSQLite = copiadeSeguridad.getArchivoSQLite();
        this.esGuardada = copiadeSeguridad.getEsGuardada();

    }


    //Getters and Setters
    public int getIdCopiadeSeguridad() {  return idCopiadeSeguridad;  }

    public void setIdCopiadeSeguridad(int idCopiadeSeguridad) {
        this.idCopiadeSeguridad = idCopiadeSeguridad;
    }

    public Timestamp getFechaSubida() {  return fechaSubida;  }

    public void setFechaSubida() {

        Date date = new Date();
        fechaSubida = new Timestamp(date.getTime());

    }

    public File getArchivoSQLite() {  return archivoSQLite;  }

    public void setArchivoSQLite(File archivoSQLite) {  this.archivoSQLite = archivoSQLite;  }

    public Cuenta getEsGuardada() {  return esGuardada;  }

    public void setEsGuardada(Cuenta esGuardada) {  this.esGuardada = esGuardada;  }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "CopiadeSeguridad{" +
                "idCopiadeSeguridad=" + idCopiadeSeguridad +
                ", fechaSubida=" + fechaSubida +
                ", archivoSQLite=" + archivoSQLite +
                ", esGuardada=" + esGuardada +
                '}';

    }

}