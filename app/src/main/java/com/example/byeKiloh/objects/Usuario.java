package com.example.byeKiloh.objects;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int idUsuario;
    private String aliasUsuario, contrasenia;


    //Constructores, en orden: vacío, con parámetros y copia
    public Usuario() {    }

    public Usuario(String aliasUsuario, String contrasenia) {

        this.aliasUsuario = aliasUsuario;
        this.contrasenia = contrasenia;

    }

    public Usuario(Usuario usuario) {

        this.idUsuario = usuario.getIdUsuario();
        this.aliasUsuario = usuario.getAliasUsuario();
        this.contrasenia = usuario.getContrasenia();

    }


    //Getters and Setters
    public int getIdUsuario() {  return idUsuario;  }

    public void setIdUsuario(int idUsuario) {  this.idUsuario = idUsuario;  }

    public String getAliasUsuario() {  return aliasUsuario;  }

    public void setAliasUsuario(String aliasUsuario) {  this.aliasUsuario = aliasUsuario;  }

    public String getContrasenia() {  return contrasenia;  }

    public void setContrasenia(String contrasenia) {  this.contrasenia = contrasenia;  }


    //toString
    @NotNull
    @Override
    public String toString() {

        return "Usuario{" +
                "id=" + idUsuario +
                ", user='" + aliasUsuario + '\'' +
                ", pass='" + contrasenia + '\'' +
                '}';

    }

}