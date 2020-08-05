package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvEjercicio, tvEjercicioGuardado;

    private EditText etTiempo, etDistancia;

    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEjercicio=findViewById(R.id.tvEjercicio);
        tvEjercicioGuardado=findViewById(R.id.tvEjercicioGuardado);
        etTiempo=findViewById(R.id.etTiempo);
        etDistancia=findViewById(R.id.etDistancia);
        btnGuardar=findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ejercicio ejercicio = new Ejercicio(Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()));

                tvEjercicio.setText(ejercicio.toString());
                tvEjercicioGuardado.setText(ejercicio.toString());

                mensaje(ejercicio.getKmh());
            }
        });
    }

    public void calcular(View view){

        int distancia = Integer.parseInt(etDistancia.getText().toString());
        int tiempo = Integer.parseInt(etTiempo.getText().toString());
        float ms = (float) distancia/(tiempo*60);
        float kmh = ms*36/10;

        tvEjercicio.setText("Has recorrido "+distancia+" metros en "+tiempo+" minutos haciendo una velocidad" +
                " media de "+String.format("%.2f", ms)+" m/s ó "+String.format("%.2f", kmh)+" km/h.");

        mensaje(kmh);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void settings(View view){
        Toast toast= Toast.makeText(getApplicationContext(),
                "Settings!!!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }

    /*public void ver(View view){
        frase.setContenido(etContenido.getText().toString());
        frase.setAutor(etAutor.getText().toString());

        if (frase.getContenido().equals("")){
            frase.setContenido("Vas con lo justo socio");
            frase.setAutor("El Developer");
        }
        if (frase.getAutor().equals("")){
            frase.setAutor("Anónimo");
        }
        tvFrase.setText(frase.toString());

        Toast toast= Toast.makeText(getApplicationContext(),
                "Añadido correctamente", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }*/

    public void mensaje(Float kmh){
        if (kmh <= 3) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tienes que andar más rapido...", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast.show();
        } else if (kmh > 3 && kmh <= 4) {
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Un poquito mas rapido", Toast.LENGTH_SHORT);
            toast2.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast2.show();
        } else if (kmh > 4) {
            Toast toast3 = Toast.makeText(getApplicationContext(),
                    "Estas progresando, good job!!", Toast.LENGTH_SHORT);
            toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast3.show();
        }
    }

}