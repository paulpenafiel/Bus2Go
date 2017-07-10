package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //intent que dirige a actividad de inicio
    public void irInicio(View view){
        Intent intent = new Intent(this,Inicio.class);
        startActivity(intent);
    }
}
