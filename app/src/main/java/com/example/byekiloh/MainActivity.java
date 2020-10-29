package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.text.SpannableString;

import android.view.Menu;
import android.view.View;

import android.widget.Spinner;
import android.widget.TextView;

import com.example.byekiloh.utilidades.*;

public class MainActivity extends AppCompatActivity {

    private TextView tvEjercicio;
    private TextView tvUsuarioMain, tvContraseñaMain;

    private Menu menu;

    Mensaje mensaje;

    private Spinner spinEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEjercicio=findViewById(R.id.tvEjercicio);
        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);
        //tvContraseñaMain=findViewById(R.id.tvContraseñaMain);

        //spinEjercicios=findViewById(R.id.spinEjercicios);

        //Recibimos Usuario a través de un intent
        final Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario_recibido
        Usuario userR = new Usuario(usuario);
        //Imprimo el Nuevo usuario
        tvUsuarioMain.setText(userR.getUsuario());

        //menu=findViewById(((Menu) R.menu.main_menu));
        //menu.getItem(nav_home);


        //Convertimos en enlaces los TextViews de, Condiciones del Servicio y Política de privacidad
        SpannableString contentC = new SpannableString(tvEjercicio.getText());
        //Esta línea permite que aparezca subrayado el TextView, en desuso
        //contentC.setSpan(new UnderlineSpan(), 0, tvCondicionesServicio.length(), 0);
        tvEjercicio.setText(contentC);



        tvEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mensaje = new Mensaje(getApplicationContext(), "CRUD Ejercicio");

                //Creamos intent para ir a .EjercicioActivity y le enviamos Usuario
                Intent intent = new Intent(getApplicationContext(), EjercicioActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);

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