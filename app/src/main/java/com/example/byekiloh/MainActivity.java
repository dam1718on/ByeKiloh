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

    private TextView tvFrase, tvFraseGuardada;
    private EditText etContenido, etAutor;
    private Button btnGuardar;

    private SettingPreferences settingPreferences;

    private Frase frase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFrase=findViewById(R.id.tvFrase);
        tvFraseGuardada=findViewById(R.id.tvFraseGuardada);
        etContenido=findViewById(R.id.etContenido);
        etAutor=findViewById(R.id.etAutor);
        btnGuardar=findViewById(R.id.btnGuardar);

        settingPreferences = new SettingPreferences(this.getApplicationContext());

        frase = settingPreferences.getFrase();

        etContenido.setText(frase.getContenido());
        etAutor.setText(frase.getAutor());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFrase();
            }
        });
    }

    private void guardarFrase() {
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

    /*@Override
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

    public void guardar(View view){
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
    }

    public void ver(View view){
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
    }

}