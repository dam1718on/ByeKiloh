package com.example.byekiloh.crearcuenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.byekiloh.*;
import com.example.byekiloh.objetos.*;
import com.example.byekiloh.utilidades.*;

public class CrearCuenta2de3 extends AppCompatActivity {

    private EditText etNombre, etDireccion, etLocalidad, etEmail, etFechaNac;

    private RadioGroup radioGroup2;
    private RadioButton radioButton2, radioButton3;
    private int srsra=0;

    private int countError=1;

    private Button btnLog, btnPaso3;

    Mensaje mensaje;
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2de3);

        etNombre=findViewById(R.id.etNombre);
        etDireccion=findViewById(R.id.etDireccion);
        etLocalidad=findViewById(R.id.etLocalidad);
        etEmail=findViewById(R.id.etEmail);
        etFechaNac=findViewById(R.id.etFechaNac);

        radioGroup2=findViewById(R.id.radioGroup2);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);

        btnLog=findViewById(R.id.btnVolverAtrasRegistro);
        btnPaso3=findViewById(R.id.btnPaso3);

        basedatos = new BaseDatos(getApplicationContext());

        final Usuario user = (Usuario) getIntent().getSerializableExtra("usuario");

        btnPaso3.setOnClickListener(new View.OnClickListener() {
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
                        etFechaNac.getText().toString().equals("")) {
                        mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                            "todos los campos son obligatorios");
                    }
                    else {
                        countError=1;
                        //Comprobamos número mínimo de carácteres en cada EditText
                        numMinL(etNombre, 4, "Nombre");
                        numMinL(etDireccion, 4, "Dirección");
                        numMinL(etLocalidad, 4, "Localidad");
                        numMinL(etEmail, 4, "E-mail");
                        numMinL(etFechaNac, 10, "Fecha de Nacimiento");
                        //Tercer if comprueba si se cumple con el mínimo de carácteres
                        if(countError==1) {
                            //Se crea e inicializa el objeto userR
                            Usuario userR = new Usuario(user);
                            //Se recogen los datos de los EditText
                            userR.setNombre(etNombre.getText().toString());
                            userR.setDireccion(etDireccion.getText().toString());
                            userR.setLocalidad(etLocalidad.getText().toString());
                            userR.setEmail(etEmail.getText().toString());
                            userR.setFechaNac(etFechaNac.getText().toString());
                            //Se establece conexion con permisos de escritura
                            SQLiteDatabase sqliteR = basedatos.getWritableDatabase();
                            //Columnas que recogerá los datos de la consulta
                            String[] columnasR = {
                                Tablas.EstructuraCuenta._IDUSER,
                                Tablas.EstructuraCuenta.COLUMN_NAME_SEXO,
                                Tablas.EstructuraCuenta.COLUMN_NAME_NOMBRE,
                                Tablas.EstructuraCuenta.COLUMN_NAME_DIRECCION,
                                Tablas.EstructuraCuenta.COLUMN_NAME_LOCALIDAD,
                                Tablas.EstructuraCuenta.COLUMN_NAME_EMAIL,
                                Tablas.EstructuraCuenta.COLUMN_NAME_FECHANAC,
                            };
                            //Cuarto if que diferencia entre Sr. y Sra. pasando el atributo al objeto usuario
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
                            //Se cierra la conexión abierta a la Base de Datos
                            sqliteR.close();
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