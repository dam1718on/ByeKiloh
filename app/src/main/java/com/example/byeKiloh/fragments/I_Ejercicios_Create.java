package com.example.byeKiloh.fragments;

import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.*;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link I_Ejercicios_Create#newInstance} factory method to
 * create an instance of this fragment.
 */

public class I_Ejercicios_Create extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaEC;

    private EditText etFechaC, etDistanciaC, etTiempoC, etInclinacionC;
    private Button btnAnadirEjercicioC;

    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    String idI;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud fejerCrud;

    BaseDatos basedatos;
    Ejercicio ejercicio;
    Mensaje mensaje;
    VaciarEditText vaciarEditText;

    public I_Ejercicios_Create() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment I_Ejercicios_Create.
     */

    // TODO: Rename and change types and number of parameters
    public static I_Ejercicios_Create newInstance(String param1, String param2) {
        I_Ejercicios_Create fragment = new I_Ejercicios_Create();
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

        vistaEC = inflater.inflate(R.layout.fragment_i_ejercicios_create, container, false);

        etFechaC = vistaEC.findViewById(R.id.etFechaC);
        etDistanciaC = vistaEC.findViewById(R.id.etDistanciaC);
        etTiempoC = vistaEC.findViewById(R.id.etTiempoC);
        etInclinacionC = vistaEC.findViewById(R.id.etInclinacionC);

        btnAnadirEjercicioC = vistaEC.findViewById(R.id.btnAnadirEjercicioC);

        //Instanciamos la activity que contiene la variable
        fejerCrud = (E_Crud) getActivity();
        idI = fejerCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        btnAnadirEjercicioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Primer if comprueba que no hay EditText vacíos
            if(etFechaC.getText().toString().equals("") ||
                etDistanciaC.getText().toString().equals("") ||
                etTiempoC.getText().toString().equals("") ||
                etInclinacionC.getText().toString().equals("")) {
                mensaje = new Mensaje(getActivity(), "Revise los datos " +
                    "introducidos\ntodos los campos son obligatorios");
            } else {
                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etFechaC, 6, "Fecha");
                numMinL(etDistanciaC, 3, "Distancia");
                numMinL(etTiempoC, 2, "Tiempo");
                numMinL(etInclinacionC, 1, "Inclinacion");

                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if(countError == 1) {
                    //Se crea objeto Ejercicio con parámetros
                    ejercicio = new Ejercicio(etFechaC.getText().toString(),
                        Integer.parseInt(etDistanciaC.getText().toString()),
                        Integer.parseInt(etTiempoC.getText().toString()),
                        etInclinacionC.getText().toString(), Integer.parseInt(idI));
                    //Se otorgan permisos de escritura
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    //EstructuraEjercicio de insercción de datos
                    ContentValues content = new ContentValues();
                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                    content.put(_IDUSUARIO, ejercicio.getIdUsuario());
                    content.put(COLUMN_NAME_FECHA, ejercicio.getFecha());
                    content.put(COLUMN_NAME_DISTANCIA, String.valueOf(ejercicio.getDistancia()));
                    content.put(COLUMN_NAME_TIEMPO, String.valueOf(ejercicio.getTiempo()));
                    content.put(COLUMN_NAME_VELOCIDAD, ejercicio.getVelocidad());
                    content.put(COLUMN_NAME_INCLINACION, ejercicio.getInclinacion());
                    sqlite.insert(TABLE_NAME, null, content);
                    //Mensaje de éxito al añadir
                    mensaje = new Mensaje(getActivity(), "El Ejercicio ha sido almacenado");
                    //Limpiamos los EditText
                    vaciarEditText = new VaciarEditText(etFechaC, etDistanciaC, etTiempoC,
                        etInclinacionC);
                    //Se cierra la conexión abierta a la Base de Datos
                    sqlite.close();

                }
            }
        }

        });

        return vistaEC;

    }

    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo){
        //Contamos el tamaño del EditText introducido
        int num = et.length();
        //Comprobamos si el tamaño del EditText es inferior al minimo exigido
        if(num<minDig) {
            //Retorno de mensaje de error detallado
            mensaje = new Mensaje(getActivity(), "'" + campo + "' tiene " + num +
                " carácteres\ny el mínimo para ese campo son " + minDig);
            countError += 1;
        }

    }

}