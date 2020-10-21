package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.byekiloh.utilidades.*;

public class LoginActivity extends AppCompatActivity {

    private Button btnCrearCuenta, btnIniciarSesion;

    private CheckBox cbMantenerSesion;

    private EditText etUsuario, etPass;

    private TextView tvPass;

    private boolean select = false;

    private String defaultValue;
    private String userSP;

    BaseDatos basedatos;
    Mensaje mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCrearCuenta =findViewById(R.id.btnCrearCuenta);
        btnIniciarSesion =findViewById(R.id.btnRegistro);

        cbMantenerSesion =findViewById(R.id.cbMantenerSesion);

        etUsuario =findViewById(R.id.etUsuario);
        etPass =findViewById(R.id.etPass);

        tvPass=findViewById(R.id.tvPass);

        //SharedPreferences para Recordar nombre de usuario
        SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userSP = prefSesion.getString("usuario", defaultValue);
        etUsuario.setText(userSP);

        basedatos = new BaseDatos(getApplicationContext());

        //TextView que permite la visualización de la contraseña
        tvPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (select == false){

                select = true;
                etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                if (etPass.getText().length() > 0) {

                    etPass.setSelection(etPass.getText().length());
                    tvPass.setBackgroundResource(R.drawable.ic_action_password_visible);

                }

            }else {

                select = false;
                etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                if (etPass.getText().length() > 0) {

                    etPass.setSelection(etPass.getText().length());
                    tvPass.setBackgroundResource(R.drawable.ic_action_password_visible_off);

                }
            }

        }

        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            //Creamos Intent para ir a .RegistroActivity
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivity(intent);

            }

        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            //Se crea e inicializa el objeto userL
            Usuario userL = new Usuario();
            //Se recogen los datos de los EditText
            userL.setUser(etUsuario.getText().toString());
            userL.setPass(etPass.getText().toString());

            //Primer if comprueba si están vacíos los dos primeros EditText
            if(userL.getUser().equals("") || userL.getPass().equals("")) {

                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                    "todos los campos son obligatorios");

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
                        mensaje = new Mensaje(getApplicationContext(), "Bienvenid@:  "+userL.getUser());

                        //Cuarto if comprueba si Recordar nombre de usuario está checked
                        if(cbMantenerSesion.isChecked()) {//dentro de este if va el intent a implementar

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
                            etUsuario.setText("");
                            etPass.setText("");

                        }

                        //Creamos intent para ir a .MainActivity y le enviamos Usuario
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("usuario", userL);
                        startActivity(intent);

                    }

                    else {

                        mensaje = new Mensaje(getApplicationContext(), "La contraseña no es correcta");
                        etPass.setText("");

                    }

                }

                else {

                    mensaje = new Mensaje(getApplicationContext(), "El usuario: "+userL.getUser()+" no existe");
                    etUsuario.setText("");
                    etPass.setText("");

                }

                //Se cierra la conexión abierta a la Base de Datos
                sqliteL.close();

            }
            }

        });

    }

}