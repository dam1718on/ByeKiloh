package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;

import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.byekiloh.LoginEstructuraDatos.Estructura;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgUser;
    private EditText etUser, etPass, etPassRe;
    private Button btnRegistro, btnLogin;
    private CheckBox radSesion;

    String userSP;
    String defaultValue;

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
        radSesion=findViewById(R.id.radSesion);

        imgUser.setImageResource(R.drawable.userh);

        SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userSP = prefSesion.getString("usuario", defaultValue);
        //verLike=like.toString();
        etUser.setText(userSP);

        basedatos = new LoginBaseDatos(getApplicationContext());

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Se crea e inicializa el objeto userR
            Usuario userR = new Usuario();
            //Se recogen los datos de los EditText
            userR.setName(etUser.getText().toString());
            userR.setPass(etPass.getText().toString());
            String contraseñaR2 = etPassRe.getText().toString();
            //Primer if comprueba si están vacíos todos los EditText
            if(userR.getName().equals("") || userR.getPass().equals("") || contraseñaR2.equals("")) {
                mensaje("Revise los datos introducidos,\ntodos los campos son obligatorios");
            }
            else {
                //Segundo if comprueba si no coinciden las contraseñas
                if(!userR.getPass().equals(contraseñaR2)) {
                    mensaje("Las contraseñas no coinciden");
                }
                else {
                    //Se establece conexion con permisos de lectura
                    SQLiteDatabase sqliteR = basedatos.getReadableDatabase();
                    //Columnas que recogerá los datos de la consulta
                    String[] columnasR = {
                        Estructura._ID,
                        Estructura.COLUMN_NAME_NAME,
                        Estructura.COLUMN_NAME_PASS,
                    };
                    //Cláusula WHERE para buscar por usuario
                    String usuarioR = Estructura.COLUMN_NAME_NAME+" LIKE '"+userR.getName()+"'";
                    //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                    String ordenSalidaR = Estructura.COLUMN_NAME_NAME + " DESC";
                    //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                    // columnas, usuario y orden de los resultados.
                    Cursor cursorR = sqliteR.query(Estructura.TABLE_NAME, columnasR, usuarioR,
                            null , null, null, ordenSalidaR);
                    //Tercer if comprueba que el cursor no esté vacío
                    if(cursorR.getCount() != 0) {
                        cursorR.moveToFirst();
                        mensaje("El nombre de usuario: "+userR.getName()+"\nno está disponible, pruebe con otro");
                    }
                    else {
                        //Aqui se introduce el usuario nuevo en la base de datos
                        //Se ganan tambien permisos de escritura
                        sqliteR = basedatos.getWritableDatabase();
                        //Estructura de insercción de datos
                        ContentValues content = new ContentValues();
                        //Se añaden los valores introducidos de cada campo mediante
                        // clave(columna)/valor(valor introducido en el campo de texto)
                        content.put(Estructura.COLUMN_NAME_NAME, userR.getName());
                        content.put(Estructura.COLUMN_NAME_PASS, userR.getPass());
                        sqliteR.insert(Estructura.TABLE_NAME, null, content);
                        mensaje("El usuario: "+userR.getName()+"\nha sido registrado con éxito");
                        //Reseteo de EditText
                        etUser.setText("");
                        etPass.setText("");
                        etPassRe.setText("");
                    }
                    //Se cierra la conexión abierta a la Base de Datos
                    sqliteR.close();
                }
            }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se crea e inicializa el objeto userL
                Usuario userL = new Usuario();
                //Se recogen los datos de los EditText
                userL.setName(etUser.getText().toString());
                userL.setPass(etPass.getText().toString());
                String contraseñaL2 = etPassRe.getText().toString();
                //Primer if comprueba si están vacíos los dos primeros EditText
                if(userL.getName().equals("") || userL.getPass().equals("")) {
                    mensaje("Revise los datos introducidos\ntodos los campos son obligatorios");
                }
                else {
                    //Segundo if comprueba que no esté vacía la contraseña2
                    if(!contraseñaL2.equals("")) {
                        mensaje("Repita contraseña... ha de estar vacía");
                        etPassRe.setText("");
                    }
                    else {
                        //Se establece conexion con permisos de lectura
                        SQLiteDatabase sqliteL = basedatos.getReadableDatabase();
                        //Columnas que recogerá los datos de la consulta
                        String[] columnasL = {
                                Estructura._ID,
                                Estructura.COLUMN_NAME_NAME,
                                Estructura.COLUMN_NAME_PASS,
                        };
                        //Cláusula WHERE para buscar por usuario
                        String usuarioL = Estructura.COLUMN_NAME_NAME+" LIKE '"+userL.getName()+"'";
                        //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                        String ordenSalidaNameL = Estructura.COLUMN_NAME_NAME + " DESC";
                        //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                        // columnas, usuario y orden de los resultados.
                        Cursor cursorL = sqliteL.query(Estructura.TABLE_NAME, columnasL, usuarioL,
                                null , null, null, ordenSalidaNameL);
                        //Tercer if comprueba que el cursor no esté vacío
                        if(cursorL.getCount() != 0) {
                            cursorL.moveToFirst();
                            String passCNL = cursorL.getString(cursorL.getColumnIndex(Estructura.COLUMN_NAME_PASS));
                            //Cuarto if comprueba que las contraseñas coinciden
                            if(userL.getPass().equals(passCNL)) {
                                //Como es el usuario correcto, incluimos el valor del atributo id en el objeto userL
                                int identificadorL = cursorL.getInt(cursorL.getColumnIndex(Estructura._ID));
                                userL.setId(identificadorL);
                                mensaje("Login correcto\n"+userL.toString());
                                //Quinto if comprueba si Mantener sesion iniciada esta checked
                                if(radSesion.isChecked()){
                                    SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
                                    userSP = userL.getName();
                                    SharedPreferences.Editor editor=prefSesion.edit();
                                    editor.putString("usuario", userSP);
                                    editor.commit();
                                    etPass.setText("");
                                }
                                else {
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
                                mensaje("La contraseña no es correcta");
                                etPass.setText("");
                            }
                        }
                        else {
                            mensaje("El usuario: "+userL.getName()+" no existe");
                            etPass.setText("");
                        }
                        //Se cierra la conexión abierta a la Base de Datos
                        sqliteL.close();
                    }
                }
            }
        });

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