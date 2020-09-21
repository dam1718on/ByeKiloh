package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.byekiloh.crearcuenta.CrearCuenta1de3;
import com.example.byekiloh.utilidades.*;
import com.example.byekiloh.objetos.*;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgUser;
    private EditText etUser, etPass;
    private Button btnRegistro, btnLogin;
    private CheckBox cbUsuario;

    private String userSP;
    private String defaultValue;

    Mensaje mensaje;
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgUser=findViewById(R.id.imgUser);

        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);

        cbUsuario =findViewById(R.id.cbUsuario);

        btnRegistro=findViewById(R.id.btnRegistro);
        btnLogin=findViewById(R.id.btnLogin);

        //imgUser.setImageResource(R.drawable.userh);
        //SharedPreferences para Recordar nombre de usuario
        SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userSP = prefSesion.getString("usuario", defaultValue);
        etUser.setText(userSP);

        basedatos = new BaseDatos(getApplicationContext());

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos Intent para ir a .CrearCuenta1de3
            Intent intent = new Intent(getApplicationContext(), CrearCuenta1de3.class);
            startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Se crea e inicializa el objeto userL
            Usuario userL = new Usuario();
            //Se recogen los datos de los EditText
            userL.setUser(etUser.getText().toString());
            userL.setPass(etPass.getText().toString());
            //Primer if comprueba si están vacíos los dos primeros EditText
            if(userL.getUser().equals("") || userL.getPass().equals("")) {
                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\ntodos los campos son obligatorios");
            }
            else {
                //Se establece conexion con permisos de lectura
                SQLiteDatabase sqliteL = basedatos.getReadableDatabase();
                //Columnas que recogerá los datos de la consulta
                String[] columnasL = {
                        Tablas.EstructuraUsuario._ID,
                        Tablas.EstructuraUsuario.COLUMN_NAME_NAME,
                        Tablas.EstructuraUsuario.COLUMN_NAME_PASS,
                };
                //Cláusula WHERE para buscar por usuario
                String usuarioL = Tablas.EstructuraUsuario.COLUMN_NAME_NAME+" LIKE '"+userL.getUser()+"'";
                //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                String ordenSalidaNameL = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " DESC";
                //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                // columnas, usuario y orden de los resultados.
                Cursor cursorL = sqliteL.query(Tablas.EstructuraUsuario.TABLE_NAME, columnasL, usuarioL,
                        null , null, null, ordenSalidaNameL);
                //Segundo if comprueba que el cursor no esté vacío
                if(cursorL.getCount() != 0) {
                    cursorL.moveToFirst();
                    String passCNL = cursorL.getString(cursorL.getColumnIndex(Tablas.EstructuraUsuario.COLUMN_NAME_PASS));
                    //Tercer if comprueba que las contraseñas coinciden
                    if(userL.getPass().equals(passCNL)) {
                        //Como es el usuario correcto, incluimos el valor del atributo id en el objeto userL
                        int identificadorL = cursorL.getInt(cursorL.getColumnIndex(Tablas.EstructuraUsuario._ID));
                        userL.setId(identificadorL);
                        mensaje = new Mensaje(getApplicationContext(), "Bienvenido, "+userL.getUser()+"\nLogin correcto");
                        //Cuarto if comprueba si Recordar nombre de usuario está checked
                        if(cbUsuario.isChecked()) {//dentro de este if va el intent a implementar
                            //Le pasamos el nombre de usuario al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            userSP = userL.getUser();
                            SharedPreferences.Editor editor=prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            etPass.setText("");
                        }
                        else {
                            //Le pasamos usuario="" al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            userSP = "";
                            SharedPreferences.Editor editor=prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            etUser.setText("");
                            etPass.setText("");
                        }
                    }
                    else {
                        mensaje = new Mensaje(getApplicationContext(), "La contraseña no es correcta");
                        etPass.setText("");
                    }
                }
                else {
                    mensaje = new Mensaje(getApplicationContext(), "El usuario: "+userL.getUser()+" no existe");
                    etUser.setText("");
                }
                //Se cierra la conexión abierta a la Base de Datos
                sqliteL.close();
            }
            }
        });

    }

    //spinEjercicios.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,
    //Collections.singletonList(ejercicio.toString())));

}