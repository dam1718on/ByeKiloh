package com.example.byeKiloh.objects;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;

import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.utils.Mensaje;

import org.jetbrains.annotations.NotNull;

public class Logro {

    private int idLogro;
    private String nombreLogro, descripcionLogro;

    BaseDatos basedatos;
    Mensaje mensaje;

    //Constructores, en orden: vacío, con parámetros y copia
    public Logro() {    }

    public Logro(String nombreLogro, String descripcionLogro) {

        this.nombreLogro = nombreLogro;
        this.descripcionLogro = descripcionLogro;

    }

    public Logro(Logro logro) {

        this.idLogro = logro.getIdLogro();
        this.nombreLogro = logro.getNombreLogro();
        this.descripcionLogro = logro.getDescripcionLogro();

    }

    //Métodos propios

    //Verifica si se ha conseguido el Logro
    public boolean conseguirLogro(View view, String idUsuario) {

        boolean conseguido = false;

        BaseDatos basedatos = new BaseDatos(view.getContext());
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();

        //Query que devuelve los resultados aritméticos de los Ejercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT MAX(Ejercicios.distanciaRecorrida), " +
                "ROUND(AVG(Ejercicios.distanciaRecorrida),2), MAX(Ejercicios.tiempoEmpleado), " +
                "ROUND(AVG(Ejercicios.tiempoEmpleado),2), MAX(Ejercicios.inclinacionTerreno), " +
                "ROUND(AVG(Ejercicios.inclinacionTerreno),2) FROM Ejercicios, Registros WHERE " +
                "Registros.idEjercicio = Ejercicios.idEjercicio AND Registros.idUsuario LIKE '" +
                idUsuario + "'", null);

            cursor.moveToFirst();

            //Recogemos los datos para hacer el calculo
            int distanciaMax = cursor.getInt(0);
            float distanciaMed = cursor.getFloat(1);
            double reldis = 0;
            int tiempoMax = cursor.getInt(2);
            float tiempoMed = cursor.getFloat(3);
            double reltem = 0;
            float inclinacionMax = cursor.getFloat(4);
            float inclinacionMed = cursor.getFloat(5);
            double relin = 0;
            float calculo = 0;

        if((distanciaMax==0) && (tiempoMax==0) && (inclinacionMax==0)) {

            mensaje = new Mensaje(view.getContext(), "No tiene registros");

        }
        else {

            //En funcion del idLogro añadimos un pocentaje
            if(idLogro==1) {  reldis = 0.5;  }
            if(idLogro==2) {  reldis = 1;  }
            if(idLogro==3) {  reltem = 0.5;  }
            if(idLogro==4) {  reltem = 1;  }
            if(idLogro==5) {  relin = 0.5;  }
            if(idLogro==6) {  relin = 1;  }

            if(reldis!=0) {

                calculo = distanciaMed + (distanciaMed * Float.parseFloat(String.valueOf(reldis)));
                if(Float.parseFloat(String.valueOf(distanciaMax))>=calculo) {  conseguido = true;  }

            }

            if (reltem!=0) {

                calculo = tiempoMed + (tiempoMed * Float.parseFloat(String.valueOf(reltem)));
                if(Float.parseFloat(String.valueOf(tiempoMax))>=calculo) {  conseguido = true;  }

            }

            if(relin!=0) {

                calculo = inclinacionMed + (inclinacionMed * Float.parseFloat(String.valueOf(relin)));
                if(inclinacionMax>=calculo) {  conseguido = true;  }

            }
        }
        //Cierres
        cursor.close();
        sqlite.close();

        return conseguido;

    }


    //Getters and Setters
    public int getIdLogro() {  return idLogro;  }

    public void setIdLogro(int idLogro) {  this.idLogro = idLogro;  }

    public String getNombreLogro() {  return nombreLogro;  }

    public void setNombreLogro(String nombreLogro) {  this.nombreLogro = nombreLogro;  }

    public String getDescripcionLogro() {  return descripcionLogro;  }

    public void setDescripcionLogro(String descripcionLogro) {
        this.descripcionLogro = descripcionLogro;
    }

    //toString
    @NotNull
    @Override
    public String toString() {

        return "Logro{" +
                "idLogro=" + idLogro +
                ", nombreLogro='" + nombreLogro + '\'' +
                ", descripcionLogro='" + descripcionLogro + '\'' +
                '}';

    }

}