package com.example.byeKiloh.fragments;

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

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.Bascula;
import com.example.byeKiloh.utils.*;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraBascula.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F_Bascula_Borrar#newInstance} factory method to
 * create an instance of this fragment.
 */

public class F_Bascula_Borrar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPD;

    private Button btnBorrarBasculaB;
    private EditText etPesoUsuarioB, etAlturaUsuarioB, etLugarBasculaB;
    private Spinner spinBasculasB;

    String idH;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud dpesaCrud;

    BaseDatos basedatos;
    Mensaje mensaje;
    Bascula esDE;
    VaciarEditText vaciarEditText;

    public F_Bascula_Borrar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_Bascula_Borrar.
     */

    // TODO: Rename and change types and number of parameters
    public static F_Bascula_Borrar newInstance(String param1, String param2) {

        F_Bascula_Borrar fragment = new F_Bascula_Borrar();
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

        vistaPD = inflater.inflate(R.layout.fragment_f_bascula_borrar, container, false);

        btnBorrarBasculaB = vistaPD.findViewById(R.id.btnBorrarBasculaB);

        etPesoUsuarioB = vistaPD.findViewById(R.id.etPesoUsuarioB);
        etAlturaUsuarioB = vistaPD.findViewById(R.id.etAlturaUsuarioB);
        etLugarBasculaB = vistaPD.findViewById(R.id.etLugarBasculaB);

        spinBasculasB = vistaPD.findViewById(R.id.spinBasculasB);

        //Instanciamos la activity que contiene la variable
        dpesaCrud = (E_Crud) getActivity();
        idH = dpesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinBasculasB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Creamos objeto Bascula con el item seleccionado del Spinner
                esDE = new Bascula((Bascula) spinBasculasB.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etPesoUsuarioB.setText(String.valueOf(esDE.getPesoUsuario()));
                etAlturaUsuarioB.setText(String.valueOf(esDE.getAlturaUsuario()));
                etLugarBasculaB.setText(esDE.getLugarBascula());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnBorrarBasculaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinBasculasB.getSelectedItem() != null) {

                    borrarBascula();
                    vaciarEditText = new VaciarEditText(etPesoUsuarioB, etAlturaUsuarioB,
                            etLugarBasculaB);
                    actualizarSpinner();

                }

            }

        });

        return vistaPD;

    }

    //Método que borra la bascula
    public void borrarBascula() {

        //Creamos objeto Bascula con el item seleccionado del Spinner
        esDE = new Bascula((Bascula) spinBasculasB.getSelectedItem());
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //Sentencia que borra la bascula indicada
        sqlite.delete("Basculas", "Basculas.idBascula = '" +
            esDE.getIdBascula() + "'", null);
        //Mensaje de éxito al borrar
        mensaje = new Mensaje(getActivity(), "La Bascula ha sido borrada");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con las basculas en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de basculas
        ArrayList<Bascula> basculasAD = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todas las basculas del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT DISTINCT Basculas.idBascula, Basculas.pesoUsuario, " +
            "Basculas.alturaUsuario, Basculas.lugarBascula FROM Registros, Basculas WHERE " +
            "Registros.idBascula=Basculas.idBascula and Registros.idUsuario LIKE '" + idH + "'",
                null);

        if(cursor.getCount() != 0) {

            cursor.moveToFirst();

            //Bucle do-while, crea una bascula y la incluye en el Array mientras haya registros
            do {
                esDE = new Bascula();
                esDE.setIdBascula(cursor.getInt(cursor.getColumnIndex(_IDBASCULA)));
                esDE.setPesoUsuario(cursor.getFloat(cursor.getColumnIndex(COLUMN_NAME_PESOUSUARIO)));
                esDE.setAlturaUsuario(cursor.getFloat(cursor.getColumnIndex(COLUMN_NAME_ALTURAUSUARIO)));
                esDE.setLugarBascula(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LUGARBASCULA)));

                basculasAD.add(esDE);

            } while (cursor.moveToNext());

        }

        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinBasculasB.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
            basculasAD));
        //Seleccionamos el último registro del Array
        spinBasculasB.setSelection(basculasAD.size()-1);

    }

}