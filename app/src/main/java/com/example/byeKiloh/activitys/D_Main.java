package com.example.byeKiloh.activitys;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import com.example.byeKiloh.R;
import com.example.byeKiloh.fragments.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.utils.*;

public class D_Main extends AppCompatActivity {

    public int idUs;
    private Button btnPromedios, btnPesajes, btnEjercicios, btnBackups;
    private TextView tvUsuarioMain;
    private Menu menu;

    //Fragments asociados a esta Activity
    A_Main_Promedios mainaPromedios;
    B_Main_Pesajes mainbPesajes;
    C_Main_Ejercicios maincEjercicios;
    D_Main_Backups maindBackups;

    //int idUs;

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

        //Recibimos Usuario por intent desde .A_Login
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);
        //Imprimo la copia del usuario recibido
        tvUsuarioMain.setText(usuario.getUsuario());

        idUs = usuario.getIdUsuario();

        //menu=findViewById(((Menu) R.menu.main_menu));
        //menu.getItem(nav_home);

        //Iniciamos los fragments
        mainaPromedios = new A_Main_Promedios();
        mainbPesajes = new B_Main_Pesajes();
        maincEjercicios = new C_Main_Ejercicios();
        maindBackups = new D_Main_Backups();

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