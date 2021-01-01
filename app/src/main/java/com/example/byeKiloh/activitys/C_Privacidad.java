package com.example.byeKiloh.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.example.byeKiloh.R;

public class C_Privacidad extends AppCompatActivity {

    private Button btnLeido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_privacidad);

        btnLeido=findViewById(R.id.btnLeido);

        btnLeido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos Intent para volver a .B_Registro
            Intent intent = new Intent(getApplicationContext(), B_Registro.class);
            startActivity(intent);
            }

        });

    }

}