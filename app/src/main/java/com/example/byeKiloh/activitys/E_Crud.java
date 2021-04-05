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

        fondoCRUD = findViewById(R.id.fondoCRUD);

        btnVolverAlMain = findViewById(R.id.btnVolverAlMain);

        //Iniciamos los fragments
        pesaeCreate = new E_Pesajes_Create();
        pesafRead = new F_Pesajes_Read();
        pesagUpdate = new G_Pesajes_Update();
        pesahDelete = new H_Pesajes_Delete();
        ejeriCreate = new I_Ejercicios_Create();
        ejerjRead = new J_Ejercicios_Read();
        ejerkUpload = new K_Ejercicios_Update();
        ejerlDelete = new L_Ejercicios_Delete();

        //Recibimos String por intent desde el fragment B_Main_Pesaje ó C_Main_Ejercicios 
        final String fragEjer = (String) getIntent().getSerializableExtra("nfrag");

        String[] parts = fragEjer.split("-");
        fragEjerCRUD = parts[0];
        fragEjerUsId = parts[1];

        //Fragment de inicio en función del String enviado a través del intent de .D_Main
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
            .add(R.id.contenedorFragEjer, ejeriCreate);

        //según valor se carga uno u otro fondo
        switch (fragEjerCRUD) {

            case "e1" :
            case "e2" :
            case "e3" :
            case "e4" :

                fondoCRUD.setBackgroundResource(R.drawable.cinta69y720x1280);
                break;

            case "p1" :
            case "p2" :
            case "p3" :
            case "p4" :

                fondoCRUD.setBackgroundResource(R.drawable.bascula69y720x1280);
                break;

            default:

                break;

        }
        
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

        btnVolverAlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Uso este método para volver al fragment(State) desde el que se ha lanzado esta Activity
            finish();
            }

        });

    }

}