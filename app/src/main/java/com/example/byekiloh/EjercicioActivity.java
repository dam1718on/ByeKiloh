package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.example.byekiloh.utilidades.BaseDatos;
import com.example.byekiloh.utilidades.Ejercicio;
import com.example.byekiloh.utilidades.Mensaje;
import com.example.byekiloh.utilidades.Tablas;
import com.example.byekiloh.utilidades.Usuario;

public class EjercicioActivity extends AppCompatActivity {

    private EditText etFecha, etDistancia, etTiempo;

    private Button btnGuardar, btnActualizar, btnBorrar, btnVolverE;

    BaseDatos basedatos;
    Mensaje mensaje;
    Ejercicio ejercicio;

    private int countError = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);

        etFecha=findViewById(R.id.etFecha);
        etDistancia=findViewById(R.id.etDistancia);
        etTiempo=findViewById(R.id.etTiempo);

        btnGuardar=findViewById(R.id.btnGuardar);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnBorrar=findViewById(R.id.btnBorrar);
        btnVolverE=findViewById(R.id.btnVolverE);

        //Recibimos el Usuario que logea a través del intent
        final Usuario userL = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario copia del Usuario recibido
        final Usuario userR = new Usuario(userL);

        basedatos = new BaseDatos(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Primer if comprueba que no hay EditText vacíos
            if(etFecha.getText().toString().equals("") || etDistancia.getText().toString().equals("")
                || etFecha.getText().toString().equals("")) {

                mensaje = new Mensaje(getApplicationContext(), "Revise los datos introducidos\n" +
                        "todos los campos son obligatorios");

            } else {

                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etFecha, 6, "Fecha");
                numMinL(etDistancia, 3, "Distancia");
                numMinL(etTiempo, 2, "Tiempo");

                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if (countError == 1) {

                    //Se crea objeto Ejercicio con parámetros
                    ejercicio = new Ejercicio(userR.getId(), etFecha.getText().toString(),
                        Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()));

                    //Se establece conexión con permisos de escritura
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    //EstructuraUsuario de insercción de datos
                    ContentValues content = new ContentValues();

                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                    content.put(Tablas.EstructuraEjercicio._IDUSER, ejercicio.getIdUsuario());
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_FECHA,
                            String.valueOf(ejercicio.getFecha()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_DISTANCIA,
                            String.valueOf(ejercicio.getDistancia()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_TIEMPO,
                            String.valueOf(ejercicio.getTiempo()));
                    content.put(Tablas.EstructuraEjercicio.COLUMN_NAME_VELOCIDAD,
                            String.valueOf(ejercicio.getVelocidad()));
                    sqlite.insert(Tablas.EstructuraEjercicio.TABLE_NAME, null, content);

                    mensaje = new Mensaje(getApplicationContext(), "El Ejercicio ha sido almacenado");

                    //Vaciamos los EditText
                    etFecha.setText("");
                    etDistancia.setText("");
                    etTiempo.setText("");

                    //Se cierra la conexión abierta a la Base de Datos
                    sqlite.close();

                }

            }

            }

        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            etFecha.setText("");
            etDistancia.setText("");
            etTiempo.setText("");

            }

        });

        btnVolverE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Creamos intent para ir a .MainActivity y le enviamos Usuario
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("usuario", userL);
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
    /* en desuso
    //Método para calcular velocidad, el atributo modo indica el boton usado 0=calcular 1=guardar
    private void velocidad(int distancia, int tiempo, int modo){
        float ms = (float) distancia / (tiempo * 60);
        float kmh = ms * 36 / 10;
        String mst = "Has recorrido "+distancia+" metros en "+tiempo+
                " minutos haciendo una velocidad"+" media de "+String.format("%.2f", ms)+" m/s";
        String kmht = "Has recorrido "+distancia+" metros en "+tiempo+
                " minutos haciendo una velocidad"+" media de "+String.format("%.2f", kmh)+" km/h";
        if (velocidad==0){
            ms = 0;
            kmh = 0;
        } else if (velocidad==1 && modo==0){
            tvEjercicio.setText(mst);
        } else if (velocidad==1 && modo==1){
            tvEjercicio.setText(mst);
            tvEjercicioGuardado.setText(mst);
        } else if (velocidad==2 && modo==0){
            tvEjercicio.setText(kmht);
        } else if (velocidad==2 && modo==1){
            tvEjercicio.setText(kmht);
            tvEjercicioGuardado.setText(kmht);
        }
        mensaje(kmh);
    }*/

}