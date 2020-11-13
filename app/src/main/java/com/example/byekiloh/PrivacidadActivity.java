package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class PrivacidadActivity extends AppCompatActivity {

    private Button btnLeido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad);

        btnLeido=findViewById(R.id.btnLeido);

        btnLeido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos Intent para volver a .RegistroActivity
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivity(intent);

            }

        });

    }

}