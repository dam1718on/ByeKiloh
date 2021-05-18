package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.Date;

public class LogrosdeCuenta {

    private int idLogrosdeCuenta;
    private Timestamp fechaLogro;
    private Logro cuenta;
    private Cuenta esConseguido;


    //Constructores, en orden: vacío, con parámetros y copia
    public LogrosdeCuenta() {

        cuenta = new Logro();
        esConseguido = new Cuenta();

    }

    public LogrosdeCuenta(Timestamp fechaLogro, Logro cuenta, Cuenta esConseguido) {

        this.fechaLogro = fechaLogro;
        this.cuenta = cuenta;
        this.esConseguido = esConseguido;

    }

    public LogrosdeCuenta(LogrosdeCuenta logrosdeCuenta) {

        this.idLogrosdeCuenta = logrosdeCuenta.getIdLogrosdeCuenta();
        this.fechaLogro = logrosdeCuenta.getFechaLogro();
        this.cuenta = logrosdeCuenta.getCuenta();
        this.esConseguido = logrosdeCuenta.getEsConseguido();

    }


    //Getters and Setters
    public int getIdLogrosdeCuenta() {  return idLogrosdeCuenta;  }

    public void setIdLogrosdeCuenta(int idLogrosdeCuenta) {
        this.idLogrosdeCuenta = idLogrosdeCuenta;
    }

    public Timestamp getFechaLogro() {  return fechaLogro;  }

    public void setFechaLogro() {

        Date date = new Date();
        fechaLogro = new Timestamp(date.getTime());

    }

    public Logro getCuenta() {  return cuenta;  }

    public void setCuenta(Logro cuenta) {  this.cuenta = cuenta;  }

    public Cuenta getEsConseguido() {  return esConseguido;  }

    public void setEsConseguido(Cuenta esConseguido) {  this.esConseguido = esConseguido;  }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "LogrosdeCuenta{" +
                "idLogrosdeCuenta=" + idLogrosdeCuenta +
                ", fechaLogro=" + fechaLogro +
                ", cuenta=" + cuenta +
                ", esConseguido=" + esConseguido +
                '}';

    }

}