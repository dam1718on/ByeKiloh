package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Gravity;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;

import com.example.byekiloh.utilidades.*;
import com.example.byekiloh.objetos.*;

public class FirstActivity extends AppCompatActivity {

    private EditText etFecha, etDistancia, etTiempo;

    private TextView tvEjercicioGuardado;
    private RadioGroup radGroup;
    private RadioButton radMS, radKMH;
    //no se usa boolean porque necesito 3 estados (no checked)
    private int velocidad=0;

    private Spinner spinEjercicios;

    private Button btnGuardar, btnBuscar, btnModificar, btnBorrar, btnReset;

    //Declaramos la clase encargada de crear y actualizar la Base de Datos
    BaseDatos basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinEjercicios=findViewById(R.id.spinEjercicios);

        tvEjercicioGuardado=findViewById(R.id.tvEjercicioGuardado);

        etFecha=findViewById(R.id.etFecha);
        etDistancia=findViewById(R.id.etDistancia);
        etTiempo=findViewById(R.id.etTiempo);

        radGroup=findViewById(R.id.radGroup);
        radMS=findViewById(R.id.radMS);
        radKMH=findViewById(R.id.radKMH);

        btnGuardar=findViewById(R.id.btnGuardar);
        btnBuscar=findViewById(R.id.btnBuscar);
        btnModificar=findViewById(R.id.btnModificar);
        btnBorrar=findViewById(R.id.btnBorrar);
        btnReset=findViewById(R.id.btnReset);

        etTiempo.addTextChangedListener(new TextWatcher() {
            int len=0;
            @Override
            public void afterTextChanged(Editable s) {
                String str = etTiempo.getText().toString();
                if(str.length()==2&& len <str.length()){//len check for backspace
                    etTiempo.append(":");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                String str = etTiempo.getText().toString();
                len = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {    }

        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int segundos = Integer.parseInt(etTiempo.getText().toString().substring(0,2))*60+Integer.parseInt(etTiempo.getText().toString().substring(3));

                Ejercicio ejercicio = new Ejercicio(Integer.parseInt(etDistancia.getText().toString()), segundos);

                //velocidad(ejercicio.getDistancia(), ejercicio.getTiempo(), 1);

                //Se inicializa la clase.
                basedatos = new BaseDatos(getApplicationContext());
                //Clase que permite llamar a los métodos para crear, eliminar, leer y actualizar registros. Se establecen permisos de escritura.
                SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                String fecha = String.valueOf(ejercicio.getFecha());
                String distancia = String.valueOf(ejercicio.getDistancia());
                String tiempo = String.valueOf(ejercicio.getTiempo());

                ContentValues content = new ContentValues();

                if(distancia.equals("") || tiempo.equals("")) {
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Revise los datos introducidos. Todos los campos son obligatorios", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();
                } else {
                    //Se añaden los valores introducidos de cada campo mediante clave(columna)/valor(valor introducido en el campo de texto)
                    //content.put(FirstEstructuraDatos.Estructura.COLUMN_NAME_FECHA,fecha);
                    //content.put(FirstEstructuraDatos.Estructura.COLUMN_NAME_DISTANCIA, distancia);
                    //content.put(FirstEstructuraDatos.Estructura.COLUMN_NAME_TIEMPO, tiempo);
                    //sqlite.insert(FirstEstructuraDatos.Estructura.TABLE_NAME, null, content);
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "El Ejercicio ha sido almacenado", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
                    toast.show();

                    spinEjercicios.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,
                            Collections.singletonList(ejercicio.toString())));

                    //edProducto.setText("");
                    //edCantidad.setText("");
                    //edId.setText("");
                }
                //Se cierra la conexión abierta a la Base de Datos
                sqlite.close();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etFecha.setText("");
                etDistancia.setText("");
                etTiempo.setText("");

                radGroup.clearCheck();
                radGroup.setOnCheckedChangeListener(null);

                tvEjercicioGuardado.setText("");

                spinEjercicios.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,
                        Collections.singletonList("")));

            }
        });



    }

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
            //tvEjercicio.setText(mst);
        } else if (velocidad==1 && modo==1){
            //tvEjercicio.setText(mst);
            tvEjercicioGuardado.setText(mst);
        } else if (velocidad==2 && modo==0){
            //tvEjercicio.setText(kmht);
        } else if (velocidad==2 && modo==1){
            //tvEjercicio.setText(kmht);
            tvEjercicioGuardado.setText(kmht);
        }

        mensaje(kmh);
    }

    //Sin implementar aun
    public void settings(View view){
        Toast toast= Toast.makeText(getApplicationContext(),
                "Settings!!!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }

    //Método para visualizar mensajes según velocidad
    public void mensaje(Float kmh){
        if (kmh==0){
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Selecciona velocidad!!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
            toast.show();
        } else if (kmh <= 3) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tienes que andar más rapido...", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast.show();
        } else if (kmh > 3 && kmh <= 4) {
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Un poquito mas rapido", Toast.LENGTH_SHORT);
            toast2.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast2.show();
        } else if (kmh > 4) {
            Toast toast3 = Toast.makeText(getApplicationContext(),
                    "Estas progresando, good job!!", Toast.LENGTH_SHORT);
            toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast3.show();
        }
    }

    //Método del RadioGroupOnClick
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radMS:
            if (checked)
            velocidad = 1;
            break;

            case R.id.radKMH:
            if (checked)
            velocidad = 2;
            break;
        }
    }

}