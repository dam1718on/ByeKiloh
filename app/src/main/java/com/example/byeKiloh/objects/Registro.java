package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Registro {

    private int idRegistro;
    private Timestamp fechaRegistro;
    //consumoEnergetico pasarlo a método
    private float consumoEnergetico;
    private Usuario hechoPOR;
    private ArrayList<Ejercicio> esHecho;
    private Bascula esDE;

    //Constructores, en orden: vacío, con todos los parámetros y copia
    public Registro() {

        hechoPOR = new Usuario();
        esHecho = new ArrayList<Ejercicio>();
        esDE = new Bascula();

    }

    public Registro(int idRegistro, Timestamp fechaRegistro, float consumoEnergetico,
                    Usuario hechoPOR, Bascula esDE, ArrayList<Ejercicio> esHecho) {

        this.idRegistro = idRegistro;
        this.fechaRegistro = fechaRegistro;
        this.consumoEnergetico = consumoEnergetico;
        this.hechoPOR = hechoPOR;
        this.esDE = esDE;
        this.esHecho = esHecho;

    }

    public Registro(Registro registro) {

        this.idRegistro = registro.getIdRegistro();
        this.fechaRegistro = registro.getFechaRegistro();
        this.consumoEnergetico = registro.getConsumoEnergetico();
        this.hechoPOR = registro.getHechoPOR();
        this.esDE = registro.getEsDE();
        this.esHecho = registro.getEsHecho();

    }

    //Getters and Setters
    public int getIdRegistro() {  return idRegistro;  }

    public void setIdRegistro(int idRegistro) {  this.idRegistro = idRegistro;  }

    public Timestamp getFechaRegistro() {  return fechaRegistro;  }

    public void setFechaRegistro() {

        Date date = new Date();
        fechaRegistro = new Timestamp(date.getTime());

    }

    public float getConsumoEnergetico() {  return consumoEnergetico;  }

    public void setConsumoEnergetico(float consumoEnergetico) {  this.consumoEnergetico = consumoEnergetico;  }

    public Usuario getHechoPOR() {  return hechoPOR;  }

    public void setHechoPOR(Usuario hechoPOR) {  this.hechoPOR = hechoPOR;  }

    public Bascula getEsDE() {  return esDE;  }

    public void setEsDE(Bascula esDE) {  this.esDE = esDE;  }

    public ArrayList<Ejercicio> getEsHecho() {  return esHecho;  }

    public void setEsHecho(ArrayList<Ejercicio> esHecho) {  this.esHecho = esHecho;  }

    //Método para añadir ejercicio al ArrayList
    public void anadirEjercicio(Ejercicio ejercicio) {  esHecho.add(ejercicio);  }

    @NotNull
    @Override
    public String toString() {

        return "Registro{" +
                "idRegistro=" + idRegistro +
                ", fechaRegistro=" + fechaRegistro +
                ", consumoEnergetico=" + consumoEnergetico +
                ", hechoPOR=" + hechoPOR +
                ", esHecho=" + esHecho +
                ", esDE=" + esDE +
                '}';
    }

}