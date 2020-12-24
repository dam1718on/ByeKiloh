package com.example.byeKiloh;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.fragments.*;

public class Activity_e_CRUD extends AppCompatActivity {

    private Button btnVolverAlMain;

    //fragments asociados a esta Activity
    CRUD_a_Create crudaCreate;
    CRUD_b_Read crudbRead;
    CRUD_c_Update crudcUpload;
    CRUD_d_Delete cruddDelete;

    String fragCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_crud);

        btnVolverAlMain = findViewById(R.id.btnVolverAlMain);

        //Iniciamos los fragments
        crudaCreate = new CRUD_a_Create();
        crudbRead = new CRUD_b_Read();
        crudcUpload = new CRUD_c_Update();
        cruddDelete = new CRUD_d_Delete();

        //Recibimos String por intent desde el fragment Main_c_Ejercicios
        final String frag = (String) getIntent().getSerializableExtra("nfrag");
        //Creo String_copia del String recibido
        fragCRUD = new String(frag);

        //Fragment de inicio en función del String enviado a través del intent de .Activity_d_Main
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
            .add(R.id.contenedorFragmentosCRUD, crudaCreate);

        //según valor se carga uno u otro fragment
        switch(fragCRUD) {
            case "uno":
                transaction.replace(R.id.contenedorFragmentosCRUD, crudaCreate);
                break;
            case "dos":
                transaction.replace(R.id.contenedorFragmentosCRUD, crudbRead);
                break;
            case "tres":
                transaction.replace(R.id.contenedorFragmentosCRUD, crudcUpload);
                break;
            case "cuatro":
                transaction.replace(R.id.contenedorFragmentosCRUD, cruddDelete);
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