package com.example.byekiloh;

import android.provider.BaseColumns;

public class LoginEstructuraDatos {

    public LoginEstructuraDatos() {    }

    public static abstract class Estructura implements BaseColumns {
        public static final String TABLE_NAME = "Usuarios";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_PASS = "pass";
    }

}