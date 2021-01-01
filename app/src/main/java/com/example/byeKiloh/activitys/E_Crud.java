package com.example.byeKiloh.activitys;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.R;
import com.example.byeKiloh.fragments.*;

public class E_Crud extends AppCompatActivity {

    private Button btnVolverAlMain2;

    //fragments
    private E_Pesajes_Create pesaeCreate;
    private F_Pesajes_Read pesafRead;
    private G_Pesajes_Update pesagUpdate;
    private H_Pesajes_Delete pesahDelete;
    private I_Ejercicios_Create ejeriCreate;
    private J_Ejercicios_Read ejerjRead;
    private K_Ejercicios_Update ejerkUpload;
    private L_Ejercicios_Delete ejerlDelete;

    public String fragEjerCRUD;
    public String fragEjerUsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_crud);

        btnVolverAlMain2 = findViewById(R.id.btnVolverAlMain);

        //Iniciamos los fragments
        pesaeCreate = new E_Pesajes_Create();
        pesafRead = new F_Pesajes_Read();
        pesagUpdate = new G_Pesajes_Update();
        pesahDelete = new H_Pesajes_Delete();
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
            case "e1":
                transaction.replace(R.id.contenedorFragEjer, ejeriCreate);
                break;
            case "e2":
                transaction.replace(R.id.contenedorFragEjer, ejerjRead);
                break;
            case "e3":
                transaction.replace(R.id.contenedorFragEjer, ejerkUpload);
                break;
            case "e4":
                transaction.replace(R.id.contenedorFragEjer, ejerlDelete);
                break;
            case "p1":
                transaction.replace(R.id.contenedorFragEjer, pesaeCreate);
                break;
            case "p2":
                transaction.replace(R.id.contenedorFragEjer, pesafRead);
                break;
            case "p3":
                transaction.replace(R.id.contenedorFragEjer, pesagUpdate);
                break;
            case "p4":
                transaction.replace(R.id.contenedorFragEjer, pesahDelete);
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