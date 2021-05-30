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
import com.example.byeKiloh.objects.Ejercicio;
import com.example.byeKiloh.objects.Registro;
import com.example.byeKiloh.utils.Mensaje;
import com.example.byeKiloh.utils.VaciarEditText;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.*;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraRegistro.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link G_Ejercicio_Crear#newInstance} factory method to
 * create an instance of this fragment.
 */

public class G_Ejercicio_Crear extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaEC;

    private EditText etDistanciaRecorridaC, etTiempoEmpleadoC, etInclinacionTerrenoC;
    private Button btnAnadirEjercicioC;

    //Esta variable permite comprobar los digitos de varios EditText a la vez
    private int countError = 1;

    String idI;

    //Se carga la activity para poder extraer el idUsuario
    public E_Crud cejerCrud;

    BaseDatos basedatos;
    Ejercicio esHecho;
    Mensaje mensaje;
    Registro registro;
    VaciarEditText vaciarEditText;

    public G_Ejercicio_Crear() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment G_Ejercicio_Crear.
     */

    // TODO: Rename and change types and number of parameters
    public static G_Ejercicio_Crear newInstance(String param1, String param2) {

        G_Ejercicio_Crear fragment = new G_Ejercicio_Crear();
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

        vistaEC = inflater.inflate(R.layout.fragment_g_ejercicio_crear, container, false);

        etDistanciaRecorridaC = vistaEC.findViewById(R.id.etDistanciaRecorridaC);
        etTiempoEmpleadoC = vistaEC.findViewById(R.id.etTiempoEmpleadoC);
        etInclinacionTerrenoC = vistaEC.findViewById(R.id.etInclinacionTerrenoC);

        btnAnadirEjercicioC = vistaEC.findViewById(R.id.btnAnadirEjercicioC);

        //Instanciamos la activity que contiene la variable
        cejerCrud = (E_Crud) getActivity();
        idI = cejerCrud.fragEjerUsId;

        basedatos = new BaseDatos(getActivity());

        btnAnadirEjercicioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Primer if comprueba que no hay EditText vacíos
            if(etDistanciaRecorridaC.getText().toString().equals("") ||
                etTiempoEmpleadoC.getText().toString().equals("") ||
                etInclinacionTerrenoC.getText().toString().equals("")) {
                mensaje = new Mensaje(getActivity(), "Revise los datos " +
                    "introducidos\ntodos los campos son obligatorios");
            }
            else {
                countError=1;
                //Comprobamos número mínimo de carácteres en cada EditText
                numMinL(etDistanciaRecorridaC, 3, "Distancia recorrida");
                numMinL(etTiempoEmpleadoC, 2, "Tiempo empleado");
                numMinL(etInclinacionTerrenoC, 1, "Inclinacion terreno");

                //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                if(countError == 1) {

                    //Recogemos todos los Registros del Usuario Logeado
                    SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                    Cursor cursor = sqlite.rawQuery("SELECT * FROM Registros WHERE " +
                                "Registros.idUsuario LIKE '" + idI + "'",null);

                    //Si el cursor tiene registro/s
                    if(cursor.getCount() != 0) {
                        cursor.moveToLast();
                        //Se crea Registro
                        registro = new Registro();
                        registro.initFechaRegistro();
                        registro.getEsInsertado().setIdUsuario(Integer.parseInt(idI));
                        registro.getMide().setIdBascula(cursor.getInt(cursor.getColumnIndex(_IDBASCULA)));

                        //Se crea Ejercicio con parámetros
                        esHecho = new Ejercicio();
                        esHecho.setDistanciaRecorrida(Integer.parseInt(etDistanciaRecorridaC.getText().toString()));
                        esHecho.setTiempoEmpleado(Integer.parseInt(etTiempoEmpleadoC.getText().toString()));
                        esHecho.setInclinacionTerreno(Float.parseFloat(etInclinacionTerrenoC.getText().toString()));

                        //Query que comprueba si el Ejercicio ya estaba guardado con anterioridad
                        Cursor cursor2 = sqlite.rawQuery("SELECT * FROM Ejercicios WHERE " +
                        "Ejercicios.distanciaRecorrida='" + esHecho.getDistanciaRecorrida() + "' and " +
                        "Ejercicios.tiempoEmpleado='" + esHecho.getTiempoEmpleado() + "' and " +
                        "Ejercicios.inclinacionTerreno='" + esHecho.getInclinacionTerreno() + "'",null);

                        //Si el Ejericio ya existia
                        if(cursor2.getCount() != 0) {

                            cursor2.moveToFirst();
                            //Le pasamos la idEjercicio al Registro
                            esHecho.setIdEjercicio(cursor2.getInt(cursor2.getColumnIndex
                                    (Tablas.EstructuraEjercicio._IDEJERCICIO)));
                            //Insertamos el Registro
                            ContentValues content2 = new ContentValues();

                            content2.put(COLUMN_NAME_FECHAREGISTRO, String.valueOf(registro.getFechaRegistro()));
                            content2.put(_IDUSUARIO, registro.getEsInsertado().getIdUsuario());
                            content2.put(Tablas.EstructuraRegistro._IDEJERCICIO, esHecho.getIdEjercicio());
                            content2.put(_IDBASCULA, registro.getMide().getIdBascula());

                            sqlite.insert("Registros", null, content2);

                        }

                        //Si no existia, lo añadimos
                        else {

                            //EstructuraEjercicio de insercción de datos
                            ContentValues content = new ContentValues();
                        /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                        valor(valor introducido en el campo de texto)*/
                            content.put(COLUMN_NAME_DISTANCIARECORRIDA, String.valueOf(esHecho.getDistanciaRecorrida()));
                            content.put(COLUMN_NAME_TIEMPOEMPLEADO, String.valueOf(esHecho.getTiempoEmpleado()));
                            content.put(COLUMN_NAME_INCLINACIONTERRENO, String.valueOf(esHecho.getInclinacionTerreno()));
                            sqlite.insert("Ejercicios", null, content);

                            registro.setHace(esHecho);

                            //Recogemos todos los Ejercicios
                            String[] columnas = {Tablas.EstructuraEjercicio._IDEJERCICIO,
                                    COLUMN_NAME_DISTANCIARECORRIDA, COLUMN_NAME_TIEMPOEMPLEADO,
                                    COLUMN_NAME_INCLINACIONTERRENO};

                            Cursor cursor3 = sqlite.query("Ejercicios", columnas,null,
                                    null, null, null, null);

                            if(cursor3.getCount() != 0) {

                                cursor3.moveToLast();
                                esHecho.setIdEjercicio(cursor3.getInt(cursor3.getColumnIndex
                                        (Tablas.EstructuraEjercicio._IDEJERCICIO)));

                                //Insertamos el Registro con el idEjercicio del Ejercicio Añadido
                                ContentValues content3 = new ContentValues();

                                content3.put(COLUMN_NAME_FECHAREGISTRO, String.valueOf(registro.getFechaRegistro()));
                                content3.put(_IDUSUARIO, registro.getEsInsertado().getIdUsuario());
                                content3.put(Tablas.EstructuraRegistro._IDEJERCICIO, esHecho.getIdEjercicio());
                                content3.put(_IDBASCULA, registro.getMide().getIdBascula());

                                sqlite.insert("Registros", null, content3);

                            }
                            //cerramos cursor3
                            cursor3.close();

                        }
                        //cerramos cursor2
                        cursor2.close();

                        mensaje = new Mensaje(getActivity(), "Ejercicio añadido correctamente");

                    }
                    //Si no tiene registros
                    else {

                        mensaje = new Mensaje(getActivity(), "No ha añadido una Bascula,\n" +
                                " añada una bascula para continuar");

                    }
                    //cerramos cursor y conexion
                    sqlite.close();
                    cursor.close();

                    //Limpiamos los EditText
                    vaciarEditText = new VaciarEditText(etDistanciaRecorridaC, etTiempoEmpleadoC,
                            etInclinacionTerrenoC);

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