package com.example.byeKiloh.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.fragments.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;

public class D_Main extends AppCompatActivity {

    //Fragments asociados a esta Activity
    private A_Main_Promedios mainaPromedios;
    private B_Main_Pesajes mainbPesajes;
    private C_Main_Ejercicios maincEjercicios;
    private D_Main_Backups maindBackups;

    public int idUs;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_main);

        Button btnPromedios = findViewById(R.id.btnPromedios);
        Button btnPesajes = findViewById(R.id.btnPesajes);
        Button btnEjercicios = findViewById(R.id.btnEjercicios);
        Button btnBackups = findViewById(R.id.btnBackups);

        //Recibimos Usuario por intent desde .A_Login
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);

        idUs = usuario.getIdUsuario();

        //Iniciamos los fragments
        mainaPromedios = new A_Main_Promedios();
        mainbPesajes = new B_Main_Pesajes();
        maincEjercicios = new C_Main_Ejercicios();
        maindBackups = new D_Main_Backups();

        //Fragment que se visualiza por defecto onCreate
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentosMAIN,
            mainaPromedios).commit();

    }

    //Método onClick para cambiar entre fragments
    @SuppressLint("NonConstantResourceId")
    public void onClick(@org.jetbrains.annotations.NotNull View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(view.getId()) {

            case R.id.btnPromedios :

                transaction.replace(R.id.contenedorFragmentosMAIN, mainaPromedios);
                break;

            case R.id.btnPesajes :

                transaction.replace(R.id.contenedorFragmentosMAIN, mainbPesajes);
                break;

            case R.id.btnEjercicios :

                transaction.replace(R.id.contenedorFragmentosMAIN, maincEjercicios);
                break;

            case R.id.btnBackups :

                transaction.replace(R.id.contenedorFragmentosMAIN, maindBackups);
                break;

        }

        transaction.commit();

    }

    //Método para crear el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Cambiamos el nombre del item al nombre de Usuario Logeado
        menu.findItem(R.id.perfil).setTitle(usuario.getUsuario());
        //Creamos separación entre los grupos del menu
        MenuCompat.setGroupDividerEnabled(menu, true);

        return true;

    }

    //Método para las acciones que hacen los items del Menu
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.perfil :

                Intent intent = new Intent(getApplicationContext(), G_Perfil.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);

                return true;

            case R.id.acercad :

                Intent intentA = new Intent(getApplicationContext(), F_Acercade.class);
                startActivity(intentA);

                return true;

            case R.id.ajustes :

                return true;

            case R.id.ayuda :

                Intent intentH = new Intent(getApplicationContext(), H_Ayuda.class);
                startActivity(intentH);

                return true;

            case R.id.cerrarSesion :

                finish();

                return true;

            default :

                return super.onOptionsItemSelected(item);

        }

    }

}