package com.example.byeKiloh.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.example.byeKiloh.R;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.datapersistence.Tablas;
import com.example.byeKiloh.objects.Usuario;
import com.example.byeKiloh.utils.Mensaje;
import com.example.byeKiloh.utils.VaciarEditText;

import at.favre.lib.crypto.bcrypt.BCrypt;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraUsuario.COLUMN_NAME_ALIASUSUARIO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraUsuario.COLUMN_NAME_CONTRASENIA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraUsuario.TABLE_NAME;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraUsuario._IDUSUARIO;

public class I_Contrasenia extends AppCompatActivity {

    BaseDatos basedatos;
    Mensaje mensaje;
    Usuario usuario;
    VaciarEditText vaciarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icontrasenia);

        EditText etUsuarioRC = findViewById(R.id.etUsuarioRC);
        EditText etEmailRC = findViewById(R.id.etEmailRC);
        EditText etPassRC = findViewById(R.id.etPassRC);
        EditText etPassRERC = findViewById(R.id.etPassRERC);

        Button btnVolverALoginRC = findViewById(R.id.btnVolverALoginRC);
        Button btnGuardarCambios = findViewById(R.id.btnGuardarCambios);

        basedatos = new BaseDatos(getApplicationContext());

        btnVolverALoginRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase sqlite;

                if(etUsuarioRC.getText().toString().equals("") ||
                        etEmailRC.getText().toString().equals("") ||
                        etPassRC.getText().toString().equals("") ||
                        etPassRERC.getText().toString().equals("")) {

                    mensaje = new Mensaje(getApplicationContext(), "Revise los datos " +
                            "introducidos\ntodos los campos son obligatorios");

                }
                else if(!etPassRC.getText().toString().equals(etPassRERC.getText().toString())) {

                    mensaje = new Mensaje(getApplicationContext(), "Las Contraseñas " +
                            "introducidas\nno coinciden");

                }
                else if(etPassRC.length()<6) {

                    mensaje = new Mensaje(getApplicationContext(), "Las Contraseñas deben " +
                            "tener 6\ncarácteres como mínimo");

                }
                else {
                    //Se crea e inicializa el objeto usuario
                    usuario = new Usuario();
                    //Se recogen los datos de los EditText
                    usuario.setAliasUsuario(etUsuarioRC.getText().toString());
                    usuario.setContrasenia(etPassRC.getText().toString());

                    //Se establece conexion con permisos de lectura
                    sqlite = basedatos.getReadableDatabase();
                    //Columnas que recogerá los datos de la consulta
                    String[] columnas = {_IDUSUARIO, COLUMN_NAME_ALIASUSUARIO,
                            COLUMN_NAME_CONTRASENIA};
                    //Cláusula WHERE para buscar por usuario
                    String usuarioSQL = COLUMN_NAME_ALIASUSUARIO + " LIKE '" +
                            usuario.getAliasUsuario() + "'";

                    //Orden de los resultados devueltos por usuario, de forma
                    // Descendente alfabéticamente
                    String ordenSalida = COLUMN_NAME_ALIASUSUARIO + " DESC";
                    //Ejecuta la sentencia devolviendo los resultados de los parámetros
                    // pasados de tabla, columnas, usuario y orden de los resultados.
                    Cursor cursor = sqlite.query(TABLE_NAME, columnas, usuarioSQL,
                            null, null, null, ordenSalida);

                    //Comprobamos que el cursor no esté vacío
                    if (cursor.getCount() != 0) {

                        cursor.moveToFirst();
                        usuario.setIdUsuario(cursor.getInt(cursor.getColumnIndex(_IDUSUARIO)));

                        Cursor cursor2 = sqlite.rawQuery("SELECT Cuentas.email FROM Cuentas WHERE " +
                                        "Cuentas.idUsuario LIKE '" + usuario.getIdUsuario() + "'",
                                null);
                        cursor2.moveToFirst();

                        String emailRecogido = cursor2.getString(cursor2.getColumnIndex(
                                Tablas.EstructuraCuenta.COLUMN_NAME_EMAIL));

                        if(!etEmailRC.getText().toString().equals(emailRecogido)) {

                            mensaje = new Mensaje(getApplicationContext(), "El email indicado " +
                                    "no esta asociado a ese Usuario");

                        }
                        else {

                            //Aqui se introduce el usuario nuevo en la base de datos
                            //Se ganan tambien permisos de escritura
                            sqlite = basedatos.getWritableDatabase();

                            //Recogemos contraseña del EditText y le hacemos hash
                            String password = usuario.getContrasenia();
                            String bcryptHashString = BCrypt.withDefaults().hashToString
                                    (10, password.toCharArray());

                            //EstructuraUsuario de insercción de datos
                            ContentValues content = new ContentValues();
                            //Se añaden los valores introducidos de cada campo mediante
                            // clave(columna)/valor(valor introducido en el campo de texto)
                            content.put(COLUMN_NAME_CONTRASENIA, bcryptHashString);

                            //Cláusula where para actualizar Cuentas
                            String where = "Usuarios.idUsuario LIKE '" + usuario.getIdUsuario() + "'";

                            sqlite.update(TABLE_NAME, content, where, null);
                            //Registro exitoso
                            mensaje = new Mensaje(getApplicationContext(), "Datos actualizados " +
                                    "correctamente");

                            //Reseteo de los EditText
                            vaciarEditText = new VaciarEditText(etUsuarioRC, etEmailRC, etPassRC,
                                    etPassRERC);

                        }

                        cursor2.close();

                    }
                    else {

                        mensaje = new Mensaje(getApplicationContext(), "El Usuario indicado " +
                                "no esta registrado");

                    }

                    cursor.close();
                    sqlite.close();

                }

            }

        });

    }

}