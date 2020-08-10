package com.example.byekiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvEjercicio, tvEjercicioGuardado, tvHoraEjercicio;
    private EditText etDistancia, etTiempo;

    private RadioGroup radGroup;
    private RadioButton radMS, radKMH;
    //no se usa boolean porque necesito 3 estados (no checked)
    private int velocidad=0;

    private Button btnReset, btnCalcular, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEjercicio=findViewById(R.id.tvEjercicio);
        tvEjercicioGuardado=findViewById(R.id.tvEjercicioGuardado);
        tvHoraEjercicio=findViewById(R.id.tvHoraEjercicio);
        etDistancia=findViewById(R.id.etDistancia);
        etTiempo=findViewById(R.id.etTiempo);

        radGroup=findViewById(R.id.radGroup);
        radMS=findViewById(R.id.radMS);
        radKMH=findViewById(R.id.radKMH);

        btnReset=findViewById(R.id.btnReset);
        btnCalcular=findViewById(R.id.btnCalcular);
        btnGuardar=findViewById(R.id.btnGuardar);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDistancia.setText("");
                etTiempo.setText("");

                radGroup.clearCheck();
                radGroup.setOnCheckedChangeListener(null);

                tvEjercicio.setText("");
                tvEjercicioGuardado.setText("");
                tvHoraEjercicio.setText("");
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                velocidad(Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()), 0);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ejercicio ejercicio = new Ejercicio(Integer.parseInt(etDistancia.getText().toString()),
                        Integer.parseInt(etTiempo.getText().toString()));

                velocidad(ejercicio.getDistancia(), ejercicio.getTiempo(), 1);
                tvHoraEjercicio.setText(String.valueOf(ejercicio.getId()));
            }
        });
    }

    //Método para calcular velocidad, el atributo modo indica el boton usado 0=calcular 1=guardar
    private void velocidad(int distancia, int tiempo, int modo){
        float ms = (float) distancia / (tiempo * 60);
        float kmh = ms * 36 / 10;

        String mst = "Has recorrido "+distancia+" metros en "+tiempo+
                " minutos haciendo una velocidad"+" media de "+String.format("%.2f", ms)+" m/s";
        String kmht = "Has recorrido "+distancia+" metros en "+tiempo+
                " minutos haciendo una velocidad"+" media de "+String.format("%.2f", kmh)+" km/h";

        if (velocidad==0){
            ms = 0;
            kmh = 0;
        } else if (velocidad==1 && modo==0){
            tvEjercicio.setText(mst);
        } else if (velocidad==1 && modo==1){
            tvEjercicio.setText(mst);
            tvEjercicioGuardado.setText(mst);
        } else if (velocidad==2 && modo==0){
            tvEjercicio.setText(kmht);
        } else if (velocidad==2 && modo==1){
            tvEjercicio.setText(kmht);
            tvEjercicioGuardado.setText(kmht);
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

    //Método para visualizar mensajes según velocidad
    public void mensaje(Float kmh){
        if (kmh==0){
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

    //Método del RadioGroupOnClick
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radMS:
            if (checked)
            velocidad = 1;
            break;

            case R.id.radKMH:
            if (checked)
            velocidad = 2;
            break;
        }
    }

}