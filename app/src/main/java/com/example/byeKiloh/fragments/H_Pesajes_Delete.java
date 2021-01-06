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
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.datapersistence.Tablas;
import com.example.byeKiloh.objects.Ejercicio;
import com.example.byeKiloh.objects.Pesaje;
import com.example.byeKiloh.utils.Mensaje;
import com.example.byeKiloh.utils.VaciarEditText;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_DISTANCIA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_FECHA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_INCLINACION;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_TIEMPO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio._IDEJERCICIO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.COLUMN_NAME_ALTURA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.COLUMN_NAME_LUGAR;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.COLUMN_NAME_PESO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje._IDPESAJE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link H_Pesajes_Delete#newInstance} factory method to
 * create an instance of this fragment.
 */
public class H_Pesajes_Delete extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPD;

    private Button btnBorrarPesajeD;
    private EditText etFechaPD, etPesoD, etAlturaD, etLugarD;
    private Spinner spinPesajesD;

    String idH;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud dpesaCrud;

    BaseDatos basedatos;
    Mensaje mensaje;
    Pesaje pesaje;
    VaciarEditText vaciarEditText;

    public H_Pesajes_Delete() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment H_Pesajes_Delete.
     */

    // TODO: Rename and change types and number of parameters
    public static H_Pesajes_Delete newInstance(String param1, String param2) {
        H_Pesajes_Delete fragment = new H_Pesajes_Delete();
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

        vistaPD = inflater.inflate(R.layout.fragment_h_pesajes_delete, container, false);

        btnBorrarPesajeD = vistaPD.findViewById(R.id.btnBorrarPesajeD);

        etFechaPD = vistaPD.findViewById(R.id.etFechaPD);
        etPesoD = vistaPD.findViewById(R.id.etPesoD);
        etAlturaD = vistaPD.findViewById(R.id.etAlturaD);
        etLugarD = vistaPD.findViewById(R.id.etLugarD);

        spinPesajesD = vistaPD.findViewById(R.id.spinPesajesD);

        //Instanciamos la activity que contiene la variable
        dpesaCrud = (E_Crud) getActivity();
        idH = dpesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinPesajesD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                pesaje = new Pesaje((Pesaje) spinPesajesD.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFechaPD.setText(pesaje.getFecha());
                etPesoD.setText(pesaje.getPeso());
                etAlturaD.setText(pesaje.getAltura());
                etLugarD.setText(pesaje.getLugar());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnBorrarPesajeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinPesajesD.getSelectedItem() != null) {
                    borrarEjercicio();
                    vaciarEditText = new VaciarEditText(etFechaPD, etPesoD, etAlturaD, etLugarD);
                    actualizarSpinner();
                }
            }

        });

        return vistaPD;

    }

    //Método que borra el ejercicio seleccionado del Spinner
    public void borrarEjercicio() {
        //Creamos objeto Ejercicio con el item seleccionado del Spinner
        pesaje = new Pesaje((Pesaje) spinPesajesD.getSelectedItem());
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //Sentencia que borra el ejercicio indicado
        sqlite.delete("Pesajes", "Pesajes.idPesaje = '" +
            pesaje.getIdPesaje() + "'", null);
        //Mensaje de éxito al borrar
        mensaje = new Mensaje(getActivity(), "El Pesaje ha sido borrado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Pesaje> pesajesAD = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Pesajes WHERE " +
            "Pesajes.idUsuario LIKE '" + idH + "'", null);

        //Comprobamos si el cursor no es null
        if(cursor != null) {

            if(cursor.moveToFirst()) {
                //Bucle do-while, crea un ejercicio y lo incluye en el Array mientras haya registros
                do {
                    pesaje = new Pesaje();
                    pesaje.setIdPesaje(cursor.getInt(cursor.getColumnIndex(_IDPESAJE)));
                    pesaje.setFecha(cursor.getString(cursor.getColumnIndex(Tablas.EstructuraPesaje.COLUMN_NAME_FECHA)));
                    pesaje.setPeso(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PESO)));
                    pesaje.setAltura(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALTURA)));
                    pesaje.setLugar(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LUGAR)));
                    pesaje.setIMC();
                    pesaje.setClasificacion();
                    pesaje.setIdUsuario(Integer.parseInt(idH));

                    pesajesAD.add(pesaje);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinPesajesD.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
            pesajesAD));
        //Seleccionamos el último registro del Array
        spinPesajesD.setSelection(pesajesAD.size()-1);

    }

}