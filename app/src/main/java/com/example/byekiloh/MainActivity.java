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

    private TextView tvEjercicio, tvFraseGuardada;
    //private EditText etContenido, etAutor, etTiempo, etDistancia;
    private EditText etTiempo, etDistancia;

    private Button btnGuardar;

    //private SettingPreferences settingPreferences;
    //private Frase frase;

    //private int distancia, tiempo;
    //private double calculo, kmh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEjercicio=findViewById(R.id.tvEjercicio);
        tvFraseGuardada=findViewById(R.id.tvFraseGuardada);
        //etContenido=findViewById(R.id.etContenido);
        //etAutor=findViewById(R.id.etAutor);
        etTiempo=findViewById(R.id.etTiempo);
        etDistancia=findViewById(R.id.etDistancia);
        btnGuardar=findViewById(R.id.btnGuardar);

        //settingPreferences = new SettingPreferences(this.getApplicationContext());

        //frase = settingPreferences.getFrase();

        //etContenido.setText(frase.getContenido());
        //etAutor.setText(frase.getAutor());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardarFrase();
            }
        });
    }

    /*private void guardarFrase() {
        frase.setContenido(etContenido.getText().toString());
        frase.setAutor(etAutor.getText().toString());
        settingPreferences.save(frase);

        tvFrase.setText(settingPreferences.getFrase().toString());

        Toast toast= Toast.makeText(getApplicationContext(),
                "Añadido correctamente", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }

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

    /*public void guardar(View view){
        frase.setContenido(etContenido.getText().toString());
        frase.setAutor(etAutor.getText().toString());

        if (frase.getAutor().equals("")){
            frase.setAutor("Anónimo");
        }
        tvFrase.setText(frase.toString());

        Toast toast= Toast.makeText(getApplicationContext(),
                "Añadido correctamente", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }*/

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

    public void calcular(View view){

        int distancia = Integer.parseInt(etDistancia.getText().toString());
        int tiempo = Integer.parseInt(etTiempo.getText().toString());
        float ms = (float) distancia/(tiempo*60);
        float kmh = ms*36/10;

        tvEjercicio.setText(distancia+"-"+tiempo+"-"+String.format("%.2f", ms)+"-"+String.format("%.2f", kmh));

        mensaje(kmh);


        //distancia=0;
        //tiempo=0;
        //calculo=0;
        //kmh=0;

        //tvFraseGuardada.setText("");

        //float calculo = 1.17f;
        //float calculo, kmh2;

        //Ejercicio ejercicio = new Ejercicio(Integer.parseInt(etDistancia.getText().toString()),Integer.parseInt(etTiempo.getText().toString()));

        //ejercicio.setDistancia(Integer.parseInt(etDistancia.getText().toString()));
        //ejercicio.setTiempo(Integer.parseInt(etTiempo.getText().toString()));
        //int segundos = ejercicio.getTiempo()*60;
        //calculo = ejercicio.getDistancia()/segundos;
        //kmh2 = calculo * 36/10;

        //String ms = Float.toString(calculo);
        //tvFraseGuardada.setText(String.format("%.3f", ejercicio.getMs()));
        //tvFraseGuardada.setText(ms);
        //tvFraseGuardada.setText(ejercicio.getDistancia()+" - "+ejercicio.getTiempo()+" - "+segundos+" - "+String.valueOf(calculo)+" - "+String.valueOf(kmh2));
        //tvFraseGuardada.setText(String.format("%.2f", calculo));
                //+"   m/s\no\n"+String.format("%.2f", kmh2)+" km/h");
        //mensaje((float) (ejercicio.getMs()*3.6));
    }

    public void mensaje(Float kmh){
        if (kmh <= 2) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tienes que andar más rapido...", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 14);
            toast.show();
        } else if (kmh > 2 && kmh <= 4) {
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