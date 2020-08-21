package com.example.byekiloh;

import android.provider.BaseColumns;

public class LoginEstructuraDatos {

    public LoginEstructuraDatos() {    }

    public static abstract class Estructura implements BaseColumns {
        //nombre, direccion, localidad, email, fechaNac, nomUser, contraseña
        public static final String TABLE_NAME = "Usuarios";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_NAME = "nombre";
        public static final String COLUMN_NAME_PASS = "contraseña";
    }

}