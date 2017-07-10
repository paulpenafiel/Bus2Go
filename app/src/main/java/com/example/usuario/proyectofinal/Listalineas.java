package com.example.usuario.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Listalineas extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<String> ListViewItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listalineas);
        lstView = (ListView) findViewById(R.id.listaLineas);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListViewItems);
        lstView.setAdapter(adapter);
        adapter.add("Tans. Alfa");
        adapter.add("Latinos");
        adapter.add("Metrotrans");
        adapter.notifyDataSetChanged();
    }
}
