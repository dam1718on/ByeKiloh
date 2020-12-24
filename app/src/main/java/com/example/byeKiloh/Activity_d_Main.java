package com.example.byeKiloh;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.fragments.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.utils.*;

public class Activity_d_Main extends AppCompatActivity {

    private Button btnPromedios, btnPesajes, btnEjercicios, btnBackups;
    private TextView tvUsuarioMain;
    private Menu menu;

    //Fragments asociados a esta Activity
    Main_a_Promedios mainaPromedios;
    Main_b_Pesajes mainbPesajes;
    Main_c_Ejercicios maincEjercicios;
    Main_d_Backups maindBackups;

    Mensaje mensaje;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_main);

        btnPromedios = findViewById(R.id.btnPromedios);
        btnPesajes = findViewById(R.id.btnPesajes);
        btnEjercicios = findViewById(R.id.btnEjercicios);
        btnBackups = findViewById(R.id.btnBackups);
        tvUsuarioMain=findViewById(R.id.tvUsuarioMain);

        //Recibimos Usuario por intent desde .Activity_a_Login
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);
        //Imprimo la copia del usuario recibido
        tvUsuarioMain.setText(usuario.getUsuario());

        //menu=findViewById(((Menu) R.menu.main_menu));
        //menu.getItem(nav_home);

        //Iniciamos los fragments
        mainaPromedios = new Main_a_Promedios();
        mainbPesajes = new Main_b_Pesajes();
        maincEjercicios = new Main_c_Ejercicios();
        maindBackups = new Main_d_Backups();

        //Fragment que se visualiza por defecto onCreate
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosMAIN, mainaPromedios).commit();

    }

    //Método onClick para cambiar entre fragments
    public void onClick(@org.jetbrains.annotations.NotNull View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(view.getId()) {
            case R.id.btnPromedios:
                transaction.replace(R.id.contenedorFragmentosMAIN, mainaPromedios);
                break;
            case R.id.btnPesajes:
                transaction.replace(R.id.contenedorFragmentosMAIN, mainbPesajes);
                break;
            case R.id.btnEjercicios:
                transaction.replace(R.id.contenedorFragmentosMAIN, maincEjercicios);
                break;
            case R.id.btnBackups:
                transaction.replace(R.id.contenedorFragmentosMAIN, maindBackups);
                break;
        }

        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}