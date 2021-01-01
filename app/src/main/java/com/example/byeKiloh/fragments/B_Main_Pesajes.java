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
 * Use the {@link B_Main_Pesajes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class B_Main_Pesajes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaP;

    private Button btnCreatePesajes, btnReadPesajes, btnUpdatePesajes, btnDeletePesajes;

    String fragId;
    String idB;

    //Se carga la activity para poder extraer el idUsuario y propagarlo a los fragments
    public D_Main dmain2;

    public B_Main_Pesajes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment B_Main_Pesajes.
     */

    // TODO: Rename and change types and number of parameters
    public static B_Main_Pesajes newInstance(String param1, String param2) {
        B_Main_Pesajes fragment = new B_Main_Pesajes();
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

        vistaP = inflater.inflate(R.layout.fragment_b_main_pesajes, container, false);

        btnCreatePesajes = vistaP.findViewById(R.id.btnCreatePesajes);
        btnReadPesajes = vistaP.findViewById(R.id.btnReadPesajes);
        btnUpdatePesajes = vistaP.findViewById(R.id.btnUpdatePesajes);
        btnDeletePesajes = vistaP.findViewById(R.id.btnDeletePesajes);

        //Instanciamos la activity que contiene la variable
        dmain2 = (D_Main) getActivity();
        idB = String.valueOf(dmain2.idUs);

        //Creamos los intent que inician .E_Crud
        btnCreatePesajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "p1";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idB);
            startActivity(intent);
            }

        });

        btnReadPesajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "p2";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idB);
            startActivity(intent);
            }

        });

        btnUpdatePesajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "p3";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idB);
            startActivity(intent);
            }

        });

        btnDeletePesajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragId = "p4";
            Intent intent = new Intent(getActivity(), E_Crud.class);
            intent.putExtra("nfrag", fragId + "-" + idB);
            startActivity(intent);
            }

        });

        return vistaP;

    }

}