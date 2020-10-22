package com.example.byekiloh.utilidades;

import android.provider.BaseColumns;

public class Tablas {

    public Tablas() {    }

    public static abstract class EstructuraUsuario implements BaseColumns {

        public static final String TABLE_NAME = "Usuarios";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_NAME = "usuario";
        public static final String COLUMN_NAME_PASS = "contraseña";

    }

    public static abstract class EstructuraCuenta implements BaseColumns {

        public static final String TABLE_NAME = "Cuentas";
        public static final String _IDUSER = "idUsuario";
        public static final String COLUMN_NAME_SEXO = "genero";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_LOCALIDAD = "localidad";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_FECHANAC = "fechaNac";

    }

    public static abstract class EstructuraEjercicio implements BaseColumns {

        public static final String TABLE_NAME = "Ejercicios";
        public static final String _IDUSER = "idUsuario";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_DISTANCIA = "distancia";
        public static final String COLUMN_NAME_TIEMPO = "tiempo";
        public static final String COLUMN_NAME_VELOCIDAD = "velocidad";

    }

}