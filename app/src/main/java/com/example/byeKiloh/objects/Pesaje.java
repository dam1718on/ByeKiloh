package com.example.byeKiloh.objects;

import java.text.DecimalFormat;

public class Pesaje {

    private int idPesaje, idUsuario;
    private String fecha, peso, altura, lugar, imc, clasificacion;

    public Pesaje() {  }

    public Pesaje(String fecha, String peso, String altura, String lugar, int idUsuario) {

        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.lugar = lugar;
        setIMC();
        setClasificacion();
        this.idUsuario = idUsuario;
    }

    public Pesaje(Pesaje pesaje) {

        this.idPesaje = pesaje.getIdPesaje();
        this.fecha = pesaje.getFecha();
        this.peso = pesaje.getPeso();
        this.altura = pesaje.getAltura();
        this.lugar = pesaje.getLugar();
        this.imc = pesaje.getIMC();
        this.clasificacion = pesaje.getClasificacion();
        this.idUsuario = pesaje.getIdUsuario();
    }

    public int getIdPesaje() { return idPesaje; }

    public void setIdPesaje(int idPesaje) { this.idPesaje = idPesaje; }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getPeso() { return peso; }

    public void setPeso(String peso) { this.peso = peso; }

    public String getAltura() { return altura; }

    public void setAltura(String altura) { this.altura = altura; }

    public String getLugar() { return lugar; }

    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getIMC() { return imc; }

    public void setIMC() {
        //reemplazamos las comas para poder hacer cálculos
        String x = peso.replace(",",".");
        String y = altura.replace(",",".");
        //convertimos los strings en float
        float pesoIMC = new Float(x);
        float alturaIMC = new Float(y);
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        imc = df.format( pesoIMC / (alturaIMC * alturaIMC));
    }

    public String getClasificacion() { return clasificacion; }

    public void setClasificacion() {

        clasificacion="GORDO";
    }

    @Override
    public String toString() {

        return "- Pesaje " + idPesaje +
                " del " + fecha +
                " con " + peso +
                " kg y " + altura +
                " cm imc " + imc +
                " clasificación " + clasificacion;
    }

}