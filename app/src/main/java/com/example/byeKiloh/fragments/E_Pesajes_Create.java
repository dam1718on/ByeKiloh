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
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.Pesaje;
import com.example.byeKiloh.R;
import com.example.byeKiloh.utils.*;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraPesaje.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link E_Pesajes_Create#newInstance} factory method to
 * create an instance of this fragment.
 */

public class E_Pesajes_Create extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPC;

    private EditText etFechaPC, etPesoC, etAlturaC, etLugarC;
    private Button btnAnadirPesajeC;

    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    String idE;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud cpesaCrud;

    BaseDatos basedatos;
    Mensaje mensaje;
    Pesaje pesaje;
    VaciarEditText vaciarEditText;

    public E_Pesajes_Create() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment E_Pesajes_Create.
     */

    // TODO: Rename and change types and number of parameters
    public static E_Pesajes_Create newInstance(String param1, String param2) {
        E_Pesajes_Create fragment = new E_Pesajes_Create();
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

        vistaPC = inflater.inflate(R.layout.fragment_e_pesajes_create, container, false);

        etFechaPC = vistaPC.findViewById(R.id.etFechaPC);
        etPesoC = vistaPC.findViewById(R.id.etPesoC);
        etAlturaC = vistaPC.findViewById(R.id.etAlturaC);
        etLugarC = vistaPC.findViewById(R.id.etLugarC);

        btnAnadirPesajeC = vistaPC.findViewById(R.id.btnAnadirPesajeC);

        //Instanciamos la activity que contiene la variable
        cpesaCrud = (E_Crud) getActivity();
        idE = cpesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        btnAnadirPesajeC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Primer if comprueba que no hay EditText vacíos
            if(etFechaPC.getText().toString().equals("") ||
                etPesoC.getText().toString().equals("") ||
                etAlturaC.getText().toString().equals("") ||
                etLugarC.getText().toString().equals("")) {
                mensaje = new Mensaje(getActivity(), "Revise los datos " +
                        "introducidos\ntodos los campos son obligatorios");
            } else {
                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etFechaPC, 6, "Fecha");
                numMinL(etPesoC, 4, "Peso");
                numMinL(etAlturaC, 4, "Altura");
                numMinL(etLugarC, 1, "Lugar Pesaje");

                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if(countError == 1) {
                    //Se crea objeto Pesaje con parámetros
                    pesaje = new Pesaje(etFechaPC.getText().toString(),
                        etPesoC.getText().toString(), etAlturaC.getText().toString(),
                        etLugarC.getText().toString(), Integer.parseInt(idE));
                    //Se otorgan permisos de escritura
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    //EstructuraEjercicio de insercción de datos
                    ContentValues content = new ContentValues();
                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                    content.put(_IDUSUARIO, pesaje.getIdUsuario());
                    content.put(COLUMN_NAME_FECHA, pesaje.getFecha());
                    content.put(COLUMN_NAME_PESO, pesaje.getPeso());
                    content.put(COLUMN_NAME_ALTURA, pesaje.getAltura());
                    content.put(COLUMN_NAME_LUGAR, pesaje.getLugar());
                    content.put(COLUMN_NAME_IMC, pesaje.getIMC());
                    content.put(COLUMN_NAME_CLASIFICACION, pesaje.getClasificacion());
                    sqlite.insert(TABLE_NAME, null, content);
                    //Mensaje de éxito al añadir
                    mensaje = new Mensaje(getActivity(), "El Pesaje ha sido almacenado");
                    //Limpiamos los EditText
                    vaciarEditText = new VaciarEditText(etFechaPC, etPesoC, etAlturaC, etLugarC);
                    //Se cierra la conexión abierta a la Base de Datos
                    sqlite.close();

                }
            }
            }

        });

        return vistaPC;

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