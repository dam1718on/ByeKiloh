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

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuarioLogin, etContraseñaLogin;
    private Button btnCrearCuentaLogin, btnIniciarSesionLogin;
    private CheckBox cbMantenerSesionIniciadaLogin;

    private String userSP;
    private String defaultValue;

    Mensaje mensaje;
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuarioLogin =findViewById(R.id.etUsuarioRegistro);
        etContraseñaLogin =findViewById(R.id.etContraseñaRegistro);

        cbMantenerSesionIniciadaLogin =findViewById(R.id.cbAceptarTOSregistro);

        btnCrearCuentaLogin =findViewById(R.id.btnCrearCuentaLogin);
        btnIniciarSesionLogin =findViewById(R.id.btnRegistroRegistro);
        //SharedPreferences para Recordar nombre de usuario
        SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userSP = prefSesion.getString("usuario", defaultValue);
        etUsuarioLogin.setText(userSP);

        basedatos = new BaseDatos(getApplicationContext());

        btnCrearCuentaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos Intent para ir a .CrearCuenta1de3
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivity(intent);
            }
        });

        btnIniciarSesionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Se crea e inicializa el objeto userL
            Usuario userL = new Usuario();
            //Se recogen los datos de los EditText
            userL.setUser(etUsuarioLogin.getText().toString());
            userL.setPass(etContraseñaLogin.getText().toString());
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
                        if(cbMantenerSesionIniciadaLogin.isChecked()) {//dentro de este if va el intent a implementar
                            //Le pasamos el nombre de usuario al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            userSP = userL.getUser();
                            SharedPreferences.Editor editor=prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            etContraseñaLogin.setText("");
                        }
                        else {
                            //Le pasamos usuario="" al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            userSP = "";
                            SharedPreferences.Editor editor=prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            etUsuarioLogin.setText("");
                            etContraseñaLogin.setText("");
                        }
                        //Abrimos el intent para ir al .MainActivity
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("usuario", userL);
                        startActivity(intent);
                    }
                    else {
                        mensaje = new Mensaje(getApplicationContext(), "La contraseña no es correcta");
                        etContraseñaLogin.setText("");
                    }
                }
                else {
                    mensaje = new Mensaje(getApplicationContext(), "El usuario: "+userL.getUser()+" no existe");
                    etUsuarioLogin.setText("");
                    etContraseñaLogin.setText("");
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