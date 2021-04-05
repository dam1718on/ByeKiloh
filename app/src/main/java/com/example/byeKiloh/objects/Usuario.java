package com.example.byeKiloh.objects;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int idUsuario;
    private String usuario, contraseña;
    private String email, nombre, direccion, localidad, fechaNac;

    public Usuario() {    }

    public Usuario(String usuario, String contraseña) {

        this.usuario = usuario;
        this.contraseña = contraseña;

    }

    public Usuario(String usuario, String contraseña, String email, String nombre, String direccion,
                   String localidad, String fechaNac) {

        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.fechaNac = fechaNac;

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
                '}';

    }

}