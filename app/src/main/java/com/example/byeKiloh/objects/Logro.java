package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

public class Logro {

    private int idLogro;
    private String nombreLogro, descripcionLogro;

    //Constructores, en orden: vacío, con parámetros y copia
    public Logro() {    }

    public Logro(int idLogro, String nombreLogro, String descripcionLogro) {

        this.idLogro = idLogro;
        this.nombreLogro = nombreLogro;
        this.descripcionLogro = descripcionLogro;

    }

    public Logro(Logro logro) {

        this.idLogro = logro.getIdLogro();
        this.nombreLogro = logro.getNombreLogro();
        this.descripcionLogro = logro.getDescripcionLogro();

    }

    //Métodos propios

    //Verifica si se ha conseguido el Logro
    public void conseguirLogro() {    }


    //Getters and Setters
    public int getIdLogro() {  return idLogro;  }

    public void setIdLogro(int idLogro) {  this.idLogro = idLogro;  }

    public String getNombreLogro() {  return nombreLogro;  }

    public void setNombreLogro(String nombreLogro) {  this.nombreLogro = nombreLogro;  }

    public String getDescripcionLogro() {  return descripcionLogro;  }

    public void setDescripcionLogro(String descripcionLogro) {
        this.descripcionLogro = descripcionLogro;
    }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "Logro{" +
                "idLogro=" + idLogro +
                ", nombreLogro='" + nombreLogro + '\'' +
                ", descripcionLogro='" + descripcionLogro + '\'' +
                '}';

    }

}