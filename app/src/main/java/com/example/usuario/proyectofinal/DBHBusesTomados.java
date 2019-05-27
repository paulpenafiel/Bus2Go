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
 * Created by Usuario on 10/08/2017.
 */

public class DBHBusesTomados extends SQLiteOpenHelper {

    private static final int version =2;
    //Nombre de la base de datos
    private static final String nombre = "DBUso";
    //Nombre de la tabla de Usos
    private static final String tabla1 = "Usos";
    private static SQLiteDatabase.CursorFactory factory = null;

    public DBHBusesTomados(Context context){
        super(context, nombre, factory, version);
    }
    //Nombres de las columnas
    private static final String KEY_ID ="u_id";
    private static final String KEY_NAME = "u_coperativa";
    private static final String KEY_FECHA = "u_fechaUso";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(),"Creando la base de datos");
        db.execSQL( "CREATE TABLE Usos(" +
                " u_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " u_coperativa TEXT NOT NULL, u_fechaUso TEXT NOT NULL)" );
        Log.i(this.getClass().toString(), "tabla Usos creada");
        Log.i(this.getClass().toString(), "Datos iniciales  insertados");
        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usos");
        onCreate(db);
    }

    //añadir Usos
    public void addUso(BusUso uso){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        //values.put(KEY_ID,linea.get_id());
        values.put(KEY_NAME,uso.getU_coperativa());
        values.put(KEY_FECHA,uso.getU_fechaUso());
        db.insert("Usos",null,values);
        db.close();
    }

    //ELIMINAR USOS

    public void deleteUso(BusUso uso, String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Usos", KEY_ID + " =?",
                new String[] {String.valueOf(id)});
        db.close();
    }

    //LISTAR BUSES
    public List<BusUso> getAllUsos(){
        List<BusUso> UsosList = new ArrayList<BusUso>();
        String sql_select = "SELECT * FROM " + "Usos";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                BusUso uso = new BusUso();
                uso.setU_coperativa((cursor.getString(1)));
                //contact.setID(cursor.getInt(0));
                uso.setU_fechaUso(cursor.getString(2));
                UsosList.add(uso);
            }
        }
        return UsosList;
    }

    //OBTENER LINEA
    public BusUso getUsoporFecha(String fecha){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(tabla1, new
                String[]{KEY_ID,KEY_NAME,KEY_FECHA}, KEY_FECHA+"=?", new
                String[]{String.valueOf(fecha)},null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        BusUso uso = new BusUso(cursor.getString(1), cursor.getString(2));
        return uso;
    }
}
