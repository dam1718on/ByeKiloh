package com.example.byeKiloh.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.*;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link J_Ejercicios_Read#newInstance} factory method to
 * create an instance of this fragment.
 */

public class J_Ejercicios_Read extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaER;

    private EditText etFechaR, etDistanciaR, etTiempoR, etInclinacionR;
    private Spinner spinEjerciciosR;

    String idJ;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud rejerCrud;

    BaseDatos basedatos;
    Ejercicio ejercicio;

    public J_Ejercicios_Read() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment J_Ejercicios_Read.
     */

    // TODO: Rename and change types and number of parameters
    public static J_Ejercicios_Read newInstance(String param1, String param2) {
        J_Ejercicios_Read fragment = new J_Ejercicios_Read();
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

        vistaER = inflater.inflate(R.layout.fragment_j_ejercicios_read, container, false);

        etFechaR = vistaER.findViewById(R.id.etFechaR);
        etDistanciaR = vistaER.findViewById(R.id.etDistanciaR);
        etTiempoR = vistaER.findViewById(R.id.etTiempoR);
        etInclinacionR = vistaER.findViewById(R.id.etInclinacionR);

        spinEjerciciosR = vistaER.findViewById(R.id.spinEjerciciosR);

        //Instanciamos la activity que contiene la variable
        rejerCrud = (E_Crud) getActivity();
        idJ = rejerCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinEjerciciosR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Creamos objeto Ejercicio con el item seleccionado del Spinner
            ejercicio = new Ejercicio((Ejercicio) spinEjerciciosR.getSelectedItem());
            //Rellenamos los EditText con los datos del Ejercicio
            etFechaR.setText(ejercicio.getFecha());
            etDistanciaR.setText(String.valueOf(ejercicio.getDistancia()));
            etTiempoR.setText(String.valueOf(ejercicio.getTiempo()));
            etInclinacionR.setText(ejercicio.getInclinacion());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        return vistaER;

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Ejercicio> ejerciciosAR = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Ejercicios WHERE " +
            "Ejercicios.idUsuario LIKE '" + idJ + "'", null);

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
                    ejercicio.setIdUsuario(Integer.parseInt(idJ));

                    ejerciciosAR.add(ejercicio);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinEjerciciosR.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
            ejerciciosAR));
        //Seleccionamos el último registro del Array
        spinEjerciciosR.setSelection(ejerciciosAR.size()-1);

    }

}