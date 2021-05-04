package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class Bascula {

    private int idBascula;
    private float pesoUsuario, alturaUsuario;
    private String lugarBascula;

    //Constructores, en orden: vacío, con todos los parámetros y copia
    public Bascula() {    }

    public Bascula(float pesoUsuario, float alturaUsuario, String lugarBascula) {

        this.pesoUsuario = pesoUsuario;
        this.alturaUsuario = alturaUsuario;
        this.lugarBascula = lugarBascula;

    }

    public Bascula(Bascula bascula) {

        this.idBascula = bascula.getIdBascula();
        this.pesoUsuario = bascula.getPesoUsuario();
        this.alturaUsuario = bascula.getAlturaUsuario();
        this.lugarBascula = bascula.getLugarBascula();

    }

    //Getters and Setters
    public int getIdBascula() {  return idBascula;  }

    public void setIdBascula(int idBascula) {  this.idBascula = idBascula;  }

    public float getPesoUsuario() {  return pesoUsuario;  }

    public void setPesoUsuario(float pesoUsuario) {  this.pesoUsuario = pesoUsuario;  }

    public float getAlturaUsuario() {  return alturaUsuario;  }

    public void setAlturaUsuario(float alturaUsuario) {  this.alturaUsuario = alturaUsuario;  }

    public String getLugarBascula() {  return lugarBascula;  }

    public void setLugarBascula(String lugarBascula) {  this.lugarBascula = lugarBascula;  }

    //Método que calcula el índice de masa corporal(IMC)
    public String imc() {

        String format;
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        format = df.format( pesoUsuario / (alturaUsuario * alturaUsuario));
        return format;

    }

    @NotNull
    @Override
    public String toString() {

        return "Bascula{" +
                "idBascula=" + idBascula +
                ", pesoUsuario=" + pesoUsuario +
                ", alturaUsuario=" + alturaUsuario +
                ", lugarBascula='" + lugarBascula + '\'' +
                '}';

    }

}