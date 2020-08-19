package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Color;
import android.os.Bundle;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgUser;
    private EditText etUser, etPass, etPassRe;
    private Button btnRegistro, btnLogin;

    LoginBaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgUser=findViewById(R.id.imgUser);
        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        etPassRe=findViewById(R.id.etPassRe);
        btnRegistro=findViewById(R.id.btnRegistro);
        btnLogin=findViewById(R.id.btnLogin);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Strings que recogen los datos de los EditText
                String usuarioR = etUser.getText().toString();
                String contraseñaR = etPass.getText().toString();
                String contraseñaR2 = etPassRe.getText().toString();
                //Primer if comprueba si están vacíos los EditText
                if(usuarioR.equals("") || contraseñaR.equals("") || contraseñaR2.equals("")) {
                    mensaje("Revise los datos introducidos\nTodos los campos son obligatorios");
                } else {
                    //Segundo if comprueba si son distintas las contraseñas
                    if(!contraseñaR.equals(contraseñaR2)) {
                        mensaje("Las contraseñas no coinciden");
                    } else {
                        basedatos = new LoginBaseDatos(getApplicationContext());
                        //Se establecen permisos de lectura
                        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
                        //Columnas que recogerá los datos de la consulta
                        String[] columnas = {
                                LoginEstructuraDatos.Estructura._ID,
                                LoginEstructuraDatos.Estructura.COLUMN_NAME_USER,
                                LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS,
                        };
                        //Cláusula WHERE para buscar por usuario
                        String usuario = LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " LIKE '" +  etUser.getText().toString() + "'";
                        //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                        String ordenSalida = LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " DESC";
                        //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla, columnas, usuario y orden de los resultados.
                        Cursor cursor = sqlite.query(LoginEstructuraDatos.Estructura.TABLE_NAME, columnas, usuario,null , null, null, ordenSalida);
                        //Tercer if comprueba que el cursor no esté vacío
                        if(cursor.getCount() != 0) {
                            cursor.moveToFirst();
                            //Lo recorremos buscando matches
                            for (int i=0;i<=cursor.getCount();i++){
                                cursor.move(i);
                                String usuarioC = cursor.getString(cursor.getColumnIndex(LoginEstructuraDatos.Estructura.COLUMN_NAME_USER));
                                //Cuarto if compara el usuario recogido con los existentes en la BBDD
                                if (usuarioC.equals(usuarioR)) {
                                    mensaje("El usuario: "+usuarioR+" ya está en uso\nPrueba con otro");
                                    break;
                                }
                            }
                        } else {
                            //sqliteL.close();
                            //Aqui se introduce el usuario nuevo en la base de datos. IMPLEMENTAR
                            //basedatos = new LoginBaseDatos(getApplicationContext());
                            //Se establecen permisos de escritura
                            //SQLiteDatabase
                                    sqlite = basedatos.getWritableDatabase();

                            ContentValues content = new ContentValues();
                            //Se añaden los valores introducidos de cada campo mediante clave(columna)/valor(valor introducido en el campo de texto)
                            content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_USER, usuarioR);
                            content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS, contraseñaR);
                            sqlite.insert(LoginEstructuraDatos.Estructura.TABLE_NAME, null, content);

                            mensaje("El usuario :"+usuarioR+" ha sido registrado");

                            etUser.setText("");
                            etPass.setText("");
                            etPassRe.setText("");
                            //Se cierra la conexión abierta a la Base de Datos
                            sqlite.close();
                        }
                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuarioL = etUser.getText().toString();
                String contraseñaL = etPass.getText().toString();
                String contraseñaL2 = etPassRe.getText().toString();

                //Se inicializa la clase.
                basedatos = new LoginBaseDatos(getApplicationContext());
                //Se establecen permisos de lectura
                SQLiteDatabase sqlite = basedatos.getReadableDatabase();

                //Columnas que devolverá la consulta.
                String[] columnas = {
                        LoginEstructuraDatos.Estructura._ID,
                        LoginEstructuraDatos.Estructura.COLUMN_NAME_USER,
                        LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS,
                };

                //Cláusula WHERE para buscar por producto
                String usuario = LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " LIKE '" +  etUser.getText().toString() + "'";
                //Orden de los resultados devueltos por Producto, de forma Descendente alfabéticamente
                String ordenSalida = LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " DESC";

                if(usuario.equals("")) {
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();
                } else {
                    //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla, columnas, producto y orden de los resultados.
                    Cursor cursor = sqlite.query(LoginEstructuraDatos.Estructura.TABLE_NAME, columnas, usuario,null , null, null, ordenSalida);

                    if(cursor.getCount() != 0) {
                        cursor.moveToFirst();

                        long identificador = cursor.getLong(cursor.getColumnIndex(LoginEstructuraDatos.Estructura._ID));
                        //Toast.makeText(this, "El Usuario " +  edProducto.getText().toString()
                         //       + " está almacenado con Identificador " + identificador, 3000).show();
                        //edProducto.setText("");
                        //edCantidad.setText("");
                        //edId.setText("");

                    }
                    else
                    {
                        //Toast.makeText(this, "El Producto '" + edProducto.getText().toString()  + "' no existe en la Base de Datos.", 3000).show();
                    }

                }
                //Se cierra la conexión abierta a la Base de Datos
                sqlite.close();

            }




                /*if(usuarioL.equals("") || contraseñaL.equals("") || contraseñaL2.equals("")) {
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Revise los datos introducidos.\nTodos los campos son obligatorios", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();
                }
                else {
                    if(contraseñaL.equals(contraseñaL2)) {
                        sqlite.execSQL("SELECT " + LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " FROM " +
                            LoginEstructuraDatos.Estructura.TABLE_NAME + " WHERE " +
                            LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + " = " + usuarioL);
                        if(usuarioL.equals(sqlite)) {
                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "Login correcto", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                            toast.show();
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Este usuario no existe", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
                            toast.show();
                        }
                    }
                    else {
                        Toast toast= Toast.makeText(getApplicationContext(),
                            "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                        toast.show();
                    }
                }
                sqlite.close();
            }*/
        });
                /*String usuarioL=etUser.getText().toString();
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
                    } else {                        Toast toast= Toast.makeText(getApplicationContext(),"Contraseña incorrecta",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                        toast.show();                    }
                } else {                    Toast toast= Toast.makeText(getApplicationContext(),"Usuario incorrecto",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();                }
                etUser.setText("");
                etPass.setText("");            }*/
        }

    //Método para lanzar toast personalizados
    public void mensaje(String cad) {
        Spannable centeredText = new SpannableString(cad);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, cad.length() - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast toast= Toast.makeText(getApplicationContext(),
                centeredText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }

}
//spinEjercicios.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,
//      Collections.singletonList(ejercicio.toString())));

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

//long identificador = cursor.getLong(cursor.getColumnIndex(LoginEstructuraDatos.Estructura._ID));
//Toast.makeText(this, "El Usuario " +  edProducto.getText().toString()
//       + " está almacenado con Identificador " + identificador, 3000).show();