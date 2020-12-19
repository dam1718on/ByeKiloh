package com.example.byeKiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.byeKiloh.fragments.FragPromedios;
import com.example.byeKiloh.objetos.Usuario;
import com.example.byeKiloh.utils.*;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llPromedios, llEjercicios;
    private TextView tvUsuarioMain;
    private Menu menu;
    FragPromedios fragPromedios;


    Mensaje mensaje;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llPromedios = findViewById(R.id.llPromedios);

        llEjercicios = findViewById(R.id.llEjercicios);

        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);
        //Recibimos Usuario por intent desde .LoginActivity
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);
        //Imprimo la copia del usuario recibido
        tvUsuarioMain.setText(usuario.getUsuario());

        //menu=findViewById(((Menu) R.menu.main_menu));
        //menu.getItem(nav_home);

        //Iniciamos el fragmento por defecto correspondiente a los Promedios
        fragPromedios= new FragPromedios();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFrag, fragPromedios).commit();

        //LinearLayout clickeable que contiene un intent
        llPromedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* //Creamos intent para ir a .EjercicioActivity y le enviamos Usuario
                Intent intent = new Intent(getApplicationContext(), EjercicioActivity.class);
                intent.putExtra("usuario", usuarioLogin);
                startActivity(intent);

                //mensaje = new Mensaje(getApplicationContext(), "CRUD Ejercicio");*/

            }

        });

        //LinearLayout clickeable que contiene un intent
        llEjercicios.setOnClickListener(new View.OnClickListener() {
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