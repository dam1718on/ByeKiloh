package com.example.byeKiloh.datapersistence;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.byeKiloh.datapersistence.Tablas.*;

public class BaseDatos extends SQLiteOpenHelper {

    //Se declaran e inicializan las variables encargadas de almacenar las consultas para crear las
    // tablas, y las consultas de eliminar/crear la Base de Datos 'byeKiloh.sqlite'.
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES_USUARIO =
            "CREATE TABLE " + EstructuraUsuario.TABLE_NAME + " (" +
                    EstructuraUsuario._IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraUsuario.COLUMN_NAME_ALIASUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraUsuario.COLUMN_NAME_CONTRASENIA + TEXT_TYPE + " )";

    private static final String SQL_CREATE_ENTRIES_CUENTA =
            "CREATE TABLE " + EstructuraCuenta.TABLE_NAME + " (" +
                    EstructuraCuenta._IDCUENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraCuenta.COLUMN_NAME_NUMEROTELEFONO + " INTEGER " + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_VALIDADO + " BOOLEAN " + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_NOMBREUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_DIRECCIONUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_NUMEROESTRELLAS + " INTEGER " + COMMA_SEP +
                    EstructuraCuenta._IDUSUARIO + " INTEGER  )";

    private static final String SQL_CREATE_ENTRIES_EJERCICIO =
            "CREATE TABLE " + EstructuraEjercicio.TABLE_NAME + " (" +
                    EstructuraEjercicio._IDEJERCICIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraEjercicio.COLUMN_NAME_FECHA + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_DISTANCIA + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_TIEMPO + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_VELOCIDAD + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_CONSUMOE + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_INCLINACION + TEXT_TYPE + COMMA_SEP +
                    EstructuraEjercicio._IDUSUARIO + " INTEGER  )";

    private static final String SQL_CREATE_ENTRIES_PESAJE =
            "CREATE TABLE " + EstructuraPesaje.TABLE_NAME + " (" +
                    EstructuraPesaje._IDPESAJE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraPesaje.COLUMN_NAME_FECHA + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje.COLUMN_NAME_PESO + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje.COLUMN_NAME_ALTURA + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje.COLUMN_NAME_LUGAR + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje.COLUMN_NAME_IMC + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje.COLUMN_NAME_CLASIFICACION + TEXT_TYPE + COMMA_SEP +
                    EstructuraPesaje._IDUSUARIO + " INTEGER  )";

    private static final String SQL_DELETE_ENTRIES_USUARIO = "DROP TABLE IF EXISTS " +
        EstructuraUsuario.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_CUENTA = "DROP TABLE IF EXISTS " +
        EstructuraCuenta.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_EJERCICIO = "DROP TABLE IF EXISTS " +
        EstructuraEjercicio.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_PESAJE = "DROP TABLE IF EXISTS " +
        EstructuraPesaje.TABLE_NAME;

    //Se declaran e inicializan las variables encargadas de crear la Base de Datos 'byeKiloh.sqlite'
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "byeKiloh.sqlite";

    public BaseDatos(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Método para crear las Tablas que recibe las consultas Transact-SQL
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES_USUARIO);
        db.execSQL(SQL_CREATE_ENTRIES_CUENTA);
        db.execSQL(SQL_CREATE_ENTRIES_EJERCICIO);
        db.execSQL(SQL_CREATE_ENTRIES_PESAJE);

    }

    //Método que actualiza las tablas, las borra y despues llama al método que las crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES_USUARIO);
        db.execSQL(SQL_DELETE_ENTRIES_CUENTA);
        db.execSQL(SQL_DELETE_ENTRIES_EJERCICIO);
        db.execSQL(SQL_DELETE_ENTRIES_PESAJE);
        onCreate(db);

    }

}