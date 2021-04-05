package com.example.byeKiloh.datapersistence;

import android.provider.BaseColumns;

public class Tablas {

    public Tablas() {    }

    public static abstract class EstructuraUsuario implements BaseColumns {

        public static final String TABLE_NAME = "Usuarios";
        public static final String _IDUSUARIO = "idUsuario";
        public static final String COLUMN_NAME_USUARIO = "usuario";
        public static final String COLUMN_NAME_CONTRASEÑA = "contraseña";

    }

    public static abstract class EstructuraCuenta implements BaseColumns {

        public static final String TABLE_NAME = "Cuentas";
        public static final String _IDCUENTA = "idCuenta";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_LOCALIDAD = "localidad";
        public static final String COLUMN_NAME_FECHANAC = "fechaNac";
        public static final String _IDUSUARIO = "idUsuario";

    }

    public static abstract class EstructuraEjercicio implements BaseColumns {

        public static final String TABLE_NAME = "Ejercicios";
        public static final String _IDEJERCICIO = "idEjercicio";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_DISTANCIA = "distancia";
        public static final String COLUMN_NAME_TIEMPO = "tiempo";
        public static final String COLUMN_NAME_VELOCIDAD = "velocidad";
        public static final String COLUMN_NAME_CONSUMOE = "consumoE";
        public static final String COLUMN_NAME_INCLINACION = "inclinacion";
        public static final String _IDUSUARIO = "idUsuario";

    }

    public static abstract class EstructuraPesaje implements BaseColumns {

        public static final String TABLE_NAME = "Pesajes";
        public static final String _IDPESAJE = "idPesaje";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_PESO = "peso";
        public static final String COLUMN_NAME_ALTURA = "altura";
        public static final String COLUMN_NAME_LUGAR = "lugar";
        public static final String COLUMN_NAME_IMC = "imc";
        public static final String COLUMN_NAME_CLASIFICACION = "clasificación";
        public static final String _IDUSUARIO = "idUsuario";

    }

}