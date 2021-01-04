package com.example.byeKiloh.fragments;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.Ejercicio;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.*;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link K_Ejercicios_Update#newInstance} factory method to
 * create an instance of this fragment.
 */

public class K_Ejercicios_Update extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaEU;

    private Button btnActualizarEjercicioU;
    private EditText etFechaU, etDistanciaU, etTiempoU, etInclinacionU;
    private Spinner spinEjerciciosU;

    String idK;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud uejerCrud;

    BaseDatos basedatos;
    Ejercicio ejercicio;
    Mensaje mensaje;
    VaciarEditText vaciarEditText;

    public K_Ejercicios_Update() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment K_Ejercicios_Update.
     */

    // TODO: Rename and change types and number of parameters
    public static K_Ejercicios_Update newInstance(String param1, String param2) {
        K_Ejercicios_Update fragment = new K_Ejercicios_Update();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        vistaEU = inflater.inflate(R.layout.fragment_k_ejercicios_update, container, false);

        btnActualizarEjercicioU = vistaEU.findViewById(R.id.btnActualizarEjercicioU);

        etFechaU = vistaEU.findViewById(R.id.etFechaU);
        etDistanciaU = vistaEU.findViewById(R.id.etDistanciaU);
        etTiempoU = vistaEU.findViewById(R.id.etTiempoU);
        etInclinacionU = vistaEU.findViewById(R.id.etInclinacionU);

        spinEjerciciosU = vistaEU.findViewById(R.id.spinEjerciciosU);

        //Instanciamos la activity que contiene la variable
        uejerCrud = (E_Crud) getActivity();
        idK = uejerCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinEjerciciosU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                ejercicio = new Ejercicio((Ejercicio) spinEjerciciosU.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFechaU.setText(ejercicio.getFecha());
                etDistanciaU.setText(String.valueOf(ejercicio.getDistancia()));
                etTiempoU.setText(String.valueOf(ejercicio.getTiempo()));
                etInclinacionU.setText(ejercicio.getInclinacion());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnActualizarEjercicioU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            actualizarEjercicio();
            vaciarEditText = new VaciarEditText(etFechaU, etDistanciaU, etTiempoU, etInclinacionU);
            actualizarSpinner();
            }

        });

        return vistaEU;

    }

    //Método que actualiza un ejercicio
    public void actualizarEjercicio() {
        //Creamos objeto Ejercicio con el item seleccionado del Spinner
        ejercicio = new Ejercicio((Ejercicio) spinEjerciciosU.getSelectedItem());
        //Actualziamos el Ejercicio con los datos de los EditText si no están vacios
        if(!etFechaU.getText().toString().equals("")) {
            ejercicio.setFecha(etFechaU.getText().toString());
        }
        if(!etDistanciaU.getText().toString().equals("")) {
            ejercicio.setDistancia(Integer.parseInt(etDistanciaU.getText().toString()));
        }
        if(!etTiempoU.getText().toString().equals("")) {
            ejercicio.setTiempo(Integer.parseInt(etTiempoU.getText().toString()));
        }
        if(!etInclinacionU.getText().toString().equals("")) {
            ejercicio.setInclinacion(etInclinacionU.getText().toString());
        }
        ejercicio.setVelocidad();
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //EstructuraUsuario de insercción de datos
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME_FECHA, ejercicio.getFecha());
        content.put(COLUMN_NAME_DISTANCIA, String.valueOf(ejercicio.getDistancia()));
        content.put(COLUMN_NAME_TIEMPO, String.valueOf(ejercicio.getTiempo()));
        content.put(COLUMN_NAME_VELOCIDAD, ejercicio.getVelocidad());
        content.put(COLUMN_NAME_INCLINACION, ejercicio.getInclinacion());
        //Actualizamos el registro en la base de datos
        sqlite.update("Ejercicios", content, "Ejercicios.idEjercicio = '" +
            ejercicio.getIdEjercicio() + "'", null);
        //Mensaje de éxito al actualizar
        mensaje = new Mensaje(getActivity(), "El Ejercicio ha sido actualizado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Ejercicio> ejerciciosAU = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Ejercicios WHERE " +
            "Ejercicios.idUsuario LIKE '" + idK + "'", null);

        //Comprobamos si el cursor no es null
        if(cursor != null) {

            if(cursor.moveToFirst()) {
                //Bucle do-while, crea un ejercicio y lo incluye en el Array mientras haya registros
                do {
                    ejercicio = new Ejercicio();
                    ejercicio.setIdEjercicio(cursor.getInt(cursor.getColumnIndex(_IDEJERCICIO)));
                    ejercicio.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FECHA)));
                    ejercicio.setDistancia(Integer.parseInt(cursor.getString(cursor.getColumnIndex
                        (COLUMN_NAME_DISTANCIA))));
                    ejercicio.setTiempo(Integer.parseInt(cursor.getString(cursor.getColumnIndex
                        (COLUMN_NAME_TIEMPO))));
                    ejercicio.setVelocidad();
                    ejercicio.setInclinacion(cursor.getString(cursor.getColumnIndex
                        (COLUMN_NAME_INCLINACION)));
                    ejercicio.setIdUsuario(Integer.parseInt(idK));

                    ejerciciosAU.add(ejercicio);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinEjerciciosU.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
            ejerciciosAU));
        //Seleccionamos el último registro del Array
        spinEjerciciosU.setSelection(ejerciciosAU.size()-1);

    }

}