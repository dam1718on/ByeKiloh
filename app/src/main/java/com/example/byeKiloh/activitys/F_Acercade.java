package com.example.byeKiloh.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.byeKiloh.R;

public class F_Acercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_acercade);

        Button btnVolverAlMain2 = findViewById(R.id.btnVolverAlMain2);

        btnVolverAlMain2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Uso este método para volver al fragment(State) desde el que se ha lanzado esta Activity
            finish();
        }

    });

    }

}