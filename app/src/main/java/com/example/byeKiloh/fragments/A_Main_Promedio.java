package com.example.byeKiloh.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.D_Main;
import com.example.byeKiloh.datapersistence.BaseDatos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link A_Main_Promedio#newInstance} factory method to
 * create an instance of this fragment.
 */

public class A_Main_Promedio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaPro;

    private TextView tvDistanciaMin, tvDistanciaMax, tvDistanciaMedia;
    private TextView tvTiempodMin, tvTiempodMax, tvTiempodMedia;
    private TextView tvVelocidadMin, tvVelocidadMax, tvVelocidadMedia;
    private TextView tvInclinacionMin, tvInclinacionMax, tvInclinacionMedia;
    private TextView tvConsumoEMin, tvConsumoEMax, tvConsumoEMedia;

    private TextView tvPesoPMin, tvPesoPMax, tvPesoPMedia;
    private TextView tvImcPMin, tvImcPMax, tvImcPMedia;

    private TextView tvEjerCount, tvPesaCount;

    String idPro;

    //Se carga la activity para poder extraer el idUsuario
    public D_Main dpmain;

    BaseDatos basedatos;

    public A_Main_Promedio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A_Main_Promedio.
     */

    // TODO: Rename and change types and number of parameters
    public static A_Main_Promedio newInstance(String param1, String param2) {

        A_Main_Promedio fragment = new A_Main_Promedio();
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

        vistaPro = inflater.inflate(R.layout.fragment_a_main_promedio, container, false);

        tvDistanciaMin = vistaPro.findViewById(R.id.tvDistanciaMin);
        tvDistanciaMax = vistaPro.findViewById(R.id.tvDistanciaMax);
        tvDistanciaMedia = vistaPro.findViewById(R.id.tvDistanciaMedia);

        tvTiempodMin = vistaPro.findViewById(R.id.tvTiempodMin);
        tvTiempodMax = vistaPro.findViewById(R.id.tvTiempodMax);
        tvTiempodMedia = vistaPro.findViewById(R.id.tvTiempodMedia);

        tvVelocidadMin = vistaPro.findViewById(R.id.tvVelocidadMin);
        tvVelocidadMax = vistaPro.findViewById(R.id.tvVelocidadMax);
        tvVelocidadMedia = vistaPro.findViewById(R.id.tvVelocidadMedia);

        tvInclinacionMin = vistaPro.findViewById(R.id.tvInclinacionMin);
        tvInclinacionMax = vistaPro.findViewById(R.id.tvInclinacionMax);
        tvInclinacionMedia = vistaPro.findViewById(R.id.tvInclinacionMedia);

        tvConsumoEMin = vistaPro.findViewById(R.id.tvConsumoEMin);
        tvConsumoEMax = vistaPro.findViewById(R.id.tvConsumoEMax);
        tvConsumoEMedia = vistaPro.findViewById(R.id.tvConsumoEMedia);

        tvPesoPMin = vistaPro.findViewById(R.id.tvPesoPMin);
        tvPesoPMax = vistaPro.findViewById(R.id.tvPesoPMax);
        tvPesoPMedia = vistaPro.findViewById(R.id.tvPesoPMedia);

        tvImcPMin = vistaPro.findViewById(R.id.tvImcPMin);
        tvImcPMax = vistaPro.findViewById(R.id.tvImcPMax);
        tvImcPMedia = vistaPro.findViewById(R.id.tvImcPMedia);

        tvEjerCount = vistaPro.findViewById(R.id.tvEjerCount);
        tvPesaCount = vistaPro.findViewById(R.id.tvPesaCount);

        //Instanciamos la activity que contiene la variable
        dpmain = (D_Main) getActivity();
        idPro = String.valueOf(dpmain.idUs);

        basedatos = new BaseDatos(getActivity());

        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        /*Cursor cursor = sqlite.rawQuery("SELECT " +
                "MIN(distanciaRecorrida), MAX(distanciaRecorrida), ROUND(AVG(distanciaRecorrida),2), " +
                "MIN(tiempo), MAX(tiempo), ROUND(AVG(tiempo),2), " +
                "MIN(velocidad), MAX(velocidad), ROUND(AVG(velocidad),2), " +
                "MIN(inclinacion), MAX(inclinacion), ROUND(AVG(inclinacion),2), " +
                "MIN(consumoE), MAX(consumoE), ROUND(AVG(consumoE),2), COUNT(distancia) " +
                "FROM Ejercicios WHERE idUsuario LIKE '" + idPro + "'", null);*/

        /*Cursor cursorPes = sqlite.rawQuery("SELECT " +
                "MIN(pesoUsuario), MAX(pesoUsuario), ROUND(AVG(pesoUsuario),2), " +
                //"MIN(imc), MAX(imc), ROUND(AVG(imc),2), " +
                "COUNT(pesoUsuario) " +
                "FROM Basculas WHERE idUsuario LIKE '" + idPro + "'", null);*/

        //cursor.moveToFirst();
        //cursorPes.moveToFirst();
/*
        //TextViews Ejercicios
        TextView olo[] = new TextView[16];

        olo[0] = tvDistanciaMin;
        olo[1] = tvDistanciaMax;
        olo[2] = tvDistanciaMedia;

        olo[3] = tvTiempodMin;
        olo[4] = tvTiempodMax;
        olo[5] = tvTiempodMedia;

        olo[6] = tvVelocidadMin;
        olo[7] = tvVelocidadMax;
        olo[8] = tvVelocidadMedia;

        olo[9] = tvInclinacionMin;
        olo[10] = tvInclinacionMax;
        olo[11] = tvInclinacionMedia;

        olo[12] = tvConsumoEMin;
        olo[13] = tvConsumoEMax;
        olo[14] = tvConsumoEMedia;

        olo[12] = tvConsumoEMin;
        olo[13] = tvConsumoEMax;
        olo[14] = tvConsumoEMedia;

        olo[15] = tvEjerCount;

        //TextViews de Pesajes
        TextView oloP[] = new TextView[7];

        oloP[0] = tvPesoPMin;
        oloP[1] = tvPesoPMax;
        oloP[2] = tvPesoPMedia;

        //oloP[3] = tvImcPMin;
        //oloP[4] = tvImcPMax;
        //oloP[5] = tvImcPMedia;

        oloP[3] = tvPesaCount;

        for (int i=0;i<olo.length;i++) {

            if (cursor.getString(i) != null) {
                olo[i].setText(cursor.getString(i).replace(".", ","));
            } else {
                olo[i].setText("N/D");
            }

        }*/

        /*for (int i=0;i<oloP.length;i++) {

            if (cursorPes.getString(i) != null) {
                oloP[i].setText(cursorPes.getString(i).replace(".", ","));
            } else {
                oloP[i].setText("N/D");
            }

        }*/

        //Cerramos cursores
        //cursor.close();
        //cursorPes.close();
        //Cerramos la conexión con la Base de Datos
        //sqlite.close();

        return vistaPro;

    }

}