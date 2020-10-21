package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.example.byekiloh.utilidades.Usuario;

public class EjercicioActivity extends AppCompatActivity {

    private EditText etFecha, etDistancia, etTiempo;

    private Button btnGuardar, btnActualizar, btnBorrar, btnVolverE;

    /*
    //no se usa boolean porque necesito 3 estados (no checked)
    private int velocidad=0;*/

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
        Usuario userR = new Usuario(userL);


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

        /*btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                velocidad(Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()), 0);
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
}