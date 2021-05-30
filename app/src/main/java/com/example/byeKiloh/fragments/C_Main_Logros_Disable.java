package com.example.byeKiloh.fragments;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.text.SpannableString;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.byeKiloh.R;
import com.example.byeKiloh.activitys.D_Main;
import com.example.byeKiloh.datapersistence.*;
import com.example.byeKiloh.objects.*;
import com.example.byeKiloh.utils.Mensaje;

import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraLogro.*;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraLogrosdeCuenta.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link C_Main_Logros_Disable#newInstance} factory method to
 * create an instance of this fragment.
 */

public class C_Main_Logros_Disable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vistaE;

    private TextView tvLogros;

    private ImageView ivLogro1, ivLogro2, ivLogro3, ivLogro4, ivLogro5, ivLogro6;

    private TableRow tr1, tr2, tr3, tr4, tr5, tr6;

    private final String[] nombreLogros = {

        "Distancia Recorrida I","Distancia Recorrida II","Tiempo Empleado I", "Tiempo Empleado II",
        "Inclinación Terreno I", "Inclinación Terreno II"

    };

    private final String[] descripcionLogros = {

        "Este Logro se consigue al recorrer una distancia un 50% superior a la media",
        "Este Logro se consigue al recorrer una distancia un 100% superior a la media",
        "Este Logro se consigue al emplear un tiempo un 50% superior a la media",
        "Este Logro se consigue al emplear un tiempo un 100% superior a la media",
        "Este Logro se consigue al caminar con una inclinacion del terreno un 50% superior a la media",
        "Este Logro se consigue al caminar con una inclinacion del terreno un 100% superior a la media",

    };

    String idC;

    BaseDatos basedatos;
    Cuenta cuenta;
    LogrosdeCuenta logrosdeCuenta;
    Logro logro;
    Mensaje mensaje;

    //Se carga la activity para poder extraer el idUsuario y propagarlo a los fragments
    public D_Main dmain;

    public C_Main_Logros_Disable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment C_Main_Logros_Disable.
     */

    // TODO: Rename and change types and number of parameters
    public static C_Main_Logros_Disable newInstance(String param1, String param2) {

        C_Main_Logros_Disable fragment = new C_Main_Logros_Disable();
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
        idC = String.valueOf(dmain.idUs);

        //Recogemos todos los datos de la cuentaRegistros del Usuario Logeado
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM Cuentas WHERE " +
                "Cuentas.idUsuario LIKE '" + idC + "'",null);

        //Si el usuario tiene cuenta/s
        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            cuenta = new Cuenta();
            cuenta.setIdCuenta(cursor.getInt(cursor.getColumnIndex(Tablas.EstructuraCuenta._IDCUENTA)));
            cuenta.setCuentaValidada(cursor.getString(cursor.getColumnIndex
                    (Tablas.EstructuraCuenta.COLUMN_NAME_VALIDADO)));

            //Y tiene la cuenta validada
            if(cuenta.isCuentaValidada()) {

                //Gana acceso a LOGROS
                vistaE = inflater.inflate(R.layout.fragment_c_main_logros_enable, container, false);

                TextView tvNombre1 = vistaE.findViewById(R.id.tvNombre1);
                TextView tvNombre2 = vistaE.findViewById(R.id.tvNombre2);
                TextView tvNombre3 = vistaE.findViewById(R.id.tvNombre3);
                TextView tvNombre4 = vistaE.findViewById(R.id.tvNombre4);
                TextView tvNombre5 = vistaE.findViewById(R.id.tvNombre5);
                TextView tvNombre6 = vistaE.findViewById(R.id.tvNombre6);

                TextView tvDesc1 = vistaE.findViewById(R.id.tvDesc1);
                TextView tvDesc2 = vistaE.findViewById(R.id.tvDesc2);
                TextView tvDesc3 = vistaE.findViewById(R.id.tvDesc3);
                TextView tvDesc4 = vistaE.findViewById(R.id.tvDesc4);
                TextView tvDesc5 = vistaE.findViewById(R.id.tvDesc5);
                TextView tvDesc6 = vistaE.findViewById(R.id.tvDesc6);

                ivLogro1 = vistaE.findViewById(R.id.ivLogro1);
                ivLogro2 = vistaE.findViewById(R.id.ivLogro2);
                ivLogro3 = vistaE.findViewById(R.id.ivLogro3);
                ivLogro4 = vistaE.findViewById(R.id.ivLogro4);
                ivLogro5 = vistaE.findViewById(R.id.ivLogro5);
                ivLogro6 = vistaE.findViewById(R.id.ivLogro6);

                tr1 = vistaE.findViewById(R.id.tr1);
                tr2 = vistaE.findViewById(R.id.tr2);
                tr3 = vistaE.findViewById(R.id.tr3);
                tr4 = vistaE.findViewById(R.id.tr4);
                tr5 = vistaE.findViewById(R.id.tr5);
                tr6 = vistaE.findViewById(R.id.tr6);


                //Convertimos en clickeables los TextViews(6*2)
                SpannableString contentN1 = new SpannableString(tvNombre1.getText());
                tvNombre1.setText(contentN1);
                SpannableString contentN2 = new SpannableString(tvNombre2.getText());
                tvNombre2.setText(contentN2);
                SpannableString contentN3 = new SpannableString(tvNombre3.getText());
                tvNombre3.setText(contentN3);
                SpannableString contentN4 = new SpannableString(tvNombre4.getText());
                tvNombre4.setText(contentN4);
                SpannableString contentN5 = new SpannableString(tvNombre5.getText());
                tvNombre5.setText(contentN5);
                SpannableString contentN6 = new SpannableString(tvNombre6.getText());
                tvNombre6.setText(contentN6);

                SpannableString contentD1 = new SpannableString(tvDesc1.getText());
                tvDesc1.setText(contentD1);
                SpannableString contentD2 = new SpannableString(tvDesc2.getText());
                tvDesc2.setText(contentD2);
                SpannableString contentD3 = new SpannableString(tvDesc3.getText());
                tvDesc3.setText(contentD3);
                SpannableString contentD4 = new SpannableString(tvDesc4.getText());
                tvDesc4.setText(contentD4);
                SpannableString contentD5 = new SpannableString(tvDesc5.getText());
                tvDesc5.setText(contentD5);
                SpannableString contentD6 = new SpannableString(tvDesc6.getText());
                tvDesc6.setText(contentD6);


                //Array de Logros
                ArrayList<Logro> logrosAD = new ArrayList<>();

                String[] columns = {"nombreLogro","descripcionLogro"};
                //Query que devuelve todas las basculas del Usuario logeado
                Cursor cursor2 = sqlite.query("Logros", columns,null,null,
                        null,null,null,null);

                for(int i=0;i<nombreLogros.length;i++) {

                    logro = new Logro();
                    logro.setIdLogro(i+1);
                    logro.setNombreLogro(nombreLogros[i]);
                    logro.setDescripcionLogro(descripcionLogros[i]);

                    if(cursor2.getCount() == 0) {

                        ContentValues content2 = new ContentValues();

                        content2.put(Tablas.EstructuraLogro._IDLOGRO, logro.getIdLogro());
                        content2.put(COLUMN_NAME_NOMBRELOGRO, logro.getNombreLogro());
                        content2.put(COLUMN_NAME_DESCRIPCIONLOGRO, logro.getDescripcionLogro());

                        sqlite.insert("Logros", null, content2);

                        content2.clear();

                    }

                    logrosAD.add(logro);


                }
                //Cierre del cursor
                cursor2.close();


                //Query que comprueba si los Logros ya se habían conseguido anteriormente
                Cursor cursorES = sqlite.rawQuery("SELECT * FROM LogrosdeCuentas WHERE " +
                        "LogrosdeCuentas.idCuenta LIKE '" + cuenta.getIdCuenta() + "'",null);

                //Si el Logro ya estaba insertado
                if(cursorES.getCount() != 0) {

                    cursorES.moveToFirst();

                    //Estructura do-while que filtra el idLogro
                    do {

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==1) {

                            tr1.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro1.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==2) {

                            tr2.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro2.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==3) {

                            tr3.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro3.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==4) {

                            tr4.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro4.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==5) {

                            tr5.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro5.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                        if(cursorES.getInt(cursorES.getColumnIndex(Tablas.
                                EstructuraLogrosdeCuenta._IDLOGRO))==6) {

                            tr6.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                            ivLogro6.setBackgroundResource(R.drawable.ic_usuario_platino);

                        }

                    }
                    while(cursorES.moveToNext());

                }

                cursorES.close();


                //setText de los TextViews
                tvNombre1.setText(logrosAD.get(0).getNombreLogro());
                tvNombre2.setText(logrosAD.get(1).getNombreLogro());
                tvNombre3.setText(logrosAD.get(2).getNombreLogro());
                tvNombre4.setText(logrosAD.get(3).getNombreLogro());
                tvNombre5.setText(logrosAD.get(4).getNombreLogro());
                tvNombre6.setText(logrosAD.get(5).getNombreLogro());

                tvDesc1.setText(logrosAD.get(0).getDescripcionLogro());
                tvDesc2.setText(logrosAD.get(1).getDescripcionLogro());
                tvDesc3.setText(logrosAD.get(2).getDescripcionLogro());
                tvDesc4.setText(logrosAD.get(3).getDescripcionLogro());
                tvDesc5.setText(logrosAD.get(4).getDescripcionLogro());
                tvDesc6.setText(logrosAD.get(5).getDescripcionLogro());

                tvNombre1.setOnClickListener(v -> {

                    if(logrosAD.get(0).conseguirLogro(v, idC)) {

                        tr1.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro1.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(0));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvNombre2.setOnClickListener(v -> {

                    if(logrosAD.get(1).conseguirLogro(v, idC)){

                        tr2.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro2.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(1));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvNombre3.setOnClickListener(v -> {

                    if(logrosAD.get(2).conseguirLogro(v, idC)) {

                        tr3.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro3.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(2));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvNombre4.setOnClickListener(v -> {

                    if(logrosAD.get(3).conseguirLogro(v, idC)) {

                        tr4.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro4.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(3));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvNombre5.setOnClickListener(v -> {

                    if(logrosAD.get(4).conseguirLogro(v, idC)) {

                        tr5.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro5.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(4));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvNombre6.setOnClickListener(v -> {

                    if(logrosAD.get(5).conseguirLogro(v, idC)) {

                        tr6.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro6.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(5));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc1.setOnClickListener(v -> {

                    if(logrosAD.get(0).conseguirLogro(v, idC)) {


                        tr1.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro1.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(0));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc2.setOnClickListener(v -> {

                    if(logrosAD.get(1).conseguirLogro(v, idC)) {

                        tr2.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro2.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(1));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc3.setOnClickListener(v -> {

                    if(logrosAD.get(2).conseguirLogro(v, idC)) {

                        tr3.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro3.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(2));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc4.setOnClickListener(v -> {

                    if(logrosAD.get(3).conseguirLogro(v, idC)) {

                        tr4.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro4.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(3));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc5.setOnClickListener(v -> {

                    if(logrosAD.get(4).conseguirLogro(v, idC)) {

                        tr5.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro5.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(4));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                tvDesc6.setOnClickListener(v -> {

                    if(logrosAD.get(5).conseguirLogro(v, idC)) {

                        tr6.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro6.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(5));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro1.setOnClickListener(v -> {

                    if(logrosAD.get(0).conseguirLogro(v, idC)) {

                        tr1.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro1.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(0));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro2.setOnClickListener(v -> {

                    if(logrosAD.get(1).conseguirLogro(v, idC)) {

                        tr2.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro2.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(1));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro3.setOnClickListener(v -> {

                    if(logrosAD.get(2).conseguirLogro(v, idC)) {

                        tr3.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro3.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(2));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro4.setOnClickListener(v -> {

                    if(logrosAD.get(3).conseguirLogro(v, idC)) {

                        tr4.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro4.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(3));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro5.setOnClickListener(v -> {

                    if(logrosAD.get(4).conseguirLogro(v, idC)) {

                        tr5.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro5.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(4));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

                ivLogro6.setOnClickListener(v -> {

                    if(logrosAD.get(5).conseguirLogro(v, idC)) {

                        tr6.setBackgroundResource(R.drawable.rounded_button_gradient_orange);
                        ivLogro6.setBackgroundResource(R.drawable.ic_usuario_platino);
                        insertarLogro(logrosAD.get(5));

                    }
                    else {
                        mensaje = new Mensaje(getActivity(), "Aun no cumples los " +
                                "requisitos\npara conseguir este logro");
                    }

                });

            }
            else {
                //no validada
                vistaE = inflater.inflate(R.layout.fragment_c_main_logros_disable, container,
                        false);

                tvLogros = vistaE.findViewById(R.id.tvLogros);
                String tvlogro1 = "Esta sección esta deshabilatada\nporque no tiene una cuenta validada";
                tvLogros.setText(tvlogro1);

            }
        }

        //Si no la tiene
        else {
            //Sin cuenta
            vistaE = inflater.inflate(R.layout.fragment_c_main_logros_disable, container,
                    false);

            tvLogros = vistaE.findViewById(R.id.tvLogros);
            tvLogros.setText("Esta sección esta deshabilatada\nporque no tiene una cuenta");

        }
        //Cerramos cursor y conexion
        cursor.close();
        sqlite.close();

        return vistaE;

    }

    public void insertarLogro(Logro logro) {

        logrosdeCuenta = new LogrosdeCuenta();
        logrosdeCuenta.initFechaLogro();

        //Recogemos todos los datos de la cuentaRegistros del Usuario Logeado
        SQLiteDatabase sqliteIN = basedatos.getWritableDatabase();

        //Query que comprueba si el Logro ya estaba guardado
        Cursor cursorIN = sqliteIN.rawQuery("SELECT * FROM LogrosdeCuentas WHERE " +
                "LogrosdeCuentas.idLogro LIKE '" + logro.getIdLogro() + "' AND " +
                "LogrosdeCuentas.idCuenta LIKE '" + cuenta.getIdCuenta() + "'",null);

        //Si el Logro ya estaba insertado
        if(cursorIN.getCount() == 0) {

            ContentValues contentIN = new ContentValues();

            contentIN.put(COLUMN_NAME_FECHALOGRO, String.valueOf(logrosdeCuenta.getFechaLogro()));
            contentIN.put(Tablas.EstructuraLogrosdeCuenta._IDLOGRO, logro.getIdLogro());
            contentIN.put(Tablas.EstructuraLogrosdeCuenta._IDCUENTA, cuenta.getIdCuenta());

            sqliteIN.insert("LogrosdeCuentas", null, contentIN);

            mensaje = new Mensaje(getActivity(), "Has conseguido el logro:\n"+
                    logro.getNombreLogro());

        }

        mensaje = new Mensaje(getActivity(), "El logro: " + logro.getNombreLogro() + "\n " +
                "ya lo habías conseguido");

        cursorIN.close();
        sqliteIN.close();

    }

}