package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvEjercicio, tvEjercicioGuardado;
    private EditText etTiempo, etDistancia;
    private RadioButton radMS, radKMH;
    //no se usa boolean porque necesito 3 estados (no checked)
    private int velocidad=0;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEjercicio=findViewById(R.id.tvEjercicio);
        tvEjercicioGuardado=findViewById(R.id.tvEjercicioGuardado);
        etTiempo=findViewById(R.id.etTiempo);
        etDistancia=findViewById(R.id.etDistancia);
        radMS=findViewById(R.id.radMS);
        radKMH=findViewById(R.id.radKMH);
        btnGuardar=findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ejercicio ejercicio = new Ejercicio(Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()));

                float ms = (float) ejercicio.getDistancia()/(ejercicio.getTiempo()*60);
                float kmh = ms*36/10;

                if (velocidad==0){
                    ms=0;
                    kmh=0;
                } else if (velocidad==1){
                    tvEjercicio.setText("Has recorrido "+ejercicio.getDistancia()+" metros en "+ejercicio.getTiempo()+
                            " minutos haciendo una velocidad"+" media de "+String.format("%.2f", ms)+" m/s");
                    tvEjercicioGuardado.setText("Has recorrido "+ejercicio.getDistancia()+" metros en "+ejercicio.getTiempo()+
                            " minutos haciendo una velocidad"+" media de "+String.format("%.2f", ms)+" m/s");
                }else if (velocidad==2){
                    tvEjercicio.setText("Has recorrido "+ejercicio.getDistancia()+" metros en "+ejercicio.getTiempo()+
                            " minutos haciendo una velocidad"+" media de "+String.format("%.2f", kmh)+" km/h");
                    tvEjercicioGuardado.setText("Has recorrido "+ejercicio.getDistancia()+" metros en "+ejercicio.getTiempo()+
                            " minutos haciendo una velocidad"+" media de "+String.format("%.2f", kmh)+" km/h");
                }
                mensaje(kmh);
            }
        });
    }


    //Metodo asociado a boton con su mismo nombre para calcular los datos y visualizarlos
    public void calcular(View view){

        int distancia = Integer.parseInt(etDistancia.getText().toString());
        int tiempo = Integer.parseInt(etTiempo.getText().toString());
        float ms = (float) distancia/(tiempo*60);
        float kmh = ms*36/10;

        if (velocidad==0){
            ms=0;
            kmh=0;
        } else if (velocidad==1){
            tvEjercicio.setText("Has recorrido "+distancia+" metros en "+tiempo+" minutos haciendo una velocidad" +
                    " media de "+String.format("%.2f", ms)+" m/s");
        }else if (velocidad==2){
            tvEjercicio.setText("Has recorrido "+distancia+" metros en "+tiempo+" minutos haciendo una velocidad" +
                    " media de "+String.format("%.2f", kmh)+" km/h");
        }
        mensaje(kmh);
    }

    //Sin implementar aun
    public void settings(View view){

        Toast toast= Toast.makeText(getApplicationContext(),
                "Settings!!!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
        toast.show();
    }

    //Método para visualizar mensajes segun velocidad
    public void mensaje(Float kmh){

        if (kmh == 0){
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Selecciona velocidad!!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 14);
            toast.show();
        } else if (kmh <= 3) {
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

    //Método del RadioGrouponClick
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radMS:
                if (checked)
                velocidad=1;
                break;
            case R.id.radKMH:
                if (checked)
                velocidad=2;
                break;
        }
    }

}