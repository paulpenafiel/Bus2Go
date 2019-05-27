package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("EXTRA_id");
        String nom = extras.getString("EXTRA_nom");
        String ruta = extras.getString("EXTRA_rut");

        TextView textView = (TextView) findViewById(R.id.textView13);
        textView.setText(id);
        TextView textView1 = (TextView) findViewById(R.id.textView14);
        textView1.setText(nom);
        TextView textView2 = (TextView) findViewById(R.id.textView16);
        textView2.setText(ruta);
    }
}
