package com.example.usuario.proyectofinal;

/**
 * Created by Usuario on 23/07/2017.
 */

public class Linea {
    String _id;
    String _nombre;
    String _ruta;

    public Linea(){}

    public Linea(String id, String nombre, String ruta){
        this._id=id;
        this._nombre=nombre;
        this._ruta=ruta;
    }

    //GETTERS
    public String get_id() {
        return _id;
    }

    public String get_nombre() {
        return _nombre;
    }


    //SETTERS

    public void set_id(String _id) {
        this._id = _id;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_ruta() {
        return _ruta;
    }

    public void set_ruta(String _ruta) {
        this._ruta = _ruta;
    }
}
