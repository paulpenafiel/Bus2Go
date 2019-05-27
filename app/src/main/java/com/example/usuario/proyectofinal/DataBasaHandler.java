package com.example.usuario.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 23/07/2017.
 */

public class DataBasaHandler extends SQLiteOpenHelper {

    private static final int version =1;
    //Nombre de la base de datos
    private static final String nombre = "DBLinea";
    //Nombre de la tabla de Lineas
    private static final String tabla1 = "Lineas";
    private static SQLiteDatabase.CursorFactory factory = null;

    public DataBasaHandler(Context context){
        super(context, nombre, factory, version);
    }
    //Nombres de las columnas
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "c_nombre";
    private static final String KEY_RUTA = "c_ruta";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(),"Creando la base de datos");
        db.execSQL( "CREATE TABLE Lineas(" +
                " _id TEXT PRIMARY KEY," +
                " c_nombre TEXT NOT NULL, c_ruta TEXT NOT NULL)" );
        Log.i(this.getClass().toString(), "tabla lineas creada");
        Log.i(this.getClass().toString(), "Datos iniciales  insertados");
        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Lineas");
        onCreate(db);
    }

    //Añadir Lineas de buses
    public void addLinea(Linea linea){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_ID,linea.get_id());
        values.put(KEY_NAME,linea.get_nombre());
        values.put(KEY_RUTA,linea.get_ruta());
        db.insert("Lineas",null,values);
        db.close();
    }

    //Eliminar Lineas de Buses
    public void deleteLinea(Linea linea){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Lineas", KEY_ID + " =?",
                new String[] {String.valueOf(linea.get_id())});
        db.close();
    }

    public List<Linea> getAllContactos(){
        List<Linea> LineaList = new ArrayList<Linea>();
        String sql_select = "SELECT * FROM " + "Lineas";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Linea linea = new Linea();
                linea.set_id((cursor.getString(0)));
                //contact.setID(cursor.getInt(0));
                linea.set_nombre(cursor.getString(1));
                linea.set_ruta(cursor.getString(2));
                LineaList.add(linea);
            }
        }
        return LineaList;
    }

        public Linea getLinea(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(tabla1, new
                String[]{KEY_ID,KEY_NAME}, KEY_ID+"=?", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
            Linea caso = new Linea(cursor.getString(0),
                cursor.getString(1),cursor.getString(2));
        return caso;
    }

    public Linea getLineaporNombre(String nombre){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(tabla1, new
                String[]{KEY_ID,KEY_NAME,KEY_RUTA}, KEY_NAME+"=?", new
                String[]{String.valueOf(nombre)},null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Linea linea = new Linea(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        return linea;
    }
}
