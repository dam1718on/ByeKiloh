package com.example.byekiloh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.byekiloh.LoginEstructuraDatos.Estructura;

public class LoginBaseDatos extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura.TABLE_NAME + " (" +
                    Estructura._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Estructura.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_NAME_PASS + TEXT_TYPE + " )";
    //private static String user;
    /*private static final String SQL_READ_ENTRIES =
            "SELECT " + Estructura._ID + " FROM " +
                    Estructura.TABLE_NAME + " WHERE " +
                    Estructura.COLUMN_NAME_USER + " = " + user;*/

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Estructura.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "basebyekiloh.sqlite";

    /*public void onRead(SQLiteDatabase db, String user){
        this.user= user;
        db.execSQL(SQL_READ_ENTRIES);
    }*/

    public LoginBaseDatos(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    //Método para crear la Tabla que recibe la consulta Transact-SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //Método que elimina la tabla y vuelve a llamar al método que la crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}