package com.example.oscarruizpatricio.practicaimc.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by oscarruizpatricio on 22/1/17.
 */

public class BaseDatosUsuario extends SQLiteOpenHelper {

    // String que contiene el código SQL para crear la tabla de Usuarios. Se utilizará en el método OnCreate.
    private static final String sqlCreacionTabla = "CREATE TABLE USUARIO (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT)";

    // Constructor de la clase, con los parámetros por defecto
    public BaseDatosUsuario (Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    // Método obligatorio que pide SQLiteOpenHelper, sirve para actualizar la BD.
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Método obligatorio que crea la BBDD, es llamado cuando se utiliza getReadableDatabase y getWritableDatabas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTabla);
    }

    //Método que será llamado cuando vayamos a cerrar la BD
    public void cerrarBaseDatos (SQLiteDatabase db) { db.close(); }

    //Método para dar de alta usuario
    public void crearUsuario (Usuario usuario) {
        Log.d("prueba", "entrando en crear usuario");
        SQLiteDatabase baseDatos = this.getWritableDatabase();
        Log.d("prueba", usuario.getUser());
        baseDatos.execSQL("INSERT INTO USUARIO (user, password) VALUES ('" + usuario.getUser()+"' , '" + usuario.getPassword()+"')");
        cerrarBaseDatos(baseDatos);
    }

    //Método para comprobar si el usuario existe en la BD, se utilizará para no duplicar nombres de usuario al crear uno nuevo
    //Si cursor existe y tiene al menos un registro, se mueve al primero. Luego comprueba si los nombres de usuario coinciden y pasa al siguiente
    public boolean comprobarUsuario (String nombreUsuario) {

        boolean existe = false;
        String usuario = "";
        String consulta = "SELECT * FROM USUARIO";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor != null && cursor.moveToNext()) {
            cursor.moveToFirst();

            do {
                usuario = cursor.getString(1);
                if (usuario.equals(nombreUsuario)) {
                    existe = true;
                }
                cursor.moveToNext();
            } while (cursor.moveToNext() == true);
        }
        cursor.close();
        cerrarBaseDatos(db);
        return existe;
    }

    //Función para hacer login
    //Comprueba si el usuario existe y si el password introducido coincide
    public boolean login (String usuario, String password) {

        boolean loginCorrecto;

        String user = "";
        String pass = "";
        String consulta = "SELECT user, password FROM USUARIO WHERE user = '"+usuario+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            user = cursor.getString(0);
            pass = cursor.getString(1);

            if (user.equals(usuario) && pass.equals(password)) {
                loginCorrecto = true;
            } else {
                loginCorrecto = false;
            }
            cursor.close();
        } else {
            loginCorrecto = false;
        }
        cerrarBaseDatos(db);
        return loginCorrecto;
    }

}
