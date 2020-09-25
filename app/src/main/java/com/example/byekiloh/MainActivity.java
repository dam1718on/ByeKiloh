package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsuarioMain, tvContraseñaMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);
        tvContraseñaMain=findViewById(R.id.tvContraseñaMain);

        final Usuario userL = (Usuario) getIntent().getSerializableExtra("usuario");

        Usuario userR = new Usuario(userL);

        tvUsuarioMain.setText(userR.getUser());





    }
}