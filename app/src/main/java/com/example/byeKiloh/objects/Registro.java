package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.Date;

public class Registro {

    private int idRegistro;
    private Timestamp fechaRegistro;
    private Usuario esInsertado;
    private Ejercicio hace;
    private Bascula mide;


    //Constructores, en orden: vacío, con todos los parámetros y copia
    public Registro() {

        esInsertado = new Usuario();
        hace = new Ejercicio();
        mide = new Bascula();

    }

    public Registro(Timestamp fechaRegistro, Usuario esInsertado, Ejercicio hace, Bascula mide) {

        this.fechaRegistro = fechaRegistro;
        this.esInsertado = esInsertado;
        this.hace = hace;
        this.mide = mide;

    }

    public Registro(Registro registro) {

        this.idRegistro = registro.getIdRegistro();
        this.fechaRegistro = registro.getFechaRegistro();
        this.esInsertado = registro.getEsInsertado();
        this.hace = registro.getHace();
        this.mide = registro.getMide();

    }


    //Getters and Setters
    public int getIdRegistro() {  return idRegistro;  }

    public void setIdRegistro(int idRegistro) {  this.idRegistro = idRegistro;  }

    public Timestamp getFechaRegistro() {  return fechaRegistro;  }

    public void setFechaRegistro() {

        Date date = new Date();
        fechaRegistro = new Timestamp(date.getTime());

    }

    public Usuario getEsInsertado() {  return esInsertado;  }

    public void setEsInsertado(Usuario esInsertado) {  this.esInsertado = esInsertado;  }

    public Ejercicio getHace() {  return hace;  }

    public void setHace(Ejercicio hace) {  this.hace = hace;  }

    public Bascula getMide() {  return mide;  }

    public void setMide(Bascula mide) {  this.mide = mide;  }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "Registro{" +
                "idRegistro=" + idRegistro +
                ", fechaRegistro=" + fechaRegistro +
                ", inserta=" + esInsertado +
                ", esHecho=" + hace +
                ", esMedida=" + mide + "}";

    }

}