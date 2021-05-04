package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

public class Cuenta {

    private int idCuenta, numeroTelefono, numeroEstrellas;
    private String email, nombreUsuario, direccionUsuario;
    private boolean validado;
    private Usuario esDE;

    //Constructores, en orden: vacío, con todos los parámetros y copia
    public Cuenta() {

        setValidado("False");

    }

    public Cuenta(int idCuenta, int numeroTelefono, String email, boolean validado,
        String nombreUsuario, String direccionUsuario, int numeroEstrellas, Usuario esDE) {

        this.idCuenta = idCuenta;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.validado = validado;
        this.nombreUsuario = nombreUsuario;
        this.direccionUsuario = direccionUsuario;
        this.numeroEstrellas = numeroEstrellas;
        this.esDE = esDE;

    }

    public Cuenta(Cuenta cuenta) {

        this.idCuenta = cuenta.getIdCuenta();
        this.numeroTelefono = cuenta.getNumeroTelefono();
        this.email = cuenta.getEmail();
        this.validado = cuenta.isValidado();
        this.nombreUsuario = cuenta.getNombreUsuario();
        this.direccionUsuario = cuenta.getDireccionUsuario();
        this.numeroEstrellas = cuenta.getNumeroEstrellas();
        this.esDE = cuenta.getEsDE();

    }

    //Método que valida la cuenta
    public void validarCuenta() {    }

    //Getters and Setters
    public int getIdCuenta() {  return idCuenta;  }

    public void setIdCuenta(int idCuenta) {  this.idCuenta = idCuenta;  }

    public int getNumeroTelefono() {  return numeroTelefono;  }

    public void setNumeroTelefono(int numeroTelefono) {  this.numeroTelefono = numeroTelefono;  }

    public String getEmail() {  return email;  }

    public void setEmail(String email) {  this.email = email;  }

    public boolean isValidado() {  return validado;  }

    public void setValidado(String validado) {  this.validado = Boolean.parseBoolean(validado);  }

    public String getNombreUsuario() {  return nombreUsuario;  }

    public void setNombreUsuario(String nombreUsuario) {  this.nombreUsuario = nombreUsuario;  }

    public String getDireccionUsuario() {  return direccionUsuario;  }

    public void setDireccionUsuario(String direccionUsuario) {  this.direccionUsuario = direccionUsuario;  }

    public int getNumeroEstrellas() {  return numeroEstrellas;  }

    public void setNumeroEstrellas(int numeroEstrellas) {  this.numeroEstrellas = numeroEstrellas;  }

    public Usuario getEsDE() {  return esDE;  }

    public void setEsDE(Usuario esDE) {  this.esDE = esDE;  }

    @NotNull
    @Override
    public String toString() {

        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", numeroTelefono=" + numeroTelefono +
                ", numeroEstrellas=" + numeroEstrellas +
                ", email='" + email + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", direccionUsuario='" + direccionUsuario + '\'' +
                ", validado=" + validado +
                ", usuario=" + esDE +
                '}';

    }

}