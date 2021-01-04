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
import com.example.byeKiloh.objects.Pesaje;
import com.example.byeKiloh.R;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Pesajes_Read#newInstance} factory method to
 * create an instance of this fragment.
 */

public class F_Pesajes_Read extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPR;

    private EditText etFechaPR, etPesoR, etAlturaR, etLugarR;
    private Spinner spinPesajesR;

    String idF;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud rpesaCrud;

    BaseDatos basedatos;
    Pesaje pesaje;

    public F_Pesajes_Read() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Pesajes_Read.
     */

    // TODO: Rename and change types and number of parameters
    public static F_Pesajes_Read newInstance(String param1, String param2) {
        F_Pesajes_Read fragment = new F_Pesajes_Read();
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

        vistaPR = inflater.inflate(R.layout.fragment_f_pesajes_read, container, false);

        etFechaPR = vistaPR.findViewById(R.id.etFechaPR);
        etPesoR = vistaPR.findViewById(R.id.etPesoR);
        etAlturaR = vistaPR.findViewById(R.id.etAlturaR);
        etLugarR = vistaPR.findViewById(R.id.etLugarR);

        spinPesajesR = vistaPR.findViewById(R.id.spinPesajesR);

        //Instanciamos la activity que contiene la variable
        rpesaCrud = (E_Crud) getActivity();
        idF = rpesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinPesajesR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                pesaje = new Pesaje((Pesaje) spinPesajesR.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFechaPR.setText(pesaje.getFecha());
                etPesoR.setText(String.valueOf(pesaje.getPeso()));
                etAlturaR.setText(String.valueOf(pesaje.getAltura()));
                etLugarR.setText(pesaje.getLugar());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        return vistaPR;

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Pesaje> pesajesAR = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Pesajes WHERE " +
                "Pesajes.idUsuario LIKE '" + idF + "'", null);

        //Comprobamos si el cursor no es null
        if(cursor != null) {

            if(cursor.moveToFirst()) {
                //Bucle do-while, crea un ejercicio y lo incluye en el Array mientras haya registros
                do {
                    pesaje = new Pesaje();
                    pesaje.setIdPesaje(cursor.getInt(cursor.getColumnIndex(_IDPESAJE)));
                    pesaje.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FECHA)));
                    pesaje.setPeso(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PESO)));
                    pesaje.setAltura(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALTURA)));
                    pesaje.setLugar(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LUGAR)));
                    pesaje.setIMC();
                    pesaje.setClasificacion();
                    pesaje.setIdUsuario(Integer.parseInt(idF));

                    pesajesAR.add(pesaje);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinPesajesR.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
                pesajesAR));
        //Seleccionamos el último registro del Array
        spinPesajesR.setSelection(pesajesAR.size()-1);

    }

}