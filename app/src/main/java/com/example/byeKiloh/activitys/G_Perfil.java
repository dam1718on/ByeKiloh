package com.example.byeKiloh.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.byeKiloh.R;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.Usuario;
import com.example.byeKiloh.utils.Mensaje;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraCuenta.*;

public class G_Perfil extends AppCompatActivity {

    EditText etEmail, etNombreyApellidos, etDireccion, etLocalidad, etFechaNac;

    BaseDatos basedatos;
    Mensaje mensaje;
    Usuario usuario;

    String idUserP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_perfil);

        TextView tvPerfil = findViewById(R.id.tvPerfil);

        etEmail = findViewById(R.id.etEmail);
        etNombreyApellidos = findViewById(R.id.etNombreyApellidos);
        etDireccion = findViewById(R.id.etDireccion);
        etLocalidad = findViewById(R.id.etLocalidad);
        etFechaNac = findViewById(R.id.etFechaNac);

        Button btnGuardarCambios = findViewById(R.id.btnGuardarCambios);
        Button btnVolverAlMain4 = findViewById(R.id.btnVolverAlMain4);

        //Recibimos Usuario por intent desde .D_Main
        final Usuario usuarioMain = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioMain);

        idUserP = String.valueOf(usuario.getIdUsuario());

        tvPerfil.setText(usuario.getUsuario());

        basedatos = new BaseDatos(getApplicationContext());

        arrancarET();

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                actualizarRegistros();

                //arrancarET();

            }

        });

        btnVolverAlMain4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }

        });

    }

    //Método para visualizar los datos de la Cuenta
    public void arrancarET() {

        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqliteE = basedatos.getReadableDatabase();
        //rawQuery que devuelve los datos del Usuario logeado
        Cursor cursor = sqliteE.rawQuery("SELECT * FROM Cuentas WHERE " +
                "Cuentas.idUsuario LIKE '" + idUserP + "'", null);

        //Si hay registros los muestra en los EditText correspondientes
        if (cursor.getCount() != 0) {

            cursor.moveToLast();

            usuario.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EMAIL)));;
            usuario.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NOMBRE)));
            usuario.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DIRECCION)));
            usuario.setLocalidad(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOCALIDAD)));
            usuario.setFechaNac(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FECHANAC)));


            etEmail.setText(usuario.getEmail());
            etNombreyApellidos.setText(usuario.getNombre());
            etDireccion.setText(usuario.getDireccion());
            etLocalidad.setText(usuario.getLocalidad());
            etFechaNac.setText(usuario.getFechaNac());

        }

        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqliteE.close();

    }

    //Método para guardar los datos de la Cuenta
    public void actualizarRegistros() {

        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqliteA = basedatos.getWritableDatabase();
        //EstructuraCuenta de insercción de datos
        ContentValues content = new ContentValues();

        usuario.setEmail(etEmail.getText().toString());
        usuario.setNombre(etNombreyApellidos.getText().toString());
        usuario.setDireccion(etDireccion.getText().toString());
        usuario.setLocalidad(etLocalidad.getText().toString());
        usuario.setFechaNac(etFechaNac.getText().toString());

        //Se añaden los valores introducidos de cada campo mediante
        // clave(columna)/valor(valor introducido en el campo de texto)
        content.put(COLUMN_NAME_EMAIL, usuario.getEmail());
        content.put(COLUMN_NAME_NOMBRE, usuario.getNombre());
        content.put(COLUMN_NAME_DIRECCION, usuario.getDireccion());
        content.put(COLUMN_NAME_LOCALIDAD, usuario.getLocalidad());
        content.put(COLUMN_NAME_FECHANAC, usuario.getFechaNac());
        content.put(_IDUSUARIO, idUserP);
        sqliteA.insert(TABLE_NAME, null, content);
        //Registro exitoso
        mensaje = new Mensaje(getApplicationContext(), "Datos guardados correctamente");

        //Se cierra la conexión abierta a la Base de Datos
        sqliteA.close();

        //content.clear();

    }

}