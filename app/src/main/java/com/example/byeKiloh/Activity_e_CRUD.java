package com.example.byeKiloh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.byeKiloh.fragments.CRUD_a_Create;
import com.example.byeKiloh.fragments.CRUD_b_Read;
import com.example.byeKiloh.fragments.CRUD_c_Upload;
import com.example.byeKiloh.fragments.CRUD_d_Delete;
import com.example.byeKiloh.fragments.Main_a_Promedios;
import com.example.byeKiloh.fragments.Main_b_Pesajes;
import com.example.byeKiloh.fragments.Main_c_Ejercicios;
import com.example.byeKiloh.fragments.Main_d_Backups;
import com.example.byeKiloh.objects.Usuario;

public class Activity_e_CRUD extends AppCompatActivity {

    private TextView tvFragMain;

    CRUD_a_Create crudCreate;
    CRUD_b_Read crudRead;
    CRUD_c_Upload crudUpload;
    CRUD_d_Delete crudDDelete;

    String fragCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_crud);

        tvFragMain = findViewById(R.id.tvFragMain);

        //Iniciamos los fragments
        crudCreate = new CRUD_a_Create();
        crudRead = new CRUD_b_Read();
        crudUpload = new CRUD_c_Upload();
        crudDDelete = new CRUD_d_Delete();

        //Recibimos String por intent desde el fragment Main_c_Ejercicios
        final String frag = (String) getIntent().getSerializableExtra("nfrag");
        //Creo String_copia del String recibido
        fragCRUD = new String(frag);
        //Imprimo la copia del String recibido
        tvFragMain.setText(fragCRUD);


        //Aun no funciona
        if(fragCRUD=="uno") {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD,
                crudCreate).commit();
        }

        if(fragCRUD=="dos") {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD,
                    crudRead).commit();
        }

        if(fragCRUD=="tres") {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD,
                    crudUpload).commit();
        }

        if(fragCRUD=="cuatro") {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD,
                    crudDDelete).commit();
        }


/*        switch(fragCRUD) {
            case fragCRUD=="crudCreate":
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD, crudCreate).commit();
                break;
            case fragCRUD=="crudRead":
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD, crudRead).commit();
                break;
            case fragCRUD=="crudUpload":
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD, crudUpload).commit();
                break;
            case fragCRUD=="crudDDelete":
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD, crudDDelete).commit();
                break;
        }*/


        //Fragment que se visualiza por defecto onCreate
        //getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosCRUD, crudCreate).commit();



    }

    /*
    //Método onClick para cambiar entre fragments
    public void onClick(@org.jetbrains.annotations.NotNull View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(view.getId()) {
            case R.id.btnPromedios:
                transaction.replace(R.id.contenedorFragmentosMAIN, mainaPromedios);
                break;
            case R.id.btnPesajes:
                transaction.replace(R.id.contenedorFragmentosMAIN, mainbPesajes);
                break;
            case R.id.btnEjercicios:
                transaction.replace(R.id.contenedorFragmentosMAIN, maincEjercicios);
                break;
            case R.id.btnBackups:
                transaction.replace(R.id.contenedorFragmentosMAIN, maindBackups);
                break;
        }

        transaction.commit();

    }
    */

}