package com.example.byeKiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.byeKiloh.utilidades.*;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llEjercicio;
    private TextView tvUsuarioMain;
    private Menu menu;

    Mensaje mensaje;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llEjercicio = findViewById(R.id.llEjercicio);

        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);
        //Recibimos Usuario por intent desde .LoginActivity
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);
        //Imprimo la copia del usuario recibido
        tvUsuarioMain.setText(usuario.getUsuario());

        //menu=findViewById(((Menu) R.menu.main_menu));
        //menu.getItem(nav_home);

        //LinearLayout clickeable que contiene un intent
        llEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos intent para ir a .EjercicioActivity y le enviamos Usuario
                Intent intent = new Intent(getApplicationContext(), EjercicioActivity.class);
                intent.putExtra("usuario", usuarioLogin);
                startActivity(intent);

                //mensaje = new Mensaje(getApplicationContext(), "CRUD Ejercicio");

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

}