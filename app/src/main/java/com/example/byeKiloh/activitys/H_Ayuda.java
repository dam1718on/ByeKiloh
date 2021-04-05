package com.example.byeKiloh.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.byeKiloh.R;

public class H_Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_ayuda);

        Button btnVolverAlMain3 = findViewById(R.id.btnVolverAlMain3);

        btnVolverAlMain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uso este método para volver al fragment(State) desde el que se ha lanzado esta Activity
                finish();
            }

        });

    }

}