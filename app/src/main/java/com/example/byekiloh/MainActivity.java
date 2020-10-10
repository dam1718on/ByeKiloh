package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;

import android.widget.Spinner;
import android.widget.TextView;

import com.example.byekiloh.utilidades.Usuario;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsuarioMain, tvContraseñaMain;

    private Spinner spinEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);
        //tvContraseñaMain=findViewById(R.id.tvContraseñaMain);

        //spinEjercicios=findViewById(R.id.spinEjercicios);

        //Recibimos el Usuario que logea a través del intent
        final Usuario userL = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario copia del Usuario recibido
        Usuario userR = new Usuario(userL);
        //Imprimo el Nuevo usuario
        tvUsuarioMain.setText(userR.getUser());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu nav_view) {

        getMenuInflater().inflate(R.menu.main_menu, nav_view);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, nav_view);
        return true;

    }

}