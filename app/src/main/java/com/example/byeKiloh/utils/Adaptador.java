package com.example.byeKiloh.utils;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.byeKiloh.R;
import com.example.byeKiloh.objects.CopiadeSeguridad;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<CopiadeSeguridad> listaCopiasSeg;

    public Adaptador(Context context, ArrayList<CopiadeSeguridad> listaCopiasSeg) {

        this.context = context;
        this.listaCopiasSeg = listaCopiasSeg;

    }

    @Override
    public int getCount() {  return listaCopiasSeg.size();  }

    @Override
    public Object getItem(int i) {  return listaCopiasSeg.get(i);  }

    @Override
    public long getItemId(int i) {  return listaCopiasSeg.get(i).getIdCopiadeSeguridad();  }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CopiadeSeguridad item = (CopiadeSeguridad) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.listview_item_d, null);

        TextView tvNombreArchivo = view.findViewById(R.id.tvNombreArchivo);
        TextView tvFechaCopia = view.findViewById(R.id.tvFechaCopia);

        tvNombreArchivo.setText(String.valueOf(item.getIdCopiadeSeguridad()));
        tvFechaCopia.setText(String.valueOf(item.getFechaSubida()));

        return view;

    }

}