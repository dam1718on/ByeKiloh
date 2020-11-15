package com.example.byeKiloh.utilidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int idUsuario;
    private String usuario, contraseña;
    private String email, nombre, direccion, localidad, fechaNac, genero;

    public Usuario() {    }

    public Usuario(String usuario, String contraseña) {

        this.usuario = usuario;
        this.contraseña = contraseña;

    }

    public Usuario(String usuario, String contraseña, String email, String nombre, String direccion,
                   String localidad, String fechaNac, String genero) {

        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.fechaNac = fechaNac;
        this.genero = genero;

    }

    public Usuario(Usuario usuario) {

        this.idUsuario = usuario.getIdUsuario();
        this.usuario = usuario.getUsuario();
        this.contraseña = usuario.getContraseña();
        this.email= usuario.getEmail();
        this.nombre= usuario.getNombre();
        this.direccion= usuario.getDireccion();
        this.localidad= usuario.getLocalidad();
        this.fechaNac= usuario.getFechaNac();
        this.genero = usuario.getGenero();

    }

    public int getIdUsuario() {  return idUsuario;  }

    public void setIdUsuario(int idUsuario) {  this.idUsuario = idUsuario;  }

    public String getUsuario() {  return usuario;  }

    public void setUsuario(String usuario) {  this.usuario = usuario;  }

    public String getContraseña() {  return contraseña;  }

    public void setContraseña(String contraseña) {  this.contraseña = contraseña;  }

    public String getEmail() {  return email;  }

    public void setEmail(String email) {  this.email = email;  }

    public String getNombre() {  return nombre;  }

    public void setNombre(String nombre) {  this.nombre = nombre;  }

    public String getDireccion() {  return direccion;  }

    public void setDireccion(String direccion) {  this.direccion = direccion;  }

    public String getLocalidad() {  return localidad;  }

    public void setLocalidad(String localidad) {  this.localidad = localidad;  }

    public String getFechaNac() {  return fechaNac;  }

    public void setFechaNac(String fechaNac) {  this.fechaNac = fechaNac;  }

    public String getGenero() {  return genero;  }

    public void setGenero(String genero) {  this.genero = genero;  }

    @Override
    public String toString() {

        return "Usuario{" +
                "id=" + idUsuario +
                ", user='" + usuario + '\'' +
                ", pass='" + contraseña + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", sexo='" + genero + '\'' +
                '}';

    }

}