package com.example.byeKiloh.activitys;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.R;
import com.example.byeKiloh.fragments.*;

public class E_Crud extends AppCompatActivity {

    private LinearLayout fondoCRUD;

    private Button btnVolverAlMain;

    //fragments
    private E_Bascula_Crear basculaCrear;
    private F_Bascula_Borrar basculaBorrar;
    private G_Ejercicio_Crear ejercicioCrear;
    private H_Ejercicio_Borrar ejercicioBorrar;

    public String fragEjerCRUD;
    public String fragEjerUsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_crud);

        fondoCRUD = findViewById(R.id.fondoCRUD);

        btnVolverAlMain = findViewById(R.id.btnVolverAlMain);

        //Iniciamos fragments
        basculaCrear = new E_Bascula_Crear();
        basculaBorrar = new F_Bascula_Borrar();
        ejercicioCrear = new G_Ejercicio_Crear();
        ejercicioBorrar = new H_Ejercicio_Borrar();

        //Recibimos String por intent desde el fragment B_Main_Registros
        final String fragEjer = (String) getIntent().getSerializableExtra("nfrag");

        String[] parts = fragEjer.split("-");
        fragEjerCRUD = parts[0];
        fragEjerUsId = parts[1];

        //Fragment de inicio en función del String enviado a través del intent de .D_Main
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
            .add(R.id.contenedorFragEjer, ejercicioCrear);

        //según valor se carga uno u otro fondo
        switch (fragEjerCRUD) {

            case "e1" :
            case "e2" :
            case "e3" :
            case "e4" :

                fondoCRUD.setBackgroundResource(R.drawable.fondo_ejercicios50y720x1280);
                break;

            case "p1" :
            case "p2" :
            case "p3" :
            case "p4" :

                fondoCRUD.setBackgroundResource(R.drawable.fondo_bascula50y720x1280);
                break;

            default:

                break;

        }
        
        //según valor se carga uno u otro fragment
        switch(fragEjerCRUD) {

            case "e1":
                transaction.replace(R.id.contenedorFragEjer, ejercicioCrear);
                break;

            case "e4":
                transaction.replace(R.id.contenedorFragEjer, ejercicioBorrar);
                break;

            case "p1":
                transaction.replace(R.id.contenedorFragEjer, basculaCrear);
                break;

            case "p4":
                transaction.replace(R.id.contenedorFragEjer, basculaBorrar);
                break;

            default:
                break;

        }

        transaction.commit();

        btnVolverAlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Uso este método para volver al fragment(State) desde el que se ha lanzado esta Activity
            finish();
            }

        });

    }

}