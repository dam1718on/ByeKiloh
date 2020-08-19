package com.example.byekiloh;

import android.provider.BaseColumns;

public class FirstEstructuraDatos {

    public FirstEstructuraDatos() {  }

    public static abstract class Estructura implements BaseColumns {
        public static final String TABLE_NAME = "Ejercicios";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_DISTANCIA = "distancia";
        public static final String COLUMN_NAME_TIEMPO = "tiempo";
    }

}