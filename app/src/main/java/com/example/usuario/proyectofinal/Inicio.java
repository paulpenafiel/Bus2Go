package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void irLineas(View view){
        Intent intent = new Intent(this,Listalineas.class);
        startActivity(intent);
    }

    public void irDestino(View view){
        Intent intent = new Intent(this,UbicacionDestino.class);
        startActivity(intent);
    }

}
