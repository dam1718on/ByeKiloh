package com.example.byeKiloh.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.D_Main;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link D_Main_CopiasDeSeguridad#newInstance} factory method to
 * create an instance of this fragment.
 */

public class D_Main_CopiasDeSeguridad extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaCOP;

    private TextView tvCopiasDeSeguridad;

    String idCOP;

    BaseDatos basedatos;
    Cuenta cuenta;

    //Se carga la activity para poder extraer el idUsuario y propagarlo a los fragments
    public D_Main dmain;

    public D_Main_CopiasDeSeguridad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment D_Main_CopiasDeSeguridad.
     */

    // TODO: Rename and change types and number of parameters
    public static D_Main_CopiasDeSeguridad newInstance(String param1, String param2) {

        D_Main_CopiasDeSeguridad fragment = new D_Main_CopiasDeSeguridad();
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

        vistaCOP = inflater.inflate(R.layout.fragment_d_main_copiasdeseguridad, container, false);

        tvCopiasDeSeguridad = vistaCOP.findViewById(R.id.tvCopiasDeSeguridad);

        //Instanciamos la activity que contiene la variable
        dmain = (D_Main) getActivity();
        idCOP = String.valueOf(dmain.idUs);

        basedatos = new BaseDatos(getActivity());

        //Recogemos todos los datos de la cuentaRegistros del Usuario Logeado
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Cuentas WHERE " +
                "Cuentas.idUsuario LIKE '" + idCOP + "'",null);

        //Si el usuario tiene cuenta/s
        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            cuenta = new Cuenta();
            cuenta.setCuentaValidada(cursor.getString(cursor.getColumnIndex
                    (Tablas.EstructuraCuenta.COLUMN_NAME_VALIDADO)));
            //Y tiene la cuenta validada
            if(cuenta.isCuentaValidada()) {
                //Gana acceso a LOGROS
                tvCopiasDeSeguridad.setText("Acceso concedido");
            }
            else {
                //no validada
                tvCopiasDeSeguridad.setText("Esta sección esta deshabilatada\nporque no tiene una cuenta validada");
            }
        }
        //Si no la tiene
        else {
            //Sin cuenta
            tvCopiasDeSeguridad.setText("Esta sección esta deshabilatada\nporque no tiene una cuenta");
        }
        //Cerramos cursor y conexion
        cursor.close();
        sqlite.close();

        return vistaCOP;

    }

}