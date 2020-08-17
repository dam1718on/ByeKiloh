package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;

import android.graphics.Color;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgUser;
    private EditText etUser, etPass;
    private Button btnRegistro, btnLogin;
    //private String usuario, contraseña;

    LoginBaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgUser=findViewById(R.id.imgUser);
        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        btnRegistro=findViewById(R.id.btnRegistro);
        btnLogin=findViewById(R.id.btnLogin);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String usuarioR,contraseñaR;
                usuarioR=etUser.getText().toString();
                contraseñaR=etPass.getText().toString();

                SharedPreferences prefUser = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefUser.edit();
                editor.putString("usuario", usuarioR);
                editor.putString("contraseña", contraseñaR);
                editor.commit();*/

                //Se inicializa la clase.
                basedatos = new LoginBaseDatos(getApplicationContext());
                //Clase que permite llamar a los métodos para crear, eliminar, leer y actualizar registros. Se establecen permisos de escritura.
                SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                String usuarioR = etUser.getText().toString();
                String contraseñaR = etPass.getText().toString();

                ContentValues content = new ContentValues();

                if(usuarioR.equals("") || contraseñaR.equals("")) {

                    /*TextView textview = new TextView(getApplicationContext());
                    textview.setText("Revise los datos introducidos.\nTodos los campos son obligatorios");
                    textview.setBackgroundColor(Color.GRAY);
                    textview.setTextColor(Color.BLUE);
                    textview.setPadding(10,10,10,10);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setView(textview);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();*/

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Revise los datos introducidos.\nTodos los campos son obligatorios", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();

                    etPass.setText("");

                } else {
                    //Se añaden los valores introducidos de cada campo mediante clave(columna)/valor(valor introducido en el campo de texto)
                    content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_USER, usuarioR);
                    content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS, contraseñaR);
                    sqlite.insert(LoginEstructuraDatos.Estructura.TABLE_NAME, null, content);

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "El Usuario ha sido registrado", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();

                    etUser.setText("");
                    etPass.setText("");

                    //spinEjercicios.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,
                      //      Collections.singletonList(ejercicio.toString())));

                    //edProducto.setText("");
                    //edCantidad.setText("");
                    //edId.setText("");
                }
                //Se cierra la conexión abierta a la Base de Datos
                sqlite.close();

                /*SharedPreferences preferenciasLike = getSharedPreferences("datos", Context.MODE_PRIVATE);
                like = preferenciasLike.getInt("likes", defaultValue);
                verLike=like.toString();
                numLike.setText("LIKE: "+verLike);

                SharedPreferences preferenciasLike = getSharedPreferences("datos", Context.MODE_PRIVATE);
                like = preferenciasLike.getInt("likes", defaultValue);
                like++;
                SharedPreferences.Editor editor=preferenciasLike.edit();
                editor.putInt("likes", like);
                editor.commit();*/

            }
        });

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuarioL=etUser.getText().toString();
                String contraseñaL=etPass.getText().toString();

                SharedPreferences prefUser = getSharedPreferences("datos", Context.MODE_PRIVATE);
                String usuarioSP = prefUser.getString("usuario", usuario);
                String contraseñaSP = prefUser.getString("contraseña", contraseña);

                if(usuarioL.equals(usuarioSP)){
                    if(contraseñaL.equals(contraseñaSP)) {
                        Toast toast= Toast.makeText(getApplicationContext(),"Login correcto",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                        toast.show();
                    } else {
                        Toast toast= Toast.makeText(getApplicationContext(),"Contraseña incorrecta",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                        toast.show();
                    }
                } else {
                    Toast toast= Toast.makeText(getApplicationContext(),"Usuario incorrecto",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();
                }

                etUser.setText("");
                etPass.setText("");

            }
        });*/

    }

}