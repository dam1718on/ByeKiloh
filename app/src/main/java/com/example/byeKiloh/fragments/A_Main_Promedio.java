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
import com.example.byeKiloh.datapersistence.BaseDatos;
import com.example.byeKiloh.objects.Bascula;
import com.example.byeKiloh.objects.Ejercicio;
import com.example.byeKiloh.objects.Registro;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_DISTANCIARECORRIDA;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_INCLINACIONTERRENO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio.COLUMN_NAME_TIEMPOEMPLEADO;
import static com.example.byeKiloh.datapersistence.Tablas.EstructuraEjercicio._IDEJERCICIO;

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

    Ejercicio ejercicio;
    Bascula bascula;

    //Se carga la activity para poder extraer el idUsuario
    public D_Main dpmain;

    String idPro;

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

        TextView tvDistanciaMin = vistaPro.findViewById(R.id.tvDistanciaMin);
        TextView tvDistanciaMax = vistaPro.findViewById(R.id.tvDistanciaMax);
        TextView tvDistanciaMedia = vistaPro.findViewById(R.id.tvDistanciaMedia);

        TextView tvTiempodMin = vistaPro.findViewById(R.id.tvTiempodMin);
        TextView tvTiempodMax = vistaPro.findViewById(R.id.tvTiempodMax);
        TextView tvTiempodMedia = vistaPro.findViewById(R.id.tvTiempodMedia);

        TextView tvInclinacionMin = vistaPro.findViewById(R.id.tvInclinacionMin);
        TextView tvInclinacionMax = vistaPro.findViewById(R.id.tvInclinacionMax);
        TextView tvInclinacionMedia = vistaPro.findViewById(R.id.tvInclinacionMedia);

        TextView tvVelocidadMin = vistaPro.findViewById(R.id.tvVelocidadMin);
        TextView tvVelocidadMax = vistaPro.findViewById(R.id.tvVelocidadMax);
        TextView tvVelocidadMedia = vistaPro.findViewById(R.id.tvVelocidadMedia);

        TextView tvPesoPMin = vistaPro.findViewById(R.id.tvPesoPMin);
        TextView tvPesoPMax = vistaPro.findViewById(R.id.tvPesoPMax);
        TextView tvPesoPMedia = vistaPro.findViewById(R.id.tvPesoPMedia);

        TextView tvImcPMin = vistaPro.findViewById(R.id.tvImcPMin);
        TextView tvImcPMax = vistaPro.findViewById(R.id.tvImcPMax);
        TextView tvImcPMedia = vistaPro.findViewById(R.id.tvImcPMedia);

        TextView tvEjerCount = vistaPro.findViewById(R.id.tvEjerCount);
        TextView tvPesaCount = vistaPro.findViewById(R.id.tvPesaCount);

        //Instanciamos la activity que contiene la variable
        dpmain = (D_Main) getActivity();
        idPro = String.valueOf(dpmain.idUs);

        basedatos = new BaseDatos(getActivity());
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();

        //Query que devuelve los resultados aritméticos de los Ejercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT " +
                "MIN(Ejercicios.distanciaRecorrida), MAX(Ejercicios.distanciaRecorrida), " +
                "ROUND(AVG(Ejercicios.distanciaRecorrida),2), MIN(Ejercicios.tiempoEmpleado), " +
                "MAX(Ejercicios.tiempoEmpleado), ROUND(AVG(Ejercicios.tiempoEmpleado),2), " +
                "MIN(Ejercicios.inclinacionTerreno), MAX(Ejercicios.inclinacionTerreno), " +
                "ROUND(AVG(Ejercicios.inclinacionTerreno),2), COUNT(Ejercicios.distanciaRecorrida) " +
                "FROM Ejercicios, Registros WHERE Registros.idEjercicio = Ejercicios.idEjercicio AND" +
                " Registros.idUsuario LIKE '" + idPro + "'" , null);

        cursor.moveToFirst();

        //TextViews Ejercicios
        TextView olo[] = new TextView[10];

        olo[0] = tvDistanciaMin;
        olo[1] = tvDistanciaMax;
        olo[2] = tvDistanciaMedia;

        olo[3] = tvTiempodMin;
        olo[4] = tvTiempodMax;
        olo[5] = tvTiempodMedia;

        olo[6] = tvInclinacionMin;
        olo[7] = tvInclinacionMax;
        olo[8] = tvInclinacionMedia;

        olo[9] = tvEjerCount;


        for (int i=0;i<olo.length;i++) {

            if (cursor.getString(i) != null) {

                olo[i].setText(cursor.getString(i).replace(".", ","));

            }
            else {  olo[i].setText("N/D");  }

        }


        String velocidad=null, velocidadMin = null, velocidadMax = null, velocidadMedia = null;

        //Cursor para conseguir la velocidad media de los Ejercicios
        Cursor cursorEj = sqlite.rawQuery("SELECT Ejercicios.distanciaRecorrida, " +
                "Ejercicios.tiempoEmpleado FROM Registros, Ejercicios WHERE Registros.idEjercicio " +
                "= Ejercicios.idEjercicio AND Registros.idUsuario LIKE '" + idPro + "'" , null);

        //Comprobamos si el cursor no es null
        if(cursorEj.getCount() != 0) {

            cursorEj.moveToFirst();

            //Estructura do-while
            do {
                ejercicio = new Ejercicio();
                ejercicio.setDistanciaRecorrida(cursorEj.getInt(0));
                ejercicio.setTiempoEmpleado(cursorEj.getInt(1));

                velocidad = ejercicio.velocidadMedia();

                //Bucle if para velocidadMin
                if(velocidadMin!=null) {
                    if(Float.parseFloat(velocidadMin.replace(",","."))
                            >Float.parseFloat(velocidad.replace(",","."))) {

                        velocidadMin = velocidad;

                    }
                }//siempre que sea null adquiere la velocidad de esta iteración
                else {  velocidadMin = velocidad;  }

                //Bucle if para velocidadMax
                if(velocidadMax!=null) {
                    if(Float.parseFloat(velocidadMax.replace(",","."))
                            <Float.parseFloat(velocidad.replace(",","."))) {

                        velocidadMax = velocidad;

                    }
                }//siempre que sea null adquiere la velocidad de esta iteración
                else {  velocidadMax = velocidad;  }

                //Bucle if para velocidadMedia
                if (velocidadMedia!= null) {

                    //Se utiliza este método para poder controlar la salida del Float
                    velocidadMedia = calculoVelMed(velocidadMedia, velocidad);

                }//siempre que sea null adquiere la velocidad de esta iteración
                else {  velocidadMedia = velocidad;  }

            } while(cursorEj.moveToNext());

        }

        //Visualizamos el resultado de la velocidades medias en los EditText
        if(velocidadMin!=null) {
            tvVelocidadMin.setText(velocidadMin.replace(".",","));
        }
        else {  tvVelocidadMin.setText("N/D");  }

        if(velocidadMax!=null) {
            tvVelocidadMax.setText(velocidadMax.replace(".",","));
        }
        else {  tvVelocidadMax.setText("N/D");  }

        if(velocidadMedia!=null) {
            tvVelocidadMedia.setText(velocidadMedia.replace(".",","));
        }
        else {  tvVelocidadMedia.setText("N/D");  }


        //Query que devuelve los resultados aritméticos de las Básculas del Usuario logeado
        Cursor cursorPes = sqlite.rawQuery("SELECT MIN(Basculas.pesoUsuario), " +
                "MAX(Basculas.pesoUsuario), ROUND(AVG(Basculas.pesoUsuario),2) FROM Basculas, " +
                "Registros WHERE Registros.idBascula = Basculas.idBascula AND Registros.idUsuario " +
                "LIKE '" + idPro + "'" , null);

        cursorPes.moveToFirst();

        //TextViews Basculas
        TextView oloP[] = new TextView[3];

        oloP[0] = tvPesoPMin;
        oloP[1] = tvPesoPMax;
        oloP[2] = tvPesoPMedia;

        for (int i=0;i<oloP.length;i++) {

            if (cursorPes.getString(i) != null) {

                oloP[i].setText(cursorPes.getString(i).replace(".", ","));

            }
            else {  oloP[i].setText("N/D");  }

        }


        //Query que devuelve el numero total de Básculas para el Usuario logeado
        Cursor cursorBasNum = sqlite.rawQuery("SELECT Basculas.idbascula FROM Basculas, " +
                "Registros WHERE Registros.idBascula=Basculas.idbascula AND Registros.idUsuario " +
                "LIKE '" + idPro + "' GROUP BY Basculas.idbascula", null);

        int i = 0;
        if(cursorBasNum.getCount() != 0) {

            cursorBasNum.moveToFirst();

            //Contabilizamos el número de basculas que hay para el Usuario logeado
            do {
                i++;
            }
            while (cursorBasNum.moveToNext());
        }

        tvPesaCount.setText(String.valueOf(i));


        String imc=null, imcMin = null, imcMax = null, imcMedia = null;

        //Cursor para conseguir el imc medio de las Basculas
        Cursor cursorBas = sqlite.rawQuery("SELECT Basculas.pesoUsuario, Basculas.alturaUsuario " +
                "FROM Registros, Basculas WHERE Registros.idBascula = Basculas.idBascula AND " +
                "Registros.idUsuario LIKE '" + idPro + "'" , null);

        //Comprobamos si el cursor no es null
        if(cursorBas.getCount() != 0) {

            cursorBas.moveToFirst();

            //Estructura do-while
            do {
                bascula = new Bascula();
                bascula.setPesoUsuario(cursorBas.getFloat(0));
                bascula.setAlturaUsuario(cursorBas.getFloat(1));

                imc = bascula.imc();

                //Bucle if para imcMin
                if(imcMin!=null) {
                    if(Float.parseFloat(imcMin.replace(",","."))
                            >Float.parseFloat(imc.replace(",","."))) {

                        imcMin = imc;

                    }
                }//siempre que sea null adquiere el imc de esta iteración
                else {  imcMin = imc;  }

                //Bucle if para imcMax
                if(imcMax!=null) {
                    if(Float.parseFloat(imcMax.replace(",","."))
                            <Float.parseFloat(imc.replace(",","."))) {

                        imcMax = imc;

                    }
                }//siempre que sea null adquiere el imc de esta iteración
                else {  imcMax = imc;  }

                //Bucle if para imcMedia
                if (imcMedia!= null) {

                    //Se utiliza este método para poder controlar la salida del Float
                    imcMedia = calculoImcMed(imcMedia, imc);

                }//siempre que sea null adquiere el imc de esta iteración
                else {  imcMedia = imc;  }

            }
            while(cursorBas.moveToNext());

        }

        //Visualizamos el resultado de la velocidades medias en los EditText
        if(imcMin!=null) {
            tvImcPMin.setText(imcMin.replace(".",","));
        }
        else {  tvImcPMin.setText("N/D");  }

        if(imcMax!=null) {
            tvImcPMax.setText(imcMax.replace(".",","));
        }
        else {  tvImcPMax.setText("N/D");  }

        if(imcMedia!=null) {
            tvImcPMedia.setText(imcMedia.replace(".",","));
        }
        else {  tvImcPMedia.setText("N/D");  }


        //Cerramos cursores
        cursor.close();
        cursorEj.close();
        cursorPes.close();
        cursorBasNum.close();
        cursorBas.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

        return vistaPro;

    }

    //Método que modifica el pattern de salida de la velocidadMedia
    public String calculoVelMed(String velocidadMedia, String velocidad) {

        float velMed = Float.parseFloat(velocidadMedia.replace(",","."));
        float vel = Float.parseFloat(velocidad.replace(",","."));
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        String format;
        format = df.format((float) (velMed + vel) / 2);
        return format;

    }

    //Método que modifica el pattern de salida del imcMedio
    public String calculoImcMed(String imcMedia, String imc) {

        float imcMed = Float.parseFloat(imcMedia.replace(",","."));
        float im = Float.parseFloat(imc.replace(",","."));
        //hacemos el cálculo con un pattern de retorno con 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        String format;
        format = df.format((float) (imcMed + im) / 2);
        return format;

    }

}