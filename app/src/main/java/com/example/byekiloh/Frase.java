package com.example.byekiloh;

import com.google.gson.annotations.SerializedName;

public class Frase {

    //private int id;
    @SerializedName("contenido")
    private String contenido;

    @SerializedName("autor")
    private String autor;

    public Frase() {
    }

    public Frase(String contenido, String autor) {
        //this.id = id;
        this.contenido = contenido;
        this.autor = autor;
    }

    public Frase(Frase frase){
        //setId(frase.getId());
        setContenido(frase.getContenido());
        setAutor(frase.getAutor());
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        String cad;
        cad = getContenido() + ".\n-" + getAutor() + "-";
        return cad;
    }

}