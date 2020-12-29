package com.example.byeKiloh.activitys;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.R;
import com.example.byeKiloh.fragments.*;

public class E_PesajesCrud extends AppCompatActivity {

    private Button btnVolverAlMain;

    //fragments asociados a esta Activity
    E_Pesajes_Create pesaeCreate;
    F_Pesajes_Read pesafRead;
    G_Pesajes_Update pesagUpdate;
    H_Pesajes_Delete pesahDelete;

    String fragPesaCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_pesajescrud);

        btnVolverAlMain = findViewById(R.id.btnVolverAlMain);

        //Iniciamos los fragments
        pesaeCreate = new E_Pesajes_Create();
        pesafRead = new F_Pesajes_Read();
        pesagUpdate = new G_Pesajes_Update();
        pesahDelete = new H_Pesajes_Delete();

        //Recibimos String por intent desde el fragment C_Main_Pesajes
        final String fragPesa = (String) getIntent().getSerializableExtra("nfragp");
        //Creo String_copia del String recibido
        fragPesaCRUD = new String(fragPesa);

        //Fragment de inicio en función del String enviado a través del intent de .D_Main
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedorFragPesa, pesaeCreate);

        //según valor se carga uno u otro fragment
        switch(fragPesaCRUD) {
            case "uno":
                transaction.replace(R.id.contenedorFragPesa, pesaeCreate);
                break;
            case "dos":
                transaction.replace(R.id.contenedorFragPesa, pesafRead);
                break;
            case "tres":
                transaction.replace(R.id.contenedorFragPesa, pesagUpdate);
                break;
            case "cuatro":
                transaction.replace(R.id.contenedorFragPesa, pesahDelete);
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