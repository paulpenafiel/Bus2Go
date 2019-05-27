package com.example.usuario.proyectofinal;

/**
 * Created by Usuario on 10/08/2017.
 */

public class BusUso {
    String u_coperativa;
    String u_fechaUso;

    public BusUso(){}

    public BusUso(String coperativa, String fechaUso){
        this.u_coperativa=coperativa;
        this.u_fechaUso=fechaUso;
    }

    public String getU_coperativa() {
        return u_coperativa;
    }

    public String getU_fechaUso() {
        return u_fechaUso;
    }

    //SETTERS

    public void setU_coperativa(String u_coperativa) {
        this.u_coperativa = u_coperativa;
    }

    public void setU_fechaUso(String u_fechaUso) {
        this.u_fechaUso = u_fechaUso;
    }
}
