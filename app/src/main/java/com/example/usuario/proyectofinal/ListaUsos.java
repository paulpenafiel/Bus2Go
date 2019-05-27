package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListaUsos extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<String> ListViewItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usos);
        DBHBusesTomados db = new DBHBusesTomados(this);
        List<BusUso> usos = db.getAllUsos();
        lstView = (ListView) findViewById(R.id.listaUsos);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListViewItems);
        lstView.setAdapter(adapter);
        lstView.setBackgroundColor(Color.WHITE);
        for(BusUso cn:usos) {
            adapter.add(cn.getU_fechaUso());
            //lstView.setBackgroundColor(-1);
        }
        adapter.notifyDataSetChanged();
        //ACCION REFERENTE AL ITEM CLICADO
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getChildAt(position).setBackgroundColor(Color.GRAY);
                String clave = ListViewItems.get(position);
                DBHBusesTomados db =new DBHBusesTomados(ListaUsos.this);
                // Log.d("READING","BUSCANDO CONTACTO");
                BusUso uso = db.getUsoporFecha(clave);
                String coperativa= ""+uso.getU_coperativa();
                String fecha=""+uso.getU_fechaUso();
                Bundle extras = new Bundle();
                extras.putString("EXTRA_cop",coperativa);
                extras.putString("EXTRA_fecha",fecha);
                Intent intent = new Intent(ListaUsos.this, CServicio.class);
                intent.putExtra(EXTRA_MESSAGE,message);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
