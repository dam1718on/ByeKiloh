package com.example.byeKiloh.fragments;

import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.activitys.*;
import com.example.byeKiloh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link C_Main_Ejercicios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class C_Main_Ejercicios extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaE;

    private Button btnCreateEjercicios, btnReadEjercicios, btnUpdateEjercicios, btnDeleteEjercicios;

    String fragId;
    String idC;

    //Se carga la activity para poder extraer el idUsuario y propagarlo a los fragments
    public D_Main dmain;

    public C_Main_Ejercicios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment C_Main_Ejercicios.
     */

    // TODO: Rename and change types and number of parameters
    public static C_Main_Ejercicios newInstance(String param1, String param2) {
        C_Main_Ejercicios fragment = new C_Main_Ejercicios();
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

        vistaE = inflater.inflate(R.layout.fragment_c_main_ejercicios, container, false);

        btnCreateEjercicios = vistaE.findViewById(R.id.btnCreateEjercicios);
        btnReadEjercicios = vistaE.findViewById(R.id.btnReadEjercicios);
        btnUpdateEjercicios = vistaE.findViewById(R.id.btnUpdateEjercicios);
        btnDeleteEjercicios = vistaE.findViewById(R.id.btnDeleteEjercicios);

        //Instanciamos la activity que contiene la variable
        dmain = (D_Main) getActivity();
        idC = String.valueOf(dmain.idUs);

        //Creamos los intent que inician .E_Crud combinando el nºfragment con el idUsuario
        btnCreateEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "e1";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idC);
            startActivity(intent);
            }

        });

        btnReadEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "e2";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idC);
            startActivity(intent);
            }

        });

        btnUpdateEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "e3";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idC);
            startActivity(intent);
            }

        });

        btnDeleteEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "e4";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idC);
            startActivity(intent);
            }

        });

        return vistaE;

    }

}