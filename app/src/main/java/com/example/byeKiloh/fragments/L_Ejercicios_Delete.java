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

import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.Ejercicio;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.*;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link L_Ejercicios_Delete#newInstance} factory method to
 * create an instance of this fragment.
 */

public class L_Ejercicios_Delete extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaED;

    private Button btnBorrarEjercicioD;
    private EditText etFechaD, etDistanciaD, etTiempoD, etInclinacionD;
    private Spinner spinEjerciciosD;

    String idD;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud dejerCrud;

    BaseDatos basedatos;
    Ejercicio ejercicio;
    Mensaje mensaje;
    VaciarEditText vaciarEditText;

    public L_Ejercicios_Delete() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment L_Ejercicios_Delete.
     */

    // TODO: Rename and change types and number of parameters
    public static L_Ejercicios_Delete newInstance(String param1, String param2) {
        L_Ejercicios_Delete fragment = new L_Ejercicios_Delete();
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

        vistaED = inflater.inflate(R.layout.fragment_l_ejercicios_delete, container, false);

        btnBorrarEjercicioD = vistaED.findViewById(R.id.btnBorrarEjercicioD);

        etFechaD = vistaED.findViewById(R.id.etFechaD);
        etDistanciaD = vistaED.findViewById(R.id.etDistanciaD);
        etTiempoD = vistaED.findViewById(R.id.etTiempoD);
        etInclinacionD = vistaED.findViewById(R.id.etInclinacionD);

        spinEjerciciosD = vistaED.findViewById(R.id.spinEjerciciosD);

        //Instanciamos la activity que contiene la variable
        dejerCrud = (E_Crud) getActivity();
        idD = dejerCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinEjerciciosD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                ejercicio = new Ejercicio((Ejercicio) spinEjerciciosD.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFechaD.setText(ejercicio.getFecha());
                etDistanciaD.setText(String.valueOf(ejercicio.getDistancia()));
                etTiempoD.setText(String.valueOf(ejercicio.getTiempo()));
                etInclinacionD.setText(ejercicio.getInclinacion());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnBorrarEjercicioD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            borrarEjercicio();
            vaciarEditText = new VaciarEditText(etFechaD, etDistanciaD, etTiempoD, etInclinacionD);
            actualizarSpinner();
            }

        });

        return vistaED;

    }

    //Método que borra el ejercicio seleccionado del Spinner
    public void borrarEjercicio() {
        //Creamos objeto Ejercicio con el item seleccionado del Spinner
        ejercicio = new Ejercicio((Ejercicio) spinEjerciciosD.getSelectedItem());
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //Sentencia que borra el ejercicio indicado
        sqlite.delete("Ejercicios", "Ejercicios.idEjercicio = '" +
            ejercicio.getIdEjercicio() + "'", null);
        //Mensaje de éxito al borrar
        mensaje = new Mensaje(getActivity(), "El Ejercicio ha sido borrado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Ejercicio> ejerciciosAD = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Ejercicios WHERE " +
                "Ejercicios.idUsuario LIKE '" + idD + "'", null);

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
                    ejercicio.setIdUsuario(Integer.parseInt(idD));

                    ejerciciosAD.add(ejercicio);

                } while (cursor.moveToNext());
            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinEjerciciosD.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_item_c,
                ejerciciosAD));
        //Seleccionamos el último registro del Array
        spinEjerciciosD.setSelection(ejerciciosAD.size()-1);

    }

}