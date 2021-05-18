package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cuenta {

    private int idCuenta, numeroTelefono;
    private String email, nombreUsuario, direccionUsuario;
    private boolean cuentaValidada;
    private Usuario esDE;
    private ArrayList<CopiadeSeguridad> guarda;
    private ArrayList<LogrosdeCuenta> consigue;


    //Constructores, en orden: vacío, con parámetros y copia
    public Cuenta() {

        //inicializamos cuentaValidad como false al instanciar una Cuenta vacía
        setCuentaValidada("False");
        esDE = new Usuario();

    }

    public Cuenta(int numeroTelefono, String email, boolean cuentaValidada, String nombreUsuario,
                  String direccionUsuario, Usuario esDE, ArrayList<CopiadeSeguridad> guarda,
                  ArrayList<LogrosdeCuenta> consigue) {

        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.cuentaValidada = cuentaValidada;
        this.nombreUsuario = nombreUsuario;
        this.direccionUsuario = direccionUsuario;
        this.esDE = esDE;
        this.guarda = guarda;
        this.consigue = consigue;

    }

    public Cuenta(Cuenta cuenta) {

        this.idCuenta = cuenta.getIdCuenta();
        this.numeroTelefono = cuenta.getNumeroTelefono();
        this.email = cuenta.getEmail();
        this.cuentaValidada = cuenta.isCuentaValidada();
        this.nombreUsuario = cuenta.getNombreUsuario();
        this.direccionUsuario = cuenta.getDireccionUsuario();
        this.esDE = cuenta.getEsDE();
        this.guarda = cuenta.getGuarda();
        this.consigue = cuenta.getConsigue();

    }

    //Métodos propios

    //Valida la cuenta
    public void validarCuenta() {    }

    //Añade copiadeSeguridad al ArrayList guarda
    public void añadiraGuarda(CopiadeSeguridad copiadeSeguridad) {  guarda.add(copiadeSeguridad);  }

    //Añade logrosdeCuenta al ArrayList consigue
    public void añadiraConsigue(LogrosdeCuenta logrosdeCuenta) {  consigue.add(logrosdeCuenta);  }


    //Getters and Setters
    public int getIdCuenta() {  return idCuenta;  }

    public void setIdCuenta(int idCuenta) {  this.idCuenta = idCuenta;  }

    public int getNumeroTelefono() {  return numeroTelefono;  }

    public void setNumeroTelefono(int numeroTelefono) {  this.numeroTelefono = numeroTelefono;  }

    public String getEmail() {  return email;  }

    public void setEmail(String email) {  this.email = email;  }

    public boolean isCuentaValidada() {  return cuentaValidada;  }

    public void setCuentaValidada(String cuentaValidada) {
        this.cuentaValidada = Boolean.parseBoolean(cuentaValidada);
    }

    public String getNombreUsuario() {  return nombreUsuario;  }

    public void setNombreUsuario(String nombreUsuario) {  this.nombreUsuario = nombreUsuario;  }

    public String getDireccionUsuario() {  return direccionUsuario;  }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public Usuario getEsDE() {  return esDE;  }

    public void setEsDE(Usuario esDE) {  this.esDE = esDE;  }

    public ArrayList<CopiadeSeguridad> getGuarda() {
        return guarda;
    }

    public void setGuarda(ArrayList<CopiadeSeguridad> guarda) {
        this.guarda = guarda;
    }

    public ArrayList<LogrosdeCuenta> getConsigue() {  return consigue;  }

    public void setConsigue(ArrayList<LogrosdeCuenta> consigue) {  this.consigue = consigue;  }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", numeroTelefono=" + numeroTelefono +
                ", email='" + email + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", direccionUsuario='" + direccionUsuario + '\'' +
                ", cuentaValidada=" + cuentaValidada +
                ", esDE=" + esDE +
                ", guarda=" + guarda +
                ", consigue=" + consigue +
                '}';

    }

}