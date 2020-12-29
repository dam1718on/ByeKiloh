package com.example.byeKiloh.activitys;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.R;
import com.example.byeKiloh.fragments.*;

public class F_EjerciciosCrud extends AppCompatActivity {

    private Button btnVolverAlMain2;

    //fragments asociados a esta Activity
    I_Ejercicios_Create ejeriCreate;
    J_Ejercicios_Read ejerjRead;
    K_Ejercicios_Update ejerkUpload;
    L_Ejercicios_Delete ejerlDelete;

    public String fragEjerCRUD;
    public String fragEjerUsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_ejercicioscrud);

        btnVolverAlMain2 = findViewById(R.id.btnVolverAlMain2);

        //Iniciamos los fragments
        ejeriCreate = new I_Ejercicios_Create();
        ejerjRead = new J_Ejercicios_Read();
        ejerkUpload = new K_Ejercicios_Update();
        ejerlDelete = new L_Ejercicios_Delete();

        //Recibimos String por intent desde el fragment C_Main_Ejercicios
        final String fragEjer = (String) getIntent().getSerializableExtra("nfrag");

        String[] parts = fragEjer.split("-");
        fragEjerCRUD = parts[0];
        fragEjerUsId = parts[1];



        //Fragment de inicio en función del String enviado a través del intent de .D_Main
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
            .add(R.id.contenedorFragEjer, ejeriCreate);

        //según valor se carga uno u otro fragment
        switch(fragEjerCRUD) {
            case "1":
                transaction.replace(R.id.contenedorFragEjer, ejeriCreate);
                break;
            case "2":
                transaction.replace(R.id.contenedorFragEjer, ejerjRead);
                break;
            case "3":
                transaction.replace(R.id.contenedorFragEjer, ejerkUpload);
                break;
            case "4":
                transaction.replace(R.id.contenedorFragEjer, ejerlDelete);
                break;
            default:
                break;
        }
        transaction.commit();

        btnVolverAlMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Uso este método para volver al fragment(State) desde el que se ha lanzado esta Activity
            finish();
            }

        });

    }

}