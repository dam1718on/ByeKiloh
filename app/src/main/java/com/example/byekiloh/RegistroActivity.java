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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistroActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7,
            editText8, editText9, editText10;

    private RadioGroup radioGroup2;
    private RadioButton radioButton2, radioButton3;
    private int srsra=0;

    private Button btnReg;

    Mensaje mensaje;
    LoginBaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);
        editText6=findViewById(R.id.editText6);
        editText7=findViewById(R.id.editText7);
        editText8=findViewById(R.id.editText8);
        editText9=findViewById(R.id.editText9);
        editText10=findViewById(R.id.editText10);

        radioGroup2=findViewById(R.id.radioGroup2);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);

        btnReg=findViewById(R.id.btnReg);

        basedatos = new LoginBaseDatos(getApplicationContext());

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Se crea e inicializa el objeto userR
            Usuario userR = new Usuario();
            //Se recogen los datos de los EditText
            userR.setName(editText8.getText().toString());
            userR.setPass(editText9.getText().toString());
            String contraseñaR2 = editText10.getText().toString();

            if (editText1.getText().toString().equals("") || editText2.getText().toString().equals("") ||
                editText3.getText().toString().equals("") || editText4.getText().toString().equals("") ||
                editText5.getText().toString().equals("") || editText6.getText().toString().equals("") ||
                editText7.getText().toString().equals("") || editText8.getText().toString().equals("") ||
                editText9.getText().toString().equals("") || editText10.getText().toString().equals("")||
                srsra==0) {
                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos,\ntodos los campos son obligatorios");
            }
            else {

                //Segundo if comprueba si no coinciden las contraseñas
                if(!userR.getPass().equals(contraseñaR2)) {
                    mensaje = new Mensaje(getApplicationContext(),"Las contraseñas no coinciden");
                }
                else {
                    //Se establece conexion con permisos de lectura
                    SQLiteDatabase sqliteR = basedatos.getReadableDatabase();
                    //Columnas que recogerá los datos de la consulta
                    String[] columnasR = {
                            LoginEstructuraDatos.Estructura._ID,
                            LoginEstructuraDatos.Estructura.COLUMN_NAME_NAME,
                            LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS,
                    };
                    //Cláusula WHERE para buscar por usuario
                    String usuarioR = LoginEstructuraDatos.Estructura.COLUMN_NAME_NAME+" LIKE '"+userR.getName()+"'";
                    //Orden de los resultados devueltos por usuario, de forma Descendente alfabéticamente
                    String ordenSalidaR = LoginEstructuraDatos.Estructura.COLUMN_NAME_NAME + " DESC";
                    //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla,
                    // columnas, usuario y orden de los resultados.
                    Cursor cursorR = sqliteR.query(LoginEstructuraDatos.Estructura.TABLE_NAME, columnasR, usuarioR,
                            null , null, null, ordenSalidaR);
                    //Tercer if comprueba que el cursor no esté vacío
                    if(cursorR.getCount() != 0) {
                        cursorR.moveToFirst();
                        mensaje = new Mensaje(getApplicationContext(),"El nombre de usuario: "+userR.getName()+"\nno está disponible, pruebe con otro");
                        editText8.setText("");
                    }
                    else {
                        //Aqui se introduce el usuario nuevo en la base de datos
                        //Se ganan tambien permisos de escritura
                        sqliteR = basedatos.getWritableDatabase();
                        //Estructura de insercción de datos
                        ContentValues content = new ContentValues();
                        //Se añaden los valores introducidos de cada campo mediante
                        // clave(columna)/valor(valor introducido en el campo de texto)
                        content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_NAME, userR.getName());
                        content.put(LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS, userR.getPass());
                        sqliteR.insert(LoginEstructuraDatos.Estructura.TABLE_NAME, null, content);
                        //Cuarto if que diferencia entre Sr. y Sra.
                        if (srsra==1) {
                            mensaje = new Mensaje(getApplicationContext(),"El Sr.: "+userR.getName()+"\nha sido registrado con éxito");
                        } else {
                            mensaje = new Mensaje(getApplicationContext(),"La Sra.: "+userR.getName()+"\nha sido registrada con éxito");
                        }
                        //Reseteo de EditText y RadioButtonGroup
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        radioGroup2.clearCheck();
                        editText5.setText("");
                        editText6.setText("");
                        editText7.setText("");
                        editText8.setText("");
                        editText9.setText("");
                        editText10.setText("");
                    }
                    //Se cierra la conexión abierta a la Base de Datos
                    sqliteR.close();
                }
            }
            }
        });



        //Intent registro = new Intent(getApplicationContext(), RegistroActivity.class);
        //startActivity(registro);


    }

    //Método del RadioGroupOnClick
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