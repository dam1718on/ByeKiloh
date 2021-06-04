package com.example.byeKiloh.activitys;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.Mensaje;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraCuenta.*;

public class G_Perfil extends AppCompatActivity {

    private EditText etNumeroTelefono, etEmail, etNombreUsuario, etDireccionUsuario;

    BaseDatos basedatos;
    Cuenta cuenta;
    Mensaje mensaje;

    private String idUsuarioCuenta;

    SQLiteDatabase sqlite0;
    Cursor cursor;

    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_perfil);

        TextView tvPerfil = findViewById(R.id.tvPerfil);

        Button btnGuardarCuenta = findViewById(R.id.btnGuardarCuenta);
        Button btnValidarCuenta = findViewById(R.id.btnValidarCuenta);
        Button btnVolverAlMain4 = findViewById(R.id.btnVolverAlMain4);


        etNumeroTelefono = findViewById(R.id.etNumeroTelefono);
        etEmail = findViewById(R.id.etEmail);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etDireccionUsuario = findViewById(R.id.etDireccionUsuario);

        cuenta = new Cuenta();

        //Recibimos Usuario por intent desde .D_Main
        final Usuario usuarioMain = (Usuario) getIntent().getSerializableExtra("usuario");
        //Se añade Usuario logeado a Cuenta
        cuenta.setEsDE(usuarioMain);

        //Extraemos el id de Usuario
        idUsuarioCuenta = String.valueOf(cuenta.getEsDE().getIdUsuario());

        tvPerfil.setText(cuenta.getEsDE().getAliasUsuario());

        basedatos = new BaseDatos(getApplicationContext());

        arrancarET();

        btnGuardarCuenta.setOnClickListener(v -> {

            //Comprobamos número mínimo de carácteres en cada EditText
            numMinL(etNumeroTelefono, 9, "Número teléfono");
            numMinL(etEmail, 6, "e-mail");
            numMinL(etNombreUsuario, 4, "Nombre Y Apellidos");
            numMinL(etDireccionUsuario, 6, "Direccion");

            if(countError==1) {

                if(!isEmailValid(etEmail.getText().toString())) {

                    mensaje = new Mensaje(getApplicationContext(), "El e-mail no es válido");

                }
                else {

                    actualizarRegistros();

                }

            }

        });

        btnValidarCuenta.setOnClickListener(v -> {

            //Comprobamos que tiene cuenta
            if(cuenta.getIdCuenta()!=0) {

                //Comprobamos que la cuenta no esta validada
                if(!cuenta.isCuentaValidada()) {

                    comprobarEmail("http://192.168.1.39:80/byekiloh/comprobar_email.php");

                }
                else {

                    mensaje = new Mensaje(getApplicationContext(), "La cuenta ya esta " +
                            "validada.\nPara hacer cambios contacte con el admyn");

                }

            }
            else {

                mensaje = new Mensaje(getApplicationContext(), "Debe registrar una " +
                        "cuenta primero");

            }

        });

        btnVolverAlMain4.setOnClickListener(v -> finish());

    }

    //Método para visualizar en los EditText los datos de Cuenta
    public void arrancarET() {

        //Se establece conexion con permisos de lectura
        sqlite0 = basedatos.getReadableDatabase();
        //rawQuery que devuelve los datos de la Cuenta del Usuario logeado
        cursor = sqlite0.rawQuery("SELECT * FROM Cuentas WHERE " +
            "Cuentas.idUsuario LIKE '" + idUsuarioCuenta + "'", null);

        //Si hay registros los muestra en los EditText correspondientes
        if (cursor.getCount() != 0) {

            cursor.moveToFirst();

            cuenta.setIdCuenta(cursor.getInt(cursor.getColumnIndex(_IDCUENTA)));
            cuenta.setNumeroTelefono(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_NUMEROTELEFONO)));
            cuenta.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EMAIL)));
            cuenta.setCuentaValidada(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_VALIDADO)));
            cuenta.setNombreUsuario(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NOMBREUSUARIO)));
            cuenta.setDireccionUsuario(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DIRECCIONUSUARIO)));

            etNumeroTelefono.setText(String.valueOf(cuenta.getNumeroTelefono()));
            etEmail.setText(cuenta.getEmail());
            etNombreUsuario.setText(cuenta.getNombreUsuario());
            etDireccionUsuario.setText(cuenta.getDireccionUsuario());

        }

        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite0.close();

    }

    //Método para actualizar/insertar los datos de Cuenta
    public void actualizarRegistros() {

        //Se establece conexion con permisos de escritura
        sqlite0 = basedatos.getWritableDatabase();
        //rawQuery que devuelve la cuenta del Usuario logeado
        cursor = sqlite0.rawQuery("SELECT * FROM Cuentas WHERE " +
                "Cuentas.idUsuario LIKE '" + idUsuarioCuenta + "'", null);

        //Se actualiza el objeto cuenta
        cuenta.setNumeroTelefono(Integer.parseInt(etNumeroTelefono.getText().toString()));
        cuenta.setEmail(etEmail.getText().toString());
        cuenta.setNombreUsuario(etNombreUsuario.getText().toString());
        cuenta.setDireccionUsuario(etDireccionUsuario.getText().toString());

        //EstructuraCuenta de insercción de datos
        ContentValues content = new ContentValues();
        //Se añaden los valores introducidos de cada campo mediante
        // clave(columna)/valor(valor introducido en el campo de texto)
        content.put(COLUMN_NAME_NUMEROTELEFONO, cuenta.getNumeroTelefono());
        content.put(COLUMN_NAME_EMAIL, cuenta.getEmail());
        content.put(COLUMN_NAME_NOMBREUSUARIO, cuenta.getNombreUsuario());
        content.put(COLUMN_NAME_DIRECCIONUSUARIO, cuenta.getDireccionUsuario());
        content.put(_IDUSUARIO, idUsuarioCuenta);

        //Cláusula where para actualizar Cuentas
        String where = "Cuentas.idUsuario LIKE '" + idUsuarioCuenta + "'";

        //Si hay registros actualizamos Cuenta
        if (cursor.getCount() != 0) {

            sqlite0.update(TABLE_NAME, content, where, null);
            //Registro exitoso
            mensaje = new Mensaje(getApplicationContext(), "Datos actualizados correctamente");

        }
        //Si no los había los insertamos (primer uso del Usuario actual)
        else {

            sqlite0.insert(TABLE_NAME, null, content);
            //Registro exitoso
            mensaje = new Mensaje(getApplicationContext(), "Datos insertados correctamente");

        }

        //Limpiamos el content
        content.clear();
        //Cerramos el cursor
        cursor.close();
        //Se cierra la conexión abierta a la Base de Datos
        sqlite0.close();

    }

    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo) {
        //Contamos el tamaño del EditText introducido
        int num = et.length();
        //Comprobamos si el tamaño del EditText es inferior al minimo exigido
        if(num<minDig) {
            //Retorno de mensaje de error detallado
            mensaje = new Mensaje(getApplicationContext(), "'" + campo + "' tiene " + num +
                    " carácteres\ny el mínimo para ese campo son " + minDig);
            countError += 1;

        }
        else {
            countError=1;
        }

    }

    //Método que conecta con MySQL para guardar los datos del Usuario
    private void ejecutarServicio(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("Mensaje", "Conexión establecida");

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Mensaje", "Error conexión: " + error);

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("idCuenta", String.valueOf(0));
                parametros.put("email", cuenta.getEmail());
                parametros.put("numeroTelefono", String.valueOf(cuenta.getNumeroTelefono()));
                parametros.put("nombreUsuario", cuenta.getNombreUsuario());
                parametros.put("direccionUsuario", cuenta.getDireccionUsuario());
                return parametros;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    //Método que conecta con MySQL para comprobar si el email ya estaba guardado
    private void comprobarEmail(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()) {

                    mensaje = new Mensaje(getApplicationContext(), "Este email ya está " +
                            "registrado");
                    Log.i("Mensaje", "Este email ya está registrado");

                }
                else {

                    try {
                        //guardamos los datos del Usuario en Remoto y validamos su cuenta
                        ejecutarServicio("http://192.168.1.39:80/byekiloh/insertar_cuenta.php");

                        sqlite0 = basedatos.getWritableDatabase();

                        ContentValues content2 = new ContentValues();

                        content2.put(COLUMN_NAME_VALIDADO, "true");

                        //Cláusula where para actualizar Cuentas
                        String where = "Cuentas.idUsuario LIKE '" + cuenta.getEsDE().getIdUsuario() +
                                "'";

                        sqlite0.update(TABLE_NAME, content2, where, null);

                        //Registro exitoso
                        mensaje = new Mensaje(getApplicationContext(), "Cuenta validada " +
                                "correctamente");

                        sqlite0.close();

                    }
                    catch (Exception e) {

                        Log.i("Mensaje", "No se ha validado la cuenta");

                    }

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Mensaje", "Error conexión: " + error);

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", cuenta.getEmail());
                return parametros;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public static boolean isEmailValid(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

}