package com.example.byekiloh.utilidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;

    private String sexo, nombre, direccion, localidad, email, fechaNac, user, pass;

    public Usuario() {    }

    public Usuario(String user, String pass) {

        this.user = user;
        this.pass = pass;

    }

    public Usuario(String nombre, String direccion, String localidad, String email, String fechaNac,
        String user, String pass) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.email = email;
        this.fechaNac = fechaNac;
        this.user = user;
        this.pass = pass;

    }

    public Usuario(Usuario user){

        this.id=user.getId();
        this.sexo=user.getSexo();
        this.nombre=user.getNombre();
        this.direccion=user.getDireccion();
        this.localidad=user.getLocalidad();
        this.email=user.getEmail();
        this.fechaNac=user.getFechaNac();
        this.user=user.getUser();
        this.pass=user.getPass();

    }

    public int getId() {  return id;  }

    public void setId(int id) {  this.id = id;  }

    public String getSexo() {  return sexo;  }

    public void setSexo(String sexo) {  this.sexo = sexo;  }

    public String getNombre() {  return nombre;  }

    public void setNombre(String nombre) {  this.nombre = nombre;  }

    public String getDireccion() {  return direccion;  }

    public void setDireccion(String direccion) {  this.direccion = direccion;  }

    public String getLocalidad() {  return localidad;  }

    public void setLocalidad(String localidad) {  this.localidad = localidad;  }

    public String getEmail() {  return email;  }

    public void setEmail(String email) {  this.email = email;  }

    public String getFechaNac() {  return fechaNac;  }

    public void setFechaNac(String fechaNac) {  this.fechaNac = fechaNac;  }

    public String getUser() {  return user;  }

    public void setUser(String user) {  this.user = user;  }

    public String getPass() {  return pass;  }

    public void setPass(String pass) {  this.pass = pass;  }

    @Override
    public String toString() {

        return "Usuario{" +
                "id=" + id +
                ", sexo='" + sexo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", email='" + email + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';

    }

}