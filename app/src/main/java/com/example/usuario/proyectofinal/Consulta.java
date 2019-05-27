package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Consulta extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<String> ListViewItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        DataBasaHandler db = new DataBasaHandler(this);
        List<Linea> contactos = db.getAllContactos();
        lstView = (ListView) findViewById(R.id.listaContactos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListViewItems);
        lstView.setAdapter(adapter);
        lstView.setBackgroundColor(Color.WHITE);
        for(Linea cn:contactos) {
            adapter.add(cn.get_nombre());
            //lstView.setBackgroundColor(-1);
        }
        adapter.notifyDataSetChanged();
        //ACCION REFERENTE AL ITEM CLICADO
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getChildAt(position).setBackgroundColor(Color.GRAY);
                String clave = ListViewItems.get(position);
                DataBasaHandler db =new DataBasaHandler(Consulta.this);
                // Buscando Linea por NOMBRE
                Linea linea = db.getLineaporNombre(clave);
                String messageid= ""+linea.get_id();
                String messagenombre= ""+linea.get_nombre();
                String messageRut= ""+linea.get_ruta();

                Bundle extras = new Bundle();
                extras.putString("EXTRA_id",messageid);
                extras.putString("EXTRA_nom",messagenombre);
                extras.putString("EXTRA_rut",messageRut);
                Intent intent = new Intent(Consulta.this, Resultado.class);
                //intent.putExtra(EXTRA_MESSAGE,message);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
