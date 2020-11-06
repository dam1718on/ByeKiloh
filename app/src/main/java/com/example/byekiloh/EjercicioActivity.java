package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.byekiloh.utilidades.*;
import static com.example.byekiloh.utilidades.Tablas.EstructuraEjercicio.*;

import java.util.ArrayList;

public class EjercicioActivity extends AppCompatActivity {

    private Button btnGuardar, btnActualizar, btnBorrar, btnVolverE;
    private EditText etFecha, etDistancia, etTiempo;
    private Spinner spinEjercicios;
    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    BaseDatos basedatos;
    Ejercicio ejercicio;
    Mensaje mensaje;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);

        btnGuardar=findViewById(R.id.btnGuardar);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnBorrar=findViewById(R.id.btnBorrar);
        btnVolverE=findViewById(R.id.btnVolverE);

        etFecha=findViewById(R.id.etFecha);
        etDistancia=findViewById(R.id.etDistancia);
        etTiempo=findViewById(R.id.etTiempo);

        spinEjercicios=findViewById(R.id.spinEjercicios);

        //Recibimos el Usuario que logea a través del intent
        final Usuario userL = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario copia del Usuario recibido
        usuario = new Usuario(userL);

        basedatos = new BaseDatos(getApplicationContext());
        //Actualizamos el contenido del Spinner
        actualizaSpin();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Primer if comprueba que no hay EditText vacíos
            if(etFecha.getText().toString().equals("") || etDistancia.getText().toString().equals("")
                || etFecha.getText().toString().equals("")) {

                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                        "todos los campos son obligatorios");

            } else {

                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etFecha, 6, "Fecha");
                numMinL(etDistancia, 3, "Distancia");
                numMinL(etTiempo, 2, "Tiempo");
                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if (countError == 1) {
                    //Se crea objeto Ejercicio con parámetros
                    ejercicio = new Ejercicio(etFecha.getText().toString(),
                        Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()), usuario.getIdUsuario());
                    //Se ganan tambien permisos de escritura
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    //EstructuraUsuario de insercción de datos
                    ContentValues content = new ContentValues();
                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                    content.put(Tablas.EstructuraEjercicio._IDUSUARIO, ejercicio.getIdUsuario());
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_FECHA,
                            String.valueOf(ejercicio.getFecha()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_DISTANCIA,
                            String.valueOf(ejercicio.getDistancia()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_TIEMPO,
                            String.valueOf(ejercicio.getTiempo()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_VELOCIDAD,
                            String.valueOf(ejercicio.getVelocidad()));
                    sqlite.insert(Tablas.EstructuraEjercicio.TABLE_NAME, null, content);
                    //Mensaje de éxito al añadir
                    mensaje = new Mensaje(getApplicationContext(), "El Ejercicio ha sido almacenado");
                    //Actualizamos el contenido del Spinner, incluyendo el ejercicio añadido
                    actualizaSpin();
                    //Vaciamos los EditText
                    etFecha.setText("");
                    etDistancia.setText("");
                    etTiempo.setText("");
                    //Se cierra la conexión abierta a la Base de Datos
                    sqlite.close();

                }

            }
            }

        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            etFecha.setText("");
            etDistancia.setText("");
            etTiempo.setText("");

            }

        });

        btnVolverE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos intent para ir a .MainActivity y le enviamos Usuario
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("usuario", userL);
            startActivity(intent);

            }

        });

    }

    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo){
        //Contamos el tamaño del EditText introducido
        int num = et.length();
        //Comprobamos si el tamaño del EditText es inferior al minimo exigido
        if(num<minDig) {
            //Retorno de mensaje de error detallado
            mensaje = new Mensaje(getApplicationContext(), "'" + campo + "' tiene " + num +
                " carácteres\ny el mínimo para ese campo son " + minDig);

            countError += 1;

        }

    }

    //Método que crea un ArrayList con los ejercicios guardados en la Base de Datos
    public ArrayList<Ejercicio> SelectAllEjer() {
        //Creamos un ArrayList de Ejercicio
        ArrayList<Ejercicio> ejerciciosAL = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los ejercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Ejercicios WHERE Ejercicios.idUsuario " +
                "LIKE '" + usuario.getIdUsuario() + "'", null);
        //Comprobamos si el cursor no es null
        if (cursor != null) {

            if (cursor.moveToFirst()) {
                //Bucle do-while, crea un ejercicio y lo incluye en el Array mientras haya registros
                do {

                    Ejercicio ejercicio = new Ejercicio();
                    ejercicio.setIdEjercicio(cursor.getInt(cursor.getColumnIndex(_IDEJERCICIO)));
                    ejercicio.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FECHA)));
                    ejercicio.setDistancia(Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        COLUMN_NAME_DISTANCIA))));
                    ejercicio.setTiempo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        COLUMN_NAME_TIEMPO))));
                    ejercicio.setVelocidad(ejercicio.getDistancia(), ejercicio.getTiempo());
                    ejercicio.setIdUsuario(usuario.getIdUsuario());

                    ejerciciosAL.add(ejercicio);

                } while (cursor.moveToNext());

            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Devuelve el ArrayList
        return ejerciciosAL;

    }

    //Método para actualizar el contenido del Spinner
    public void actualizaSpin() {
        //Usamos el método SelecAllEjer que devuelve un Array con la Tabla Ejercicios
        spinEjercicios.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, SelectAllEjer()));

    }

}