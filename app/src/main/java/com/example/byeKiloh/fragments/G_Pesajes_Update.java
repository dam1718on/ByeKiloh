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
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.*;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link G_Pesajes_Update#newInstance} factory method to
 * create an instance of this fragment.
 */

public class G_Pesajes_Update extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPU;

    private Button btnActualizarPesajeU;
    private EditText etFechaPU, etPesoU, etAlturaU, etLugarR;
    private Spinner spinPesajesU;

    String idG;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud upesaCrud;

    BaseDatos basedatos;
    Mensaje mensaje;
    Pesaje pesaje;
    VaciarEditText vaciarEditText;

    public G_Pesajes_Update() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment G_Pesajes_Update.
     */

    // TODO: Rename and change types and number of parameters
    public static G_Pesajes_Update newInstance(String param1, String param2) {
        G_Pesajes_Update fragment = new G_Pesajes_Update();
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

        vistaPU = inflater.inflate(R.layout.fragment_g_pesajes_update, container, false);

        btnActualizarPesajeU = vistaPU.findViewById(R.id.btnActualizarPesajeU);

        etFechaPU = vistaPU.findViewById(R.id.etFechaPU);
        etPesoU = vistaPU.findViewById(R.id.etPesoU);
        etAlturaU = vistaPU.findViewById(R.id.etAlturaU);
        etLugarR = vistaPU.findViewById(R.id.etLugarR);

        spinPesajesU = vistaPU.findViewById(R.id.spinPesajesU);

        //Instanciamos la activity que contiene la variable
        upesaCrud = (E_Crud) getActivity();
        idG = upesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinPesajesU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                pesaje = new Pesaje((Pesaje) spinPesajesU.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFechaPU.setText(pesaje.getFecha());
                etPesoU.setText(pesaje.getPeso());
                etAlturaU.setText(pesaje.getAltura());
                etLugarR.setText(pesaje.getLugar());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnActualizarPesajeU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinPesajesU.getSelectedItem() != null) {
                    actualizarEjercicio();
                    vaciarEditText = new VaciarEditText(etFechaPU, etPesoU, etAlturaU, etLugarR);
                    actualizarSpinner();
                }
            }

        });

        return vistaPU;

    }

    //Método que actualiza un ejercicio
    public void actualizarEjercicio() {
        //Creamos objeto Pesaje con el item seleccionado del Spinner
        pesaje = new Pesaje((Pesaje) spinPesajesU.getSelectedItem());
        //Actualziamos el Ejercicio con los datos de los EditText si no están vacios
        if(!etFechaPU.getText().toString().equals("")) {
            pesaje.setFecha(etFechaPU.getText().toString());
        }
        if(!etPesoU.getText().toString().equals("")) {
            pesaje.setPeso(etPesoU.getText().toString());
        }
        if(!etAlturaU.getText().toString().equals("")) {
            pesaje.setAltura(etAlturaU.getText().toString());
        }
        if(!etLugarR.getText().toString().equals("")) {
            pesaje.setLugar(etLugarR.getText().toString());
        }
        pesaje.setIMC();
        pesaje.setClasificacion();
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //EstructuraUsuario de insercción de datos
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME_FECHA, pesaje.getFecha());
        content.put(COLUMN_NAME_PESO, pesaje.getPeso());
        content.put(COLUMN_NAME_ALTURA, pesaje.getAltura());
        content.put(COLUMN_NAME_LUGAR, pesaje.getLugar());
        content.put(COLUMN_NAME_IMC, pesaje.getIMC());
        content.put(COLUMN_NAME_CLASIFICACION, pesaje.getClasificacion());
        //Actualizamos el registro en la base de datos
        sqlite.update("Pesajes", content, "Pesajes.idPesaje = '" +
            pesaje.getIdPesaje() + "'", null);
        //Mensaje de éxito al actualizar
        mensaje = new Mensaje(getActivity(), "El Pesaje ha sido actualizado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Pesaje> pesajesAU = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Pesajes WHERE " +
            "Pesajes.idUsuario LIKE '" + idG + "'", null);

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
                    pesaje.setIdUsuario(Integer.parseInt(idG));

                    pesajesAU.add(pesaje);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinPesajesU.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
                pesajesAU));
        //Seleccionamos el último registro del Array
        spinPesajesU.setSelection(pesajesAU.size()-1);

    }

}