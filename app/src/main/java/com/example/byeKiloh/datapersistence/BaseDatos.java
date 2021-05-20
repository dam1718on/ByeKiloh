package com.example.byeKiloh.datapersistence;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.byeKiloh.datapersistence.Tablas.*;

public class BaseDatos extends SQLiteOpenHelper {

    //Se declaran e inicializan las variables encargadas de almacenar las consultas para crear las
    // tablas, y las consultas de eliminar/crear la Base de Datos 'byeKiloh.sqlite'.
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_ENTRIES_USUARIO =
            "CREATE TABLE " + EstructuraUsuario.TABLE_NAME + " (" +
                    EstructuraUsuario._IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraUsuario.COLUMN_NAME_ALIASUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraUsuario.COLUMN_NAME_CONTRASENIA + TEXT_TYPE + ")";

    private static final String SQL_CREATE_ENTRIES_CUENTA =
            "CREATE TABLE " + EstructuraCuenta.TABLE_NAME + " (" +
                    EstructuraCuenta._IDCUENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraCuenta.COLUMN_NAME_NUMEROTELEFONO + " INTEGER" + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_VALIDADO + " BOOLEAN" + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_NOMBREUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta.COLUMN_NAME_DIRECCIONUSUARIO + TEXT_TYPE + COMMA_SEP +
                    EstructuraCuenta._IDUSUARIO + " INTEGER," +
                    " FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario) ON DELETE CASCADE)";

    private static final String SQL_CREATE_ENTRIES_COPIADESEGURIDAD =
            "CREATE TABLE " + EstructuraCopiadeSeguridad.TABLE_NAME + " (" +
                    EstructuraCopiadeSeguridad._IDCOPIADESEGURIDAD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraCopiadeSeguridad.COLUMN_NAME_FECHASUBIDA + " TIMESTAMP" + COMMA_SEP +
                    EstructuraCopiadeSeguridad.COLUMN_NAME_ARCHIVOSQLITE + " FILE" + COMMA_SEP +
                    EstructuraCopiadeSeguridad._IDCUENTA + " INTEGER," +
                    " FOREIGN KEY (idCuenta) REFERENCES Cuentas(idCuenta) ON DELETE CASCADE)";

    private static final String SQL_CREATE_ENTRIES_LOGROSDECUENTA =
            "CREATE TABLE " + EstructuraLogrosdeCuenta.TABLE_NAME + " (" +
                    EstructuraLogrosdeCuenta._IDLOGRODECUENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraLogrosdeCuenta.COLUMN_NAME_FECHALOGRO + " TIMESTAMP" + COMMA_SEP +
                    EstructuraLogrosdeCuenta._IDLOGRO + " INTEGER," +
                    EstructuraLogrosdeCuenta._IDCUENTA + " INTEGER," +
                    " FOREIGN KEY (idLogro) REFERENCES Logros(idLogro)," +
                    " FOREIGN KEY (idCuenta) REFERENCES Cuentas(idCuenta) ON DELETE CASCADE)";

    private static final String SQL_CREATE_ENTRIES_LOGRO =
            "CREATE TABLE " + EstructuraLogro.TABLE_NAME + " (" +
                    EstructuraLogro._IDLOGRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraLogro.COLUMN_NAME_NOMBRELOGRO + TEXT_TYPE + COMMA_SEP +
                    EstructuraLogro.COLUMN_NAME_DESCRIPCIONLOGRO + TEXT_TYPE + ")";

    private static final String SQL_CREATE_ENTRIES_REGISTRO =
            "CREATE TABLE " + EstructuraRegistro.TABLE_NAME + " (" +
                    EstructuraRegistro._IDREGISTRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraRegistro.COLUMN_NAME_FECHAREGISTRO + " TIMESTAMP" + COMMA_SEP +
                    EstructuraRegistro._IDUSUARIO + " INTEGER" + COMMA_SEP +
                    EstructuraRegistro._IDBASCULA + " INTEGER" + COMMA_SEP +
                    EstructuraRegistro._IDEJERCICIO + " INTEGER," +
                    " FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario) ON DELETE CASCADE," +
                    " FOREIGN KEY (idBascula) REFERENCES Basculas(idBascula) ON DELETE CASCADE," +
                    " FOREIGN KEY (idEjercicio) REFERENCES Ejercicios(idEjercicio))";

    private static final String SQL_CREATE_ENTRIES_BASCULA =
            "CREATE TABLE " + EstructuraBascula.TABLE_NAME + " (" +
                    EstructuraBascula._IDBASCULA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraBascula.COLUMN_NAME_PESOUSUARIO + " DECIMAL" + COMMA_SEP +
                    EstructuraBascula.COLUMN_NAME_ALTURAUSUARIO + " DECIMAL" + COMMA_SEP +
                    EstructuraBascula.COLUMN_NAME_LUGARBASCULA + TEXT_TYPE + COMMA_SEP +
                    EstructuraBascula._IDREGISTRO + " INTEGER," +
                    " FOREIGN KEY (idRegistro) REFERENCES Registros(idRegistro))";

    private static final String SQL_CREATE_ENTRIES_EJERCICIO =
            "CREATE TABLE " + EstructuraEjercicio.TABLE_NAME + " (" +
                    EstructuraEjercicio._IDEJERCICIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraEjercicio.COLUMN_NAME_DISTANCIARECORRIDA + " INTEGER" + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_TIEMPOEMPLEADO + " INTEGER" + COMMA_SEP +
                    EstructuraEjercicio.COLUMN_NAME_INCLINACIONTERRENO + " DECIMAL)";


    private static final String SQL_DELETE_ENTRIES_USUARIO = "DROP TABLE IF EXISTS " +
        EstructuraUsuario.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_CUENTA = "DROP TABLE IF EXISTS " +
        EstructuraCuenta.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_COPIADESEGURIDAD = "DROP TABLE IF EXISTS " +
        EstructuraCopiadeSeguridad.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_LOGROSDECUENTA = "DROP TABLE IF EXISTS " +
            EstructuraLogrosdeCuenta.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_LOGRO = "DROP TABLE IF EXISTS " +
            EstructuraLogro.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_REGISTRO = "DROP TABLE IF EXISTS " +
        EstructuraRegistro.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_BASCULA = "DROP TABLE IF EXISTS " +
        EstructuraBascula.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_EJERCICIO = "DROP TABLE IF EXISTS " +
        EstructuraEjercicio.TABLE_NAME;


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
        db.execSQL(SQL_CREATE_ENTRIES_COPIADESEGURIDAD);
        db.execSQL(SQL_CREATE_ENTRIES_LOGROSDECUENTA);
        db.execSQL(SQL_CREATE_ENTRIES_LOGRO);
        db.execSQL(SQL_CREATE_ENTRIES_REGISTRO);
        db.execSQL(SQL_CREATE_ENTRIES_BASCULA);
        db.execSQL(SQL_CREATE_ENTRIES_EJERCICIO);

    }

    //Método que actualiza las tablas, las borra y despues llama al método que las crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES_USUARIO);
        db.execSQL(SQL_DELETE_ENTRIES_CUENTA);
        db.execSQL(SQL_DELETE_ENTRIES_COPIADESEGURIDAD);
        db.execSQL(SQL_DELETE_ENTRIES_LOGROSDECUENTA);
        db.execSQL(SQL_DELETE_ENTRIES_LOGRO);
        db.execSQL(SQL_DELETE_ENTRIES_REGISTRO);
        db.execSQL(SQL_DELETE_ENTRIES_BASCULA);
        db.execSQL(SQL_DELETE_ENTRIES_EJERCICIO);
        onCreate(db);

    }

}