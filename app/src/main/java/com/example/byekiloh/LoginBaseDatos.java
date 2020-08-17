package com.example.byekiloh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginBaseDatos extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LoginEstructuraDatos.Estructura.TABLE_NAME + " (" +
                    LoginEstructuraDatos.Estructura._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LoginEstructuraDatos.Estructura.COLUMN_NAME_USER + TEXT_TYPE + COMMA_SEP +
                    LoginEstructuraDatos.Estructura.COLUMN_NAME_PASS + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + LoginEstructuraDatos.Estructura.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AppBBDD.sqlite";

    public LoginBaseDatos(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}