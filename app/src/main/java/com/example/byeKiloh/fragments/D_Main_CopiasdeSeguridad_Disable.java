package com.example.byeKiloh.fragments;

import android.Manifest;

import android.content.ContentValues;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.os.Environment;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.D_Main;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.utils.*;

import java.io.File;

import java.sql.Timestamp;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link D_Main_CopiasdeSeguridad_Disable#newInstance} factory method to
 * create an instance of this fragment.
 */

public class D_Main_CopiasdeSeguridad_Disable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaCOP;

    private ListView lvCopias;
    private TextView tvCopiasDeSeguridad;
    private Button btnCrearCopia, btnBorrarCopia, btnRestaurarCopia;

    private Adaptador adaptador;

    String idCOP;

    BaseDatos basedatos;
    CopiadeSeguridad copiadeSeguridad;
    CopiadeSeguridad copia;
    Cuenta cuenta;
    Mensaje mensaje;
    SQLiteDatabase sqlite;

    //Se carga la activity para poder extraer el idUsuario y propagarlo a los fragments
    public D_Main dmain;

    public D_Main_CopiasdeSeguridad_Disable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment D_Main_CopiasdeSeguridad_Disable.
     */

    // TODO: Rename and change types and number of parameters
    public static D_Main_CopiasdeSeguridad_Disable newInstance(String param1, String param2) {

        D_Main_CopiasdeSeguridad_Disable fragment = new D_Main_CopiasdeSeguridad_Disable();
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
        final Bundle savedInstanceState) {

        basedatos = new BaseDatos(getActivity());

        //Instanciamos la activity que contiene la variable
        dmain = (D_Main) getActivity();
        idCOP = String.valueOf(dmain.idUs);

        //Recogemos todos los datos de la cuentaRegistros del Usuario Logeado
        sqlite = basedatos.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Cuentas WHERE " +
                "Cuentas.idUsuario LIKE '" + idCOP + "'",null);


        //Si el usuario tiene cuenta/s
        if(cursor.getCount() != 0) {

            cursor.moveToLast();

            cuenta = new Cuenta();
            cuenta.setIdCuenta(cursor.getInt(cursor.getColumnIndex(Tablas.EstructuraCuenta._IDCUENTA)));
            cuenta.setCuentaValidada(cursor.getString(cursor.getColumnIndex
                    (Tablas.EstructuraCuenta.COLUMN_NAME_VALIDADO)));

            //Y tiene la cuenta validada
            if(cuenta.isCuentaValidada()) {

                //Gana acceso a COPIASDESEGURIDAD
                vistaCOP = inflater.inflate(R.layout.fragment_d_main_copiasdeseguridad_enable,
                        container, false);

                btnCrearCopia = vistaCOP.findViewById(R.id.btnCrearCopia);
                btnBorrarCopia = vistaCOP.findViewById(R.id.btnBorrarCopia);
                btnRestaurarCopia = vistaCOP.findViewById(R.id.btnRestaurarCopia);
                lvCopias = vistaCOP.findViewById(R.id.lvCopias);

                //Ubicación de la base de datos para crear y restaurar CopiadeSeguridad
                String pathOrigen = dmain.getDatabasePath(basedatos.getDatabaseName()).toString();

                refrescarListView();

                lvCopias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        view.setSelected(true);

                        copia = new CopiadeSeguridad((CopiadeSeguridad) lvCopias.getItemAtPosition(i));

                    }

                });

                btnCrearCopia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Comprobamos si tenemos permisos de escritura en sd-card
                        if(checkExternalStoragePermission(v)) {

                            sqlite = basedatos.getWritableDatabase();

                            ContentValues content = new ContentValues();

                            copiadeSeguridad = new CopiadeSeguridad();
                            copiadeSeguridad.initFechaSubida();
                            copiadeSeguridad.setPathCopia(Environment.getExternalStorageDirectory().
                                    getPath() + "/Android/data/" + dmain.getPackageName() + "/");
                            copiadeSeguridad.getEsGuardada().setIdCuenta(cuenta.getIdCuenta());

                            content.put(Tablas.EstructuraCopiadeSeguridad.COLUMN_NAME_FECHASUBIDA,
                                    String.valueOf(copiadeSeguridad.getFechaSubida()));
                            content.put(Tablas.EstructuraCopiadeSeguridad.COLUMN_NAME_PATHCOPIA,
                                    copiadeSeguridad.getPathCopia());
                            content.put(Tablas.EstructuraCopiadeSeguridad._IDCUENTA,
                                    cuenta.getIdCuenta());

                            sqlite.insert("CopiasdeSeguridad", null, content);

                            Cursor cursor = sqlite.rawQuery("SELECT * FROM CopiasdeSeguridad " +
                                    "WHERE CopiasdeSeguridad.idCuenta LIKE '" + cuenta.getIdCuenta() +
                                    "'",null);

                            cursor.moveToLast();

                            copiadeSeguridad.setIdCopiadeSeguridad(cursor.getInt(0));

                            //Creamos copia de Seguridad pasando por parámetros, origen y destino
                            BaseDatos.copiaBD(pathOrigen, copiadeSeguridad.getPathCopia() +
                                    "byeKilohBackup-" + copiadeSeguridad.getIdCopiadeSeguridad() +
                                    ".sqlite");

                            mensaje = new Mensaje(getActivity(), "Se ha creado Copia de Seguridad");

                            cursor.close();
                            sqlite.close();

                            refrescarListView();

                        }

                    }

                });

                btnBorrarCopia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        File archivoB = new File(Environment.getExternalStorageDirectory().
                                getPath() + "/Android/data/" + dmain.getPackageName() +
                                "/byeKilohBackup-" + copia.getIdCopiadeSeguridad() + ".sqlite");

                        if(archivoB.delete()) {

                            sqlite = basedatos.getWritableDatabase();

                            sqlite.delete("CopiasdeSeguridad", "CopiasdeSeguridad" +
                                    ".idCopiadeSeguridad = '" + copia.getIdCopiadeSeguridad() +
                                    "'", null);

                            mensaje = new Mensaje(getActivity(), "Se ha borrado idCopia: " +
                                    copia.getIdCopiadeSeguridad());

                            refrescarListView();

                            sqlite.close();

                        }
                        else {

                            mensaje = new Mensaje(getActivity(), "Error al borrar la " +
                                    "copia seleccionada");

                        }

                    }

                });

                btnRestaurarCopia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        basedatos.copiaBD(Environment.getExternalStorageDirectory().
                                getPath() + "/Android/data/" + dmain.getPackageName() +
                                "/byeKilohBackup-" + copia.getIdCopiadeSeguridad() + ".sqlite",
                                pathOrigen);

                        mensaje = new Mensaje(getActivity(), "Restaurada la aplicación a " +
                                "idCopia: " + copia.getIdCopiadeSeguridad());

                    }

                });


            }
            else {
                //no validada
                vistaCOP = inflater.inflate(R.layout.fragment_d_main_copiasdeseguridad_disable,
                        container,false);

                tvCopiasDeSeguridad = vistaCOP.findViewById(R.id.tvCopiasDeSeguridad);
                tvCopiasDeSeguridad.setText("Esta sección esta deshabilatada\nporque no tiene " +
                        "una cuenta validada");

            }

        }
        //Si no la tiene
        else {
            //Sin cuenta
            vistaCOP = inflater.inflate(R.layout.fragment_d_main_copiasdeseguridad_disable, container,
                    false);

            tvCopiasDeSeguridad = vistaCOP.findViewById(R.id.tvCopiasDeSeguridad);
            tvCopiasDeSeguridad.setText("Esta sección esta deshabilatada\nporque no tiene una cuenta");

        }

        //Cerramos cursor y conexion
        cursor.close();
        sqlite.close();

        return vistaCOP;

    }

    //Método que comprueba si se tiene permiso de lectura-escritura en la sd
    private boolean checkExternalStoragePermission(View v) {

        boolean checked = false;

        int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(dmain, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);

        }
        else {

            Log.i("Mensaje", "Se tiene permiso para leer!");
            checked = true;

        }

        return checked;

    }

    //Método que inicializa y refresca el ListView
    private void refrescarListView() {

        IntegridadRegistroCopias();

        //Recogemos todos los datos de CopiadeSeguridad Usuario Logeado
        sqlite = basedatos.getReadableDatabase();
        Cursor cursorlv = sqlite.rawQuery("SELECT * FROM CopiasdeSeguridad WHERE " +
                "CopiasdeSeguridad.idCuenta LIKE '" + cuenta.getIdCuenta() + "'",null);

        ArrayList<CopiadeSeguridad> listaitems = new ArrayList<>();

        if(cursorlv.getCount() != 0) {

            cursorlv.moveToFirst();

            do {

                copiadeSeguridad = new CopiadeSeguridad();
                copiadeSeguridad.setIdCopiadeSeguridad(cursorlv.getInt(0));
                copiadeSeguridad.setFechaSubida(Timestamp.valueOf(cursorlv.getString(1)));

                listaitems.add(copiadeSeguridad);

            } while(cursorlv.moveToNext());

        }

        adaptador = new Adaptador(dmain.getApplicationContext(), listaitems);
        lvCopias.setAdapter(adaptador);

        //Cerramos Cursor y Conexion
        cursorlv.close();
        sqlite.close();

    }

    //Método que garantiza que el registro tiene su archivo de respaldo, si no lo borra
    private void IntegridadRegistroCopias() {

        //Recogemos todos los datos de CopiadeSeguridad
        sqlite = basedatos.getWritableDatabase();
        Cursor cursorIRC = sqlite.rawQuery("SELECT * FROM CopiasdeSeguridad",null);

        ArrayList<CopiadeSeguridad> listaItems = new ArrayList<>();

        if(cursorIRC.getCount() != 0) {

            cursorIRC.moveToFirst();

            do {

                copiadeSeguridad = new CopiadeSeguridad();
                copiadeSeguridad.setIdCopiadeSeguridad(cursorIRC.getInt(0));
                copiadeSeguridad.setFechaSubida(Timestamp.valueOf(cursorIRC.getString(1)));

                listaItems.add(copiadeSeguridad);

            } while(cursorIRC.moveToNext());

            for(int i=0;i<listaItems.size();i++) {

                File archivoB = new File(Environment.getExternalStorageDirectory().
                        getPath() + "/Android/data/" + dmain.getPackageName() +
                        "/byeKilohBackup-" + listaItems.get(i).getIdCopiadeSeguridad() + ".sqlite");

                //Si el archivo no existe, se borra el Registro que le indicaba
                if(!archivoB.exists()) {

                    sqlite.delete("CopiasdeSeguridad", "CopiasdeSeguridad" +
                            ".idCopiadeSeguridad = '" + listaItems.get(i).getIdCopiadeSeguridad() +
                            "'", null);

                }

            }

        }

        cursorIRC.close();
        sqlite.close();

    }

}