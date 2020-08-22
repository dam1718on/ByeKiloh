package com.example.byekiloh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.byekiloh.LoginEstructuraDatos.*;

public class LoginBaseDatos extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraUsuario.TABLE_NAME + " (" +
                    EstructuraUsuario._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraUsuario.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    EstructuraUsuario.COLUMN_NAME_PASS + TEXT_TYPE + " )";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + EstructuraCuenta.TABLE_NAME + " (" +
                    EstructuraCuenta._IDUSER + " INTEGER," +
                    EstructuraCuenta.COLUMN_NAME_SEXO + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_DIRECCION + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_LOCALIDAD + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_FECHANAC + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + EstructuraUsuario.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "alphaFinalizada.sqlite";

    public LoginBaseDatos(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    //Método para crear la Tabla que recibe la consulta Transact-SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    //Método que elimina la tabla y vuelve a llamar al método que la crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}