package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.text.SpannableString;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.byekiloh.utilidades.*;

public class RegistroActivity extends AppCompatActivity {

    private Button btnVolverAtras, btnRegistro;

    private CheckBox cbAcepto;

    private EditText etUsuario, etContraseña, etContraseñaRe;

    private TextView tvCondicionesServicio, tvPoliticaPrivacidad, tvPass, tvPassRe;

    private boolean select = false;

    private int countError = 1;

    BaseDatos basedatos;
    Mensaje mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnVolverAtras =findViewById(R.id.btnVolverAtras);
        btnRegistro =findViewById(R.id.btnRegistro);

        cbAcepto=findViewById(R.id.cbAcepto);

        etUsuario =findViewById(R.id.etUsuario);
        etContraseña =findViewById(R.id.etContraseña);
        etContraseñaRe =findViewById(R.id.etContraseñaRe);

        tvCondicionesServicio=findViewById(R.id.tvCondicionesServicio);
        tvPoliticaPrivacidad=findViewById(R.id.tvPoliticaPrivacidad);
        tvPass=findViewById(R.id.tvPass);
        tvPassRe=findViewById(R.id.tvPassRe);

        basedatos = new BaseDatos(getApplicationContext());

        //Convertimos en enlaces los TextViews de, Condiciones del Servicio y Política de privacidad
        SpannableString contentC = new SpannableString(tvCondicionesServicio.getText());
        //Esta línea permite que aparezca subrayado el TextView, en desuso
        //contentC.setSpan(new UnderlineSpan(), 0, tvCondicionesServicio.length(), 0);
        tvCondicionesServicio.setText(contentC);

        SpannableString contentP = new SpannableString(tvPoliticaPrivacidad.getText());
        //contentC.setSpan(new UnderlineSpan(), 0, tvPoliticaPrivacidad.length(), 0);
        tvPoliticaPrivacidad.setText(contentP);

        tvCondicionesServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            mensaje = new Mensaje(getApplicationContext(), "Términos y Condiciones del\nservicio " +
                "y Política de privacidad");

            //Creamos Intent para visualizar .PrivacidadActivity
            Intent intent = new Intent(getApplicationContext(), PrivacidadActivity.class);
            startActivity(intent);

            }

        });

        tvPoliticaPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            mensaje = new Mensaje(getApplicationContext(), "Términos y Condiciones del\nservicio " +
                "y Política de privacidad");

            //Creamos Intent para visualizar .PrivacidadActivity
            Intent intent = new Intent(getApplicationContext(), PrivacidadActivity.class);
            startActivity(intent);

            }

        });

        //TextView que permite la visualización de "Confirmar Contraseña"
        tvPassRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (select == false){

                    select = true;
                    etContraseñaRe.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    if (etContraseñaRe.getText().length() > 0) {

                        etContraseñaRe.setSelection(etContraseñaRe.getText().length());
                        tvPassRe.setBackgroundResource(R.drawable.ic_action_password_visible);

                    }

                }else {

                    select = false;
                    etContraseñaRe.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    if (etContraseñaRe.getText().length() > 0) {

                        etContraseñaRe.setSelection(etContraseñaRe.getText().length());
                        tvPassRe.setBackgroundResource(R.drawable.ic_action_password_visible_off);

                    }
                }

            }

        });

        //TextView que permite la visualización de "Contraseña"
        tvPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (select == false){

                select = true;
                etContraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                if (etContraseña.getText().length() > 0) {

                    etContraseña.setSelection(etContraseña.getText().length());
                    tvPass.setBackgroundResource(R.drawable.ic_action_password_visible);

                }

            }else {

                select = false;
                etContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());

                if (etContraseña.getText().length() > 0) {

                    etContraseña.setSelection(etContraseña.getText().length());
                    tvPass.setBackgroundResource(R.drawable.ic_action_password_visible_off);

                }
            }

        }

        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Primer if comprueba que no hay EditText vacíos
            Usuario useR = null;
            if (etUsuario.getText().toString().equals("") ||
                etContraseña.getText().toString().equals("") ||
                etContraseñaRe.getText().toString().equals("")) {

                    mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                        "todos los campos son obligatorios");

            } else {

                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etUsuario, 4, "Usuario");
                numMinL(etContraseña, 6, "Contraseña");
                numMinL(etContraseñaRe, 6, "Confirmar Contraseña");

                //Segundo if comprueba si se cumple con el mínimo de carácteres
                if (countError == 1) {

                    //Se crea e inicializa el objeto useR
                    useR = new Usuario();
                    //Se recogen los datos de los EditText
                    useR.setUser(etUsuario.getText().toString());
                    useR.setPass(etContraseña.getText().toString());
                    String passRe = etContraseñaRe.getText().toString();

                    //Tercer if comprueba si no coinciden las contraseñas
                    if (!useR.getPass().equals(passRe)) {

                        mensaje = new Mensaje(getApplicationContext(), "Las Contraseñas " +
                            "introducidas\nno coinciden");

                    } else {

                        //Cuarto if comprueba que los Terminos y Condiciones están aceptados
                        if (cbAcepto.isChecked()){
                            //Se establece conexion con permisos de lectura
                            SQLiteDatabase sqlite = basedatos.getReadableDatabase();
                            //Columnas que recogerá los datos de la consulta
                            String[] columnas = {
                                Tablas.EstructuraUsuario._ID,
                                Tablas.EstructuraUsuario.COLUMN_NAME_NAME,
                                Tablas.EstructuraUsuario.COLUMN_NAME_PASS,
                            };

                            //Cláusula WHERE para buscar por usuario
                            String usuario = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " LIKE '" + useR.getUser() + "'";
                            //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                            String ordenSalida = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " DESC";
                            //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                            // columnas, usuario y orden de los resultados.
                            Cursor cursor = sqlite.query(Tablas.EstructuraUsuario.TABLE_NAME, columnas, usuario,
                                null, null, null, ordenSalida);

                            //Quinto if comprueba que el cursor no esté vacío
                            if (cursor.getCount() != 0) {

                                cursor.moveToFirst();
                                mensaje = new Mensaje(getApplicationContext(), "El nombre de usuario: " +
                                        useR.getUser() + "\nno está disponible, pruebe con otro");
                                etUsuario.setText("");

                            } else {

                                //Aqui se introduce el usuario nuevo en la base de datos
                                //Se ganan tambien permisos de escritura
                                sqlite = basedatos.getWritableDatabase();
                                //EstructuraUsuario de insercción de datos
                                ContentValues content = new ContentValues();

                                //Se añaden los valores introducidos de cada campo mediante
                                // clave(columna)/valor(valor introducido en el campo de texto)
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_NAME, useR.getUser());
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_PASS, useR.getPass());
                                sqlite.insert(Tablas.EstructuraUsuario.TABLE_NAME, null, content);

                                //Registro exitoso
                                mensaje = new Mensaje(getApplicationContext(), "El usuario " + useR.getUser() +
                                    "\nse registró con éxito");

                                //Reseteo de los EditText
                                etUsuario.setText("");
                                etContraseña.setText("");
                                etContraseñaRe.setText("");

                                //Abrimos un intent para volver a .LoginActivity
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);

                            }

                            //Se cierra la conexión abierta a la Base de Datos
                            sqlite.close();

                        } else {

                            mensaje = new Mensaje(getApplicationContext(), "Debe aceptar los Términos " +
                                "y Condiciones\npara poder registrarse");

                        }
                    }
                }
            }

            }

        });

        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Creamos Intent para volver a .LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

            }

        });

    }

    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo){

        int num = et.length();

        if(num<minDig){

            mensaje = new Mensaje(getApplicationContext(), "'" + campo + "' tiene " +
            String.valueOf(num) + " carácteres\ny el mínimo para ese campo son " + minDig);
            countError = countError + 1;

        }

    }

}