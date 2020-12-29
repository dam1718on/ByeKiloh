package com.example.byeKiloh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EjercicioActivity extends AppCompatActivity {

    /*//private Button btnAnadirEjercicio, btnActualizar, btnBorrar, btnVolverE;
    //private EditText    etFecha, etDistancia, etTiempo, etInclinacion, etFecha2, etDistancia2,
            etTiempo2, etInclinacion2;
    //private Spinner spinEjercicios;
    //Esta variable permite comprobar los digitos de varios EditText a la vez
    //private int countError = 1;

    BaseDatos basedatos;
    Ejercicio ejercicio;
    Mensaje mensaje;
    Usuario usuario;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_ejercicio_activity);

        /*//btnAnadirEjercicio = findViewById(R.id.btnAnadirEjercicio);
        //btnActualizar = findViewById(R.id.btnActualizar);
        //btnBorrar = findViewById(R.id.btnBorrar);
        btnVolverE = findViewById(R.id.btnVolverAlMain2);

        //etFecha = findViewById(R.id.etFecha);
        //etDistancia = findViewById(R.id.etDistancia);
        //etTiempo = findViewById(R.id.etTiempo);
        //etInclinacion = findViewById(R.id.etInclinacion);
        //etFecha2 = findViewById(R.id.etFecha2);
        //etDistancia2 = findViewById(R.id.etDistancia2);
        //etTiempo2 = findViewById(R.id.etTiempo2);
        //etInclinacion2 = findViewById(R.id.etInclinacion2);

        //spinEjercicios = findViewById(R.id.spinEjercicios);

        //Recibimos el Usuario que logea a través del intent
        final Usuario usuarioMain = (Usuario) getIntent().getSerializableExtra("usuario");
        //Creo Usuario copia del Usuario recibido
        usuario = new Usuario(usuarioMain);

        basedatos = new BaseDatos(getApplicationContext());
        //Visualizamos el Spinner onCreate
        actualizarSpinner();

        spinEjercicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos objeto Ejercicio con el item seleccionado del Spinner
                ejercicio = new Ejercicio((Ejercicio) spinEjercicios.getSelectedItem());
                //Rellenamos los EditText con los datos del Ejercicio
                etFecha2.setText(ejercicio.getFecha());
                etDistancia2.setText(String.valueOf(ejercicio.getDistancia()));
                etTiempo2.setText(String.valueOf(ejercicio.getTiempo()));
                etInclinacion2.setText(ejercicio.getInclinacion());

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {    }

        });

        btnAnadirEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Primer if comprueba que no hay EditText vacíos
                if(etFecha.getText().toString().equals("") ||
                        etDistancia.getText().toString().equals("") ||
                        etFecha.getText().toString().equals("") ||
                        etInclinacion.getText().toString().equals("")) {

                    mensaje = new Mensaje(getApplicationContext(), "Revise los datos " +
                            "introducidos\ntodos los campos son obligatorios");

                } else {

                    countError=1;
                    //Comprobamos número mínimo de carácteres en cada EditText
                    numMinL(etFecha, 6, "Fecha");
                    numMinL(etDistancia, 3, "Distancia");
                    numMinL(etTiempo, 2, "Tiempo");
                    numMinL(etInclinacion, 1, "Inclinacion");
                    //Segundo if comprueba si se cumple con el mínimo de carácteres para cada EditText
                    if(countError == 1) {
                        //Se crea objeto Ejercicio con parámetros
                        ejercicio = new Ejercicio(etFecha.getText().toString(),
                                Integer.parseInt(etDistancia.getText().toString()),
                                Integer.parseInt(etTiempo.getText().toString()),
                                etInclinacion.getText().toString(),
                                usuario.getIdUsuario());
                        //Se ganan tambien permisos de escritura
                        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
                        //EstructuraEjercicio de insercción de datos
                        ContentValues content = new ContentValues();
                    /*Se añaden los valores introducidos de cada campo mediante clave(columna) /
                    valor(valor introducido en el campo de texto)*/
                        /*content.put(_IDUSUARIO, ejercicio.getIdUsuario());
                        content.put(COLUMN_NAME_FECHA, ejercicio.getFecha());
                        content.put(COLUMN_NAME_DISTANCIA, String.valueOf(ejercicio.getDistancia()));
                        content.put(COLUMN_NAME_TIEMPO, String.valueOf(ejercicio.getTiempo()));
                        content.put(COLUMN_NAME_VELOCIDAD, ejercicio.getVelocidad());
                        content.put(COLUMN_NAME_INCLINACION, ejercicio.getInclinacion());
                        sqlite.insert(TABLE_NAME, null, content);
                        //Mensaje de éxito al añadir
                        mensaje = new Mensaje(getApplicationContext(), "El Ejercicio ha sido " +
                                "almacenado");

                        vaciarEditText(1);
                        //Se cierra la conexión abierta a la Base de Datos
                        sqlite.close();

                        actualizarSpinner();

                    }

                }

            }

        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarEjercicio();

                vaciarEditText(1);

                actualizarSpinner();

            }

        });


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                borrarEjercicio();

                vaciarEditText(1);

                vaciarEditText(2);

                actualizarSpinner();

            }

        });

        btnVolverE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos intent para ir a .D_Main y le enviamos Usuario
                Intent intent = new Intent(getApplicationContext(), D_Main.class);
                intent.putExtra("usuario", usuarioMain);
                startActivity(intent);

            }

        });

    }

    //Método que vacía los EditText, si x vale 1 vacía los de Añadir y si vale 2 los de Seleccionado
    public void vaciarEditText(int x) {

        if(x==1) {

            etFecha.setText("");
            etDistancia.setText("");
            etTiempo.setText("");
            etInclinacion.setText("");

        }

        if(x==2) {

            etFecha2.setText("");
            etDistancia2.setText("");
            etTiempo2.setText("");
            etInclinacion2.setText("");

        }*/

    }

/*    //Método que cuenta el número de caracteres introducidos
    public void numMinL(EditText et, int minDig, String campo){
        //Contamos el tamaño del EditText introducido
        int num = et.length();
        //Comprobamos si el tamaño del EditText es inferior al minimo exigido
        if(num<minDig) {
            //Retorno de mensaje de error detallado
            mensaje = new Mensaje(getApplicationContext(), "'" + campo + "' tiene " + num +
                    " carácteres\ny el mínimo para ese campo son " + minDig);

            countError += 1;

        }

    }

    //Método que actualiza un ejercicio
    public void actualizarEjercicio() {
        //Creamos objeto Ejercicio con el item seleccionado del Spinner
        ejercicio = new Ejercicio((Ejercicio) spinEjercicios.getSelectedItem());
        //Visualizamos el objeto antes de ser modificado
        //verSelected.setText(ejercicio.toString());
        //Actualziamos el Ejercicio con los datos de los EditText si no están vacios
        if(!etFecha2.getText().toString().equals("")) {
            ejercicio.setFecha(etFecha2.getText().toString());
        }
        if(!etDistancia2.getText().toString().equals("")) {
            ejercicio.setDistancia(Integer.parseInt(etDistancia2.getText().toString()));
        }
        if(!etTiempo2.getText().toString().equals("")) {
            ejercicio.setTiempo(Integer.parseInt(etTiempo2.getText().toString()));
        }
        if(!etInclinacion2.getText().toString().equals("")) {
            ejercicio.setInclinacion(etInclinacion2.getText().toString());
        }
        ejercicio.setVelocidad();
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //EstructuraUsuario de insercción de datos
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME_FECHA, ejercicio.getFecha());
        content.put(COLUMN_NAME_DISTANCIA, String.valueOf(ejercicio.getDistancia()));
        content.put(COLUMN_NAME_TIEMPO, String.valueOf(ejercicio.getTiempo()));
        content.put(COLUMN_NAME_VELOCIDAD, ejercicio.getVelocidad());
        content.put(COLUMN_NAME_INCLINACION, ejercicio.getInclinacion());
        //Actualizamos el registro en la base de datos
        sqlite.update("C_Main_Ejercicios", content, "C_Main_Ejercicios.idEjercicio = '" +
                ejercicio.getIdEjercicio() + "'", null);
        //Mensaje de éxito al actualizar
        mensaje = new Mensaje(getApplicationContext(), "El Ejercicio ha sido actualizado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Vaciamos los EditText
        vaciarEditText(2);

    }

    //Método que borra el ejercicio seleccionado del Spinner
    public void borrarEjercicio() {
        //Creamos objeto Ejercicio con el item seleccionado del Spinner
        ejercicio = new Ejercicio((Ejercicio) spinEjercicios.getSelectedItem());
        //Se establece conexion con permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        //Sentencia que borra el ejercicio indicado
        sqlite.delete("C_Main_Ejercicios", "C_Main_Ejercicios.idEjercicio = '" +
                ejercicio.getIdEjercicio() + "'", null);
        //Mensaje de éxito al borrar
        mensaje = new Mensaje(getApplicationContext(), "El Ejercicio ha sido borrado");
        //Cerramos la conexión con la Base de Datos
        sqlite.close();

    }

    //Método que crea un ArrayList con los maincEjercicios en la Base de Datos
    public void actualizarSpinner() {
        //Creamos un ArrayList de maincEjercicios
        ArrayList<Ejercicio> ejerciciosAL = new ArrayList<>();
        //Se establece conexion con permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Query que devuelve todos los maincEjercicios del Usuario logeado
        Cursor cursor = sqlite.rawQuery("SELECT * FROM C_Main_Ejercicios WHERE C_Main_Ejercicios.idUsuario "
                + "LIKE '" + usuario.getIdUsuario() + "'", null);
        //Comprobamos si el cursor no es null
        if(cursor != null) {

            if(cursor.moveToFirst()) {
                //Bucle do-while, crea un ejercicio y lo incluye en el Array mientras haya registros
                do {

                    ejercicio = new Ejercicio();
                    ejercicio.setIdEjercicio(cursor.getInt(cursor.getColumnIndex(_IDEJERCICIO)));
                    ejercicio.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FECHA)));
                    ejercicio.setDistancia(Integer.parseInt(cursor.getString(cursor.getColumnIndex
                            (COLUMN_NAME_DISTANCIA))));
                    ejercicio.setTiempo(Integer.parseInt(cursor.getString(cursor.getColumnIndex
                            (COLUMN_NAME_TIEMPO))));
                    ejercicio.setVelocidad();
                    ejercicio.setInclinacion(cursor.getString(cursor.getColumnIndex
                            (COLUMN_NAME_INCLINACION)));
                    ejercicio.setIdUsuario(usuario.getIdUsuario());

                    ejerciciosAL.add(ejercicio);

                } while (cursor.moveToNext());

            }

        }
        //Cerramos el cursor
        cursor.close();
        //Cerramos la conexión con la Base de Datos
        sqlite.close();
        //Inflamos el Spinner con el ArrayList
        spinEjercicios.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.spinner_item_c, ejerciciosAL));
        //Seleccionamos el último registro del Array
        spinEjercicios.setSelection(ejerciciosAL.size()-1);

    }*/

}