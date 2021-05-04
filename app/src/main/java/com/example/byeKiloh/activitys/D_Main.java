package com.example.byeKiloh.activitys;

import android.annotation.SuppressLint;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuCompat;

import androidx.viewpager.widget.ViewPager;

import com.example.byeKiloh.R;
import com.example.byeKiloh.objects.Usuario;
import com.example.byeKiloh.utils.PagerController;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class D_Main extends AppCompatActivity {

    //Atributos del TabLayout
    private TabLayout tlMain;
    private ViewPager vpMain;
    private TabItem tiEstadisticas, tiMediciones, tiLogros, tiBackups;
    PagerController pagerController;

    Usuario usuario;
    public int idUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_main);

        tlMain = findViewById(R.id.tlMain);
        vpMain = findViewById(R.id.vpMain);
        tiEstadisticas = findViewById(R.id.tiEstadisticas);
        tiMediciones = findViewById(R.id.tiMediciones);
        tiLogros = findViewById(R.id.tiLogros);
        tiBackups = findViewById(R.id.tiBackups);

        //Recibimos Usuario por intent desde .A_Login
        final Usuario usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario_copia del Usuario recibido
        usuario = new Usuario(usuarioLogin);

        idUs = usuario.getIdUsuario();

        pagerController = new PagerController(getSupportFragmentManager(), tlMain.getTabCount());
        vpMain.setAdapter(pagerController);
        tlMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                vpMain.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0) {
                    pagerController.notifyDataSetChanged();
                }

                if(tab.getPosition()==1) {
                    pagerController.notifyDataSetChanged();
                }

                if(tab.getPosition()==2) {
                    pagerController.notifyDataSetChanged();
                }

                if(tab.getPosition()==3) {
                    pagerController.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMain));

    }
    //Método para crear el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Cambiamos el nombre del item al nombre de Usuario Logeado
        menu.findItem(R.id.perfil).setTitle(usuario.getAliasUsuario());
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