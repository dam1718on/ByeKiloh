package com.example.byeKiloh.datapersistence;

import android.provider.BaseColumns;

public class Tablas {

    public Tablas() {    }

    public static abstract class EstructuraUsuario implements BaseColumns {

        public static final String TABLE_NAME = "Usuarios";
        public static final String _IDUSUARIO = "idUsuario";
        public static final String COLUMN_NAME_ALIASUSUARIO = "aliasUsuario";
        public static final String COLUMN_NAME_CONTRASENIA = "contrasenia";

    }

    public static abstract class EstructuraCuenta implements BaseColumns {

        public static final String TABLE_NAME = "Cuentas";
        public static final String _IDCUENTA = "idCuenta";
        public static final String COLUMN_NAME_NUMEROTELEFONO = "numeroTelefono";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_VALIDADO = "validado";
        public static final String COLUMN_NAME_NOMBREUSUARIO = "nombreUsuario";
        public static final String COLUMN_NAME_DIRECCIONUSUARIO = "direccionUsuario";
        public static final String _IDUSUARIO = "idUsuario";

    }

    public static abstract class EstructuraCopiadeSeguridad implements BaseColumns {

        public static final String TABLE_NAME = "CopiasdeSeguridad";
        public static final String _IDCOPIADESEGURIDAD = "idCopiadeSeguridad";
        public static final String COLUMN_NAME_FECHASUBIDA = "fechaSubida";
        public static final String COLUMN_NAME_PATHCOPIA = "pathCopia";
        public static final String _IDCUENTA = "idCuenta";

    }

    public static abstract class EstructuraLogrosdeCuenta implements BaseColumns {

        public static final String TABLE_NAME = "LogrosdeCuentas";
        public static final String _IDLOGRODECUENTA = "idLogrodeCuenta";
        public static final String COLUMN_NAME_FECHALOGRO = "fechaLogro";
        public static final String _IDLOGRO = "idLogro";
        public static final String _IDCUENTA = "idCuenta";

    }

    public static abstract class EstructuraLogro implements BaseColumns {

        public static final String TABLE_NAME = "Logros";
        public static final String _IDLOGRO = "idLogro";
        public static final String COLUMN_NAME_NOMBRELOGRO = "nombreLogro";
        public static final String COLUMN_NAME_DESCRIPCIONLOGRO = "descripcionLogro";

    }

    public static abstract class EstructuraRegistro implements BaseColumns {

        public static final String TABLE_NAME = "Registros";
        public static final String _IDREGISTRO = "idRegistro";
        public static final String COLUMN_NAME_FECHAREGISTRO = "fechaRegistro";
        public static final String _IDUSUARIO = "idUsuario";
        public static final String _IDBASCULA = "idBascula";
        public static final String _IDEJERCICIO = "idEjercicio";

    }

    public static abstract class EstructuraBascula implements BaseColumns {

        public static final String TABLE_NAME = "Basculas";
        public static final String _IDBASCULA = "idBascula";
        public static final String COLUMN_NAME_PESOUSUARIO = "pesoUsuario";
        public static final String COLUMN_NAME_ALTURAUSUARIO = "alturaUsuario";
        public static final String COLUMN_NAME_LUGARBASCULA = "lugarBascula";
        public static final String _IDREGISTRO = "idRegistro";

    }

    public static abstract class EstructuraEjercicio implements BaseColumns {

        public static final String TABLE_NAME = "Ejercicios";
        public static final String _IDEJERCICIO = "idEjercicio";
        public static final String COLUMN_NAME_DISTANCIARECORRIDA = "distanciaRecorrida";
        public static final String COLUMN_NAME_TIEMPOEMPLEADO = "tiempoEmpleado";
        public static final String COLUMN_NAME_INCLINACIONTERRENO = "inclinacionTerreno";

    }

}