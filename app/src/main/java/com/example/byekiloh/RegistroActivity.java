package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.byekiloh.utilidades.*;
import com.example.byekiloh.objetos.*;

public class RegistroActivity extends AppCompatActivity {

    private ImageView imgPerfil;

    private EditText etNombre, etDireccion, etLocalidad, etEmail, etFechaNac, etUsuario, etContraseña,etContraseñaRe;

    private RadioGroup radioGroup2;
    private RadioButton radioButton2, radioButton3;
    private int srsra=0;

    private int countError=1;

    private Button btnReg, btnLog;

    Mensaje mensaje;
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        imgPerfil=findViewById(R.id.imgPerfil);

        etNombre=findViewById(R.id.etNombre);
        etDireccion=findViewById(R.id.etDireccion);
        etLocalidad=findViewById(R.id.etLocalidad);
        etEmail=findViewById(R.id.etEmail);
        etFechaNac=findViewById(R.id.etFechaNac);
        etUsuario=findViewById(R.id.etUsuario);
        etContraseña=findViewById(R.id.etContraseña);
        etContraseñaRe=findViewById(R.id.etContraseñaRe);

        radioGroup2=findViewById(R.id.radioGroup2);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);

        btnReg=findViewById(R.id.btnReg);
        btnLog=findViewById(R.id.btnLog);

        basedatos = new BaseDatos(getApplicationContext());

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Primer if comprueba que hay algún radioButton checkeado
            if(srsra==0) {
                mensaje = new Mensaje(getApplicationContext(), "Seleccione Tratamiento");
            }
            else {
                //Segundo if comprueba que no hay EditText vacíos
                if(etNombre.getText().toString().equals("") || etDireccion.getText().toString().equals("") ||
                etLocalidad.getText().toString().equals("") || etEmail.getText().toString().equals("") ||
                etFechaNac.getText().toString().equals("") || etUsuario.getText().toString().equals("") ||
                etContraseña.getText().toString().equals("") || etContraseñaRe.getText().toString().equals("")) {
                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos," +
                        "\ntodos los campos son obligatorios");
                }
                else {
                    countError=1;
                    //Comprobamos número mínimo de carácteres en cada EditText
                    numMinL(etNombre, 3, "Nombre");
                    numMinL(etDireccion, 3, "Dirección");
                    numMinL(etLocalidad, 3, "Localidad");
                    numMinL(etEmail, 3, "E-mail");
                    numMinL(etFechaNac, 9, "Fecha de Nacimiento");
                    numMinL(etUsuario, 3, "Usuario");
                    numMinL(etContraseña, 5, "contraseña");
                    numMinL(etContraseñaRe, 5, "Repita Contraseña");
                    //Tercer if comprueba si se cumple con el mínimo de carácteres
                    if(countError==1) {
                        //Se crea e inicializa el objeto userR
                        Usuario userR = new Usuario();
                        //Se recogen los datos de los EditText
                        userR.setNombre(etNombre.getText().toString());
                        userR.setDireccion(etDireccion.getText().toString());
                        userR.setLocalidad(etLocalidad.getText().toString());
                        userR.setEmail(etEmail.getText().toString());
                        userR.setFechaNac(etFechaNac.getText().toString());
                        userR.setUser(etUsuario.getText().toString());
                        userR.setPass(etContraseña.getText().toString());
                        String contraseñaR2 = etContraseñaRe.getText().toString();
                        //Cuarto if comprueba si no coinciden las contraseñas
                        if (!userR.getPass().equals(contraseñaR2)) {
                            mensaje = new Mensaje(getApplicationContext(), "Las contraseñas no coinciden");
                        }
                        else {
                            //Se establece conexion con permisos de lectura
                            SQLiteDatabase sqliteR = basedatos.getReadableDatabase();
                            //Columnas que recogerá los datos de la consulta
                            String[] columnasR = {
                                    Tablas.EstructuraUsuario._ID,
                                    Tablas.EstructuraUsuario.COLUMN_NAME_NAME,
                                    Tablas.EstructuraUsuario.COLUMN_NAME_PASS,
                            };
                            //Cláusula WHERE para buscar por usuario
                            String usuarioR = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " LIKE '" + userR.getUser() + "'";
                            //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                            String ordenSalidaR = Tablas.EstructuraUsuario.COLUMN_NAME_NAME + " DESC";
                            //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                            // columnas, usuario y orden de los resultados.
                            Cursor cursorR = sqliteR.query(Tablas.EstructuraUsuario.TABLE_NAME, columnasR, usuarioR,
                                    null, null, null, ordenSalidaR);
                            //Quinto if comprueba que el cursor no esté vacío
                            if (cursorR.getCount() != 0) {
                                cursorR.moveToFirst();
                                mensaje = new Mensaje(getApplicationContext(), "El nombre de usuario: " +
                                        userR.getUser() + "\nno está disponible, pruebe con otro");
                                etUsuario.setText("");
                            }
                            else {
                                //Aqui se introduce el usuario nuevo en la base de datos
                                //Se ganan tambien permisos de escritura
                                sqliteR = basedatos.getWritableDatabase();
                                //EstructuraUsuario de insercción de datos
                                ContentValues content = new ContentValues();
                                //Se añaden los valores introducidos de cada campo mediante
                                // clave(columna)/valor(valor introducido en el campo de texto)
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_NAME, userR.getUser());
                                content.put(Tablas.EstructuraUsuario.COLUMN_NAME_PASS, userR.getPass());
                                sqliteR.insert(Tablas.EstructuraUsuario.TABLE_NAME, null, content);
                                //Volvemos a rellenar el cursorR el cual incluye los datos ya insertados
                                cursorR = sqliteR.query(Tablas.EstructuraUsuario.TABLE_NAME, columnasR, usuarioR,
                                        null, null, null, ordenSalidaR);
                                cursorR.moveToFirst();
                                //Extraemos el atributo id y se lo pasamos al objeto Usuario
                                int identificadorL = cursorR.getInt(cursorR.getColumnIndex(Tablas.EstructuraUsuario._ID));
                                userR.setId(identificadorL);
                                //Sexto if que diferencia entre Sr. y Sra. pasando el atributo al objeto usuario
                                if (srsra == 1) {
                                    userR.setSexo("Hombre");
                                }
                                else {
                                    userR.setSexo("Mujer");
                                }
                                //EstructuraCuenta de insercción de datos
                                ContentValues content2 = new ContentValues();
                                //Se añaden los valores introducidos de cada campo mediante
                                // clave(columna)/valor(valor introducido en el campo de texto)
                                content2.put(Tablas.EstructuraCuenta._IDUSER, userR.getId());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_SEXO, userR.getSexo());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_NOMBRE, userR.getNombre());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_DIRECCION, userR.getDireccion());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_LOCALIDAD, userR.getLocalidad());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_EMAIL, userR.getEmail());
                                content2.put(Tablas.EstructuraCuenta.COLUMN_NAME_FECHANAC, userR.getFechaNac());
                                sqliteR.insert(Tablas.EstructuraCuenta.TABLE_NAME, null, content2);
                                //Registro exitoso
                                mensaje = new Mensaje(getApplicationContext(), "El usuario: " +
                                        userR.getUser() + "\nha sido registrado con éxito");
                                //Reseteo del RadioButtonGroup y de los EditText
                                radioGroup2.clearCheck();
                                etNombre.setText("");
                                etDireccion.setText("");
                                etLocalidad.setText("");
                                etEmail.setText("");
                                etFechaNac.setText("");
                                etUsuario.setText("");
                                etContraseña.setText("");
                                etContraseñaRe.setText("");
                            }
                            //Se cierra la conexión abierta a la Base de Datos
                            sqliteR.close();
                        }
                    }
                }
            }
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
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
        int num;
        num=et.getText().toString().length();
        if(num<=minDig){
            mensaje = new Mensaje(getApplicationContext(), "El campo "+campo+"\ntiene "+
                    String.valueOf(num)+" carácteres, corrígalo");
            countError=countError+1;
        }

    }
    //Método del RadioButtonOnClick
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton2:
                if (checked)
                    srsra = 1;
                    break;

            case R.id.radioButton3:
                if (checked)
                    srsra = 2;
                    break;
        }
    }

}