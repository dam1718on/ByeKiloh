package com.example.byeKiloh.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.E_Crud;
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.datapersistence.Tablas;
import com.example.byeKiloh.objects.Bascula;
import com.example.byeKiloh.objects.Registro;
import com.example.byeKiloh.utils.Mensaje;
import com.example.byeKiloh.utils.VaciarEditText;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraBascula.COLUMN_NAME_ALTURAUSUARIO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraBascula.COLUMN_NAME_LUGARBASCULA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraBascula.COLUMN_NAME_PESOUSUARIO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraRegistro.COLUMN_NAME_FECHAREGISTRO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraRegistro._IDUSUARIO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link E_Bascula_Crear#newInstance} factory method to
 * create an instance of this fragment.
 */

public class E_Bascula_Crear extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPC;

    private EditText etPesoUsuario, etAlturaUsuario, etLugarBascula;
    private Button btnAnadirBascula;

    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    String idE;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud cpesaCrud;

    Bascula esDE;
    BaseDatos basedatos;
    Mensaje mensaje;
    Registro registro;
    VaciarEditText vaciarEditText;

    public E_Bascula_Crear() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment E_Bascula_Crear.
     */

    // TODO: Rename and change types and number of parameters
    public static E_Bascula_Crear newInstance(String param1, String param2) {

        E_Bascula_Crear fragment = new E_Bascula_Crear();
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

        vistaPC = inflater.inflate(R.layout.fragment_e_bascula_crear, container, false);

        etPesoUsuario = vistaPC.findViewById(R.id.etPesoUsuario);
        etAlturaUsuario = vistaPC.findViewById(R.id.etAlturaUsuario);
        etLugarBascula = vistaPC.findViewById(R.id.etLugarBascula);

        btnAnadirBascula = vistaPC.findViewById(R.id.btnAnadirBascula);

        //Instanciamos la activity que contiene la variable
        cpesaCrud = (E_Crud) getActivity();
        idE = cpesaCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        btnAnadirBascula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Primer if comprueba que no hay EditText vacíos
            if(etPesoUsuario.getText().toString().equals("") ||
                etAlturaUsuario.getText().toString().equals("") ||
                etLugarBascula.getText().toString().equals("")) {
                mensaje = new Mensaje(getActivity(), "Revise los datos " +
                        "introducidos\ntodos los campos son obligatorios");
            }
            else {
                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etPesoUsuario, 3, "Peso usuario");
                numMinL(etAlturaUsuario, 3, "Altura usuario");
                numMinL(etLugarBascula, 1, "Lugar bascula");

                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if(countError == 1) {
                    registro = new Registro();
                    //Se crea objeto Bascula con parámetros
                    esDE = new Bascula(Float.parseFloat(etPesoUsuario.getText().toString()),
                            Float.parseFloat(etAlturaUsuario.getText().toString()),
                            etLugarBascula.getText().toString());
                    registro.setEsDE(esDE);
                    registro.setFechaRegistro();
                    registro.getHechoPOR().setIdUsuario(Integer.parseInt(idE));
                    //Se otorgan permisos de escritura
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    //EstructuraEjercicio de insercción de datos
                    ContentValues content = new ContentValues();
                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                    content.put(COLUMN_NAME_PESOUSUARIO, String.valueOf(esDE.getPesoUsuario()));
                    content.put(COLUMN_NAME_ALTURAUSUARIO, String.valueOf(esDE.getAlturaUsuario()));
                    content.put(COLUMN_NAME_LUGARBASCULA, esDE.getLugarBascula());
                    sqlite.insert("Basculas", null, content);

                    //Columnas que recogerá los datos de la consulta
                    String[] columnas = {Tablas.EstructuraBascula._IDBASCULA,
                        COLUMN_NAME_PESOUSUARIO, COLUMN_NAME_ALTURAUSUARIO, COLUMN_NAME_LUGARBASCULA};
                    Cursor cursor = sqlite.query("Basculas", columnas,null,
                            null, null, null, null);

                    if (cursor.getCount() != 0) {

                        cursor.moveToLast();
                        registro.getEsDE().setIdBascula(cursor.getInt(cursor.getColumnIndex
                                (Tablas.EstructuraBascula._IDBASCULA)));

                        ContentValues content2 = new ContentValues();

                        content2.put(COLUMN_NAME_FECHAREGISTRO, String.valueOf(registro.getFechaRegistro()));
                        content2.put(_IDUSUARIO, registro.getHechoPOR().getIdUsuario());
                        content2.put(Tablas.EstructuraRegistro._IDBASCULA, registro.getEsDE().getIdBascula());

                        sqlite.insert("Registros", null, content2);

                    }

                    //Mensaje de éxito al añadir
                    mensaje = new Mensaje(getActivity(), "La Báscula ha sido almacenada");
                    //Limpiamos los EditText
                    vaciarEditText = new VaciarEditText(etPesoUsuario, etAlturaUsuario,
                            etLugarBascula);

                    //Cerramos el cursor
                    cursor.close();
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