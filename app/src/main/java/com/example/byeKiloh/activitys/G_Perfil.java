package com.example.byeKiloh.activitys;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.Mensaje;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraCuenta.*;

public class G_Perfil extends AppCompatActivity {

    private EditText etNumeroTelefono, etEmail, etNombreUsuario, etDireccionUsuario;

    BaseDatos basedatos;
    Cuenta cuenta;
    Mensaje mensaje;

    private String idUsuarioCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_perfil);

        TextView tvPerfil = findViewById(R.id.tvPerfil);

        Button btnGuardarCambios = findViewById(R.id.btnGuardarCambios);
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

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                actualizarRegistros();

            }

        });

        btnValidarCuenta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Validación Rápida para hacer test

                SQLiteDatabase sqlite2 = basedatos.getWritableDatabase();

                ContentValues content2 = new ContentValues();

                content2.put(COLUMN_NAME_VALIDADO, "true");

                //Cláusula where para actualizar Cuentas
                String where = "Cuentas.idUsuario LIKE '" + cuenta.getEsDE().getIdUsuario() + "'";

                sqlite2.update(TABLE_NAME, content2, where, null);

                //Registro exitoso
                mensaje = new Mensaje(getApplicationContext(), "Cuenta validada correctamente");

                sqlite2.close();

            }

        });

        btnVolverAlMain4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }

        });

    }

    //Método para visualizar en los EditText los datos de Cuenta
    public void arrancarET() {

        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqliteE = basedatos.getReadableDatabase();
        //rawQuery que devuelve los datos de la Cuenta del Usuario logeado
        Cursor cursor = sqliteE.rawQuery("SELECT * FROM Cuentas WHERE " +
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
        sqliteE.close();

    }

    //Método para actualizar/insertar los datos de Cuenta
    public void actualizarRegistros() {

        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqliteA = basedatos.getWritableDatabase();
        //rawQuery que devuelve la cuenta del Usuario logeado
        Cursor cursor = sqliteA.rawQuery("SELECT * FROM Cuentas WHERE " +
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
        String where = "Cuentas.idCuenta LIKE '" + cuenta.getIdCuenta() + "'";

        //Si hay registros actualizamos Cuenta
        if (cursor.getCount() != 0) {

            sqliteA.update(TABLE_NAME, content, where, null);
            //Registro exitoso
            mensaje = new Mensaje(getApplicationContext(), "Datos actualizados correctamente");

        }
        //Si no los había los insertamos (primer uso del Usuario actual)
        else {

            sqliteA.insert(TABLE_NAME, null, content);
            //Registro exitoso
            mensaje = new Mensaje(getApplicationContext(), "Datos insertados correctamente");

        }

        //Cerramos el cursor
        cursor.close();
        //Se cierra la conexión abierta a la Base de Datos
        sqliteA.close();

    }

}