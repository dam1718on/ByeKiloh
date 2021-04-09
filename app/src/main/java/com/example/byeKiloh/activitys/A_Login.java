package com.example.byeKiloh.activitys;

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
import android.widget.ImageView;

import com.example.byeKiloh.R;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.utils.*;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraUsuario.*;

public class A_Login extends AppCompatActivity {

    private Button btnCrearCuenta, btnIniciarSesion;
    private CheckBox cbMantenerSesion;
    private EditText etUsuario, etPass;
    private ImageView imgPassL;

    private boolean select = false;
    //Variables para SharedPreferences
    String defaultValue;
    private String userSP;

    //Strings para desEncriptar la pass
    private static String publicKey;
    private static String privateKey;
    private static String encode_pass;
    private static String decode_pass;

    BaseDatos basedatos;
    Mensaje mensaje;
    VaciarEditText vaciarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_login);

        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        cbMantenerSesion = findViewById(R.id.cbMantenerSesion);
        etUsuario = findViewById(R.id.etUsuario);
        etPass = findViewById(R.id.etPass);
        imgPassL = findViewById(R.id.imgPassL);

        //SharedPreferences para Recordar nombre de usuario
        SharedPreferences prefSesion = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userSP = prefSesion.getString("usuario", defaultValue);
        etUsuario.setText(userSP);

        basedatos = new BaseDatos(getApplicationContext());

        //TextView que permite la visualización de la contraseña
        imgPassL.setOnClickListener(new View.OnClickListener() {
            boolean selectPassL = false;
            @Override
            public void onClick(View v) {

            if(!selectPassL) {
                selectPassL = true;
                etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                if(etPass.getText().length() > 0) {
                    etPass.setSelection(etPass.getText().length());
                    imgPassL.setBackgroundResource(R.drawable.ic_visible);
                }

            } else {
                selectPassL = false;
                etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                if(etPass.getText().length() > 0) {
                    etPass.setSelection(etPass.getText().length());
                    imgPassL.setBackgroundResource(R.drawable.ic_visible_no);
                }
            }
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos Intent para ir a .B_Registro
            Intent intent = new Intent(getApplicationContext(), B_Registro.class);
            startActivity(intent);
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Primer if comprueba si están vacíos los dos primeros EditText
            if(etUsuario.getText().toString().equals("")||etPass.getText().toString().equals("")) {

                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos"
                        + "\ntodos los campos son obligatorios");

            } else {

                //Se crea e inicializa el objeto usuario
                Usuario usuario = new Usuario();
                //Se recogen los datos de los EditText
                usuario.setUsuario(etUsuario.getText().toString());
                usuario.setContraseña(etPass.getText().toString());
                //Se establece conexion con permisos de lectura
                SQLiteDatabase sqlite = basedatos.getReadableDatabase();
                //Columnas que recogerá los datos de la consulta
                String[] columnasL = {_IDUSUARIO, COLUMN_NAME_USUARIO, COLUMN_NAME_CLAVEPUBLICA,
                    COLUMN_NAME_CLAVEPRIVADA, COLUMN_NAME_CONTRASEÑA};
                //Cláusula WHERE para buscar por usuario
                String usuarioL = COLUMN_NAME_USUARIO + " LIKE '" + usuario.getUsuario() + "'";
                //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                String ordenSalidaNameL = COLUMN_NAME_USUARIO + " DESC";
                //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                // columnas, usuario y orden de los resultados.
                Cursor cursor = sqlite.query(TABLE_NAME, columnasL, usuarioL,null ,
                        null, null, ordenSalidaNameL);

                //Segundo if comprueba que el cursor no esté vacío
                if(cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    //Asignamos los valores del cursor a las variables
                    publicKey = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CLAVEPUBLICA));
                    privateKey = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CLAVEPRIVADA));
                    encode_pass = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CONTRASEÑA));

                    //DesEncriptamos la contraseña
                    deCrypt(v);

                    //Tercer if comprueba que las contraseñas coinciden
                    if(usuario.getContraseña().equals(decode_pass)) {

                        //Login correcto
                        //Incluimos el valor del atributo id en el objeto usuario a logear
                        int identificadorL = cursor.getInt(cursor.getColumnIndex(_IDUSUARIO));
                        usuario.setIdUsuario(identificadorL);
                        mensaje = new Mensaje(getApplicationContext(), "Bienvenid@:  " +
                                usuario.getUsuario());

                        //Cuarto if comprueba si Recordar nombre de usuario está checked
                        if(cbMantenerSesion.isChecked()) {

                            //Le pasamos el nombre de usuario al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos",
                                    Context.MODE_PRIVATE);
                            userSP = usuario.getUsuario();
                            SharedPreferences.Editor editor = prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            vaciarEditText = new VaciarEditText(etPass);

                        } else {

                            //Le pasamos usuario="" al SharedPreferences
                            SharedPreferences prefSesion = getSharedPreferences("datos",
                                    Context.MODE_PRIVATE);
                            userSP = "";
                            SharedPreferences.Editor editor = prefSesion.edit();
                            editor.putString("usuario", userSP);
                            editor.commit();
                            vaciarEditText = new VaciarEditText(etUsuario, etPass);

                        }

                        //Creamos intent para ir a .D_Main y le enviamos Usuario
                        Intent intent = new Intent(getApplicationContext(), D_Main.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);

                    } else {

                        mensaje = new Mensaje(getApplicationContext(), "La contraseña no es " +
                            "correcta");
                        vaciarEditText = new VaciarEditText(etPass);

                    }

                } else {

                    mensaje = new Mensaje(getApplicationContext(), "El usuario: " +
                        usuario.getUsuario() + " no existe");
                    vaciarEditText = new VaciarEditText(etUsuario, etPass);

                }
                //Se cierra cursor
                cursor.close();
                //Se cierra la conexión a la Base de Datos
                sqlite.close();

            }
        }
        });

    }

    //Método que DesEncripta la contraseña
    public void deCrypt(View view) {

        try {

            //Creamos objeto de la clase RSA
            RSA rsaD = new RSA();

            //Le pasamos el contexto
            rsaD.setContext(getBaseContext());

            //Usamos los Strings guardadas anteriormente para generar nuevas Claves
            rsaD.setPublicKeyString(publicKey);
            rsaD.setPrivateKeyString(privateKey);

            //Desciframos
            decode_pass = rsaD.Decrypt(encode_pass);

        } catch (Exception e) {    }

    }

}