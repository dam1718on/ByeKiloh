package com.example.byeKiloh.fragments;

import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.Activity_e_CRUD;
import com.example.byeKiloh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main_c_Ejercicios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_c_Ejercicios extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button btnCreateEjercicios, btnReadEjercicios, btnUpdateEjercicios, btnDeleteEjercicios;
    String frag = "";

    public Main_c_Ejercicios() {    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_c_Ejercicios.
     */
    // TODO: Rename and change types and number of parameters
    public static Main_c_Ejercicios newInstance(String param1, String param2) {
        Main_c_Ejercicios fragment = new Main_c_Ejercicios();
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

        vista = inflater.inflate(R.layout.fragment_main_c_ejercicios, container, false);

        btnCreateEjercicios = vista.findViewById(R.id.btnCreateEjercicios);
        btnReadEjercicios = vista. findViewById(R.id.btnReadEjercicios);
        btnUpdateEjercicios = vista.findViewById(R.id.btnUpdateEjercicios);
        btnDeleteEjercicios = vista.findViewById(R.id.btnDeleteEjercicios);

        //Creamos los intent que inician .Activity_e_CRUD
        btnCreateEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            frag = "uno";
            Intent intent = new Intent(getActivity(), Activity_e_CRUD.class);
            intent.putExtra("nfrag", frag);
            startActivity(intent);
            }

        });

        btnReadEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            frag = "dos";
            Intent intent = new Intent(getActivity(), Activity_e_CRUD.class);
            intent.putExtra("nfrag", frag);
            startActivity(intent);
            }

        });

        btnUpdateEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            frag = "tres";
            Intent intent = new Intent(getActivity(), Activity_e_CRUD.class);
            intent.putExtra("nfrag", frag);
            startActivity(intent);
            }

        });

        btnDeleteEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            frag = "cuatro";
            Intent intent = new Intent(getActivity(), Activity_e_CRUD.class);
            intent.putExtra("nfrag", frag);
            startActivity(intent);
            }

        });

        return vista;

    }

}