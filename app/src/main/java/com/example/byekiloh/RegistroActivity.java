package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.byekiloh.objetos.*;
import com.example.byekiloh.utilidades.*;

public class RegistroActivity extends AppCompatActivity {

    private EditText etUsuarioRegistro, etContraseñaRegistro, etConfirmarContraseña;

    private int countError=1;

    private Button btnVolverAtrasRegistro, btnRegistroRegistro;

    Mensaje mensaje;
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuarioRegistro =findViewById(R.id.etUsuarioRegistro);
        etContraseñaRegistro =findViewById(R.id.etContraseñaRegistro);
        etConfirmarContraseña =findViewById(R.id.etContraseñaRe);

        btnVolverAtrasRegistro =findViewById(R.id.btnVolverAtrasRegistro);
        btnRegistroRegistro =findViewById(R.id.btnRegistroRegistro);

        basedatos = new BaseDatos(getApplicationContext());

        btnRegistroRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Primer if comprueba que no hay EditText vacíos
                Usuario useR = null;
                if (etUsuarioRegistro.getText().toString().equals("") || etContraseñaRegistro.getText().toString().equals("") ||
                        etConfirmarContraseña.getText().toString().equals("")) {
                    mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                            "todos los campos son obligatorios");
                } else {
                    countError=1;
                    //Comprobamos número mínimo de carácteres en cada EditText
                    numMinL(etUsuarioRegistro, 4, "Usuario");
                    numMinL(etContraseñaRegistro, 6, "Contraseña");
                    numMinL(etConfirmarContraseña, 6, "Confirmar contraseña");
                    //Segundo if comprueba si se cumple con el mínimo de carácteres
                    if (countError == 1) {
                        //Se crea e inicializa el objeto useR
                        useR = new Usuario();
                        //Se recogen los datos de los EditText
                        useR.setUser(etUsuarioRegistro.getText().toString());
                        useR.setPass(etContraseñaRegistro.getText().toString());
                        String passRe = etConfirmarContraseña.getText().toString();
                        //Tercer if comprueba si no coinciden las contraseñas
                        if (!useR.getPass().equals(passRe)) {
                            mensaje = new Mensaje(getApplicationContext(), "Las Contraseñas introducidas\n" +
                                    "no coinciden");
                        } else {
                            //Se establece conexion con permisos de lectura
                            SQLiteDatabase sqlite = basedatos.getReadableDatabase();
                            //Columnas que recogerá los datos de la consulta
                            String[] columnas = {
                                    Tablas.EstructuraUsuario._ID,
                                    Tablas.EstructuraUsuario.COLUMN_NAME_NAME,
                                    Tablas.EstructuraUsuario.COLUMN_NAME_PASS,
                            };
                            //Cláusula WHERE para buscar por usuario
                            String usuario = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " LIKE '" + useR.getUser() + "'";
                            //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                            String ordenSalida = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " DESC";
                            //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                            // columnas, usuario y orden de los resultados.
                            Cursor cursor = sqlite.query(Tablas.EstructuraUsuario.TABLE_NAME, columnas, usuario,
                                    null, null, null, ordenSalida);
                            //Cuarto if comprueba que el cursor no esté vacío
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                mensaje = new Mensaje(getApplicationContext(), "El nombre de usuario: " +
                                        useR.getUser() + "\nno está disponible, pruebe con otro");
                                etUsuarioRegistro.setText("");
                            } else {
                                //Aqui se introduce el usuario nuevo en la base de datos
                                //Se ganan tambien permisos de escritura
                                sqlite = basedatos.getWritableDatabase();
                                //EstructuraUsuario de insercción de datos
                                ContentValues content = new ContentValues();
                                //Se añaden los valores introducidos de cada campo mediante
                                // clave(columna)/valor(valor introducido en el campo de texto)
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_NAME, useR.getUser());
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_PASS, useR.getPass());
                                sqlite.insert(Tablas.EstructuraUsuario.TABLE_NAME, null, content);
                                //Volvemos a rellenar el cursor el cual incluye los datos ya insertados
                                cursor = sqlite.query(Tablas.EstructuraUsuario.TABLE_NAME, columnas, usuario,
                                        null, null, null, ordenSalida);
                                cursor.moveToFirst();
                                //Extraemos el atributo id y se lo pasamos al objeto Usuario
                                int identificador = cursor.getInt(cursor.getColumnIndex(Tablas.EstructuraUsuario._ID));
                                useR.setId(identificador);
                                //Registro exitoso
                                mensaje = new Mensaje(getApplicationContext(), "El usuario " + useR.getUser() +
                                        " con id " + useR.getId() + "\nha sido registrado con éxito");
                                //Reseteo de los EditText
                                etUsuarioRegistro.setText("");
                                etContraseñaRegistro.setText("");
                                etConfirmarContraseña.setText("");


                                /*//Abrimos un intent para ir al Paso 2
                                Intent intent = new Intent(getApplicationContext(), CrearCuenta2de3.class);
                                intent.putExtra("usuario", useR);
                                startActivity(intent);*/
                            }
                            //Se cierra la conexión abierta a la Base de Datos
                            sqlite.close();
                        }
                    }
                }
            }
        });

        btnVolverAtrasRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos Intent para volver a .LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo){
        int num;
        num=et.length();
        if(num<minDig){
            mensaje = new Mensaje(getApplicationContext(), "'"+campo+"' tiene "+
                    String.valueOf(num)+" carácteres\ny el mínimo para ese campo son "+minDig);
            countError=countError+1;
        }
    }

}