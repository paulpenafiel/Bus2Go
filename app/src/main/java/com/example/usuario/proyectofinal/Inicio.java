package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Inicio extends AppCompatActivity {

    private DataBasaHandler ddb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        ddb= new DataBasaHandler(this.getBaseContext());
    }


    public void irDestino(View view){
        Intent intent = new Intent(this,UbicacionDestino.class);
        startActivity(intent);
    }

    public void consultLineas(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 ="http://10.0.2.2:8080/servicioWeb/rest/LineasSoftware/listar";

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new
                        Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    List<Linea> contactos= ddb.getAllContactos();
                                    for(Linea con:contactos){
                                    ddb.deleteLinea(con);
                                    }
                                    JSONObject jsonObj = new JSONObject(response.toString());
                                    JSONArray contacts = jsonObj.getJSONArray("Linea");
                                    for (int i = 0; i < contacts.length(); i++) {
                                        JSONObject c = contacts.getJSONObject(i);
                                        String id = c.getString("id");
                                        String name = c.getString("nombre");
                                        String ruta=c.getString("ruta");
                                        Linea cont= new Linea(id,name,ruta);
                                        ddb.addLinea(cont);
                                    }
                                }
                                catch (final JSONException e) {
                                }
                            }
                        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // mTextView.setText("That didn't work!");
                    }
                });
        queue.add(jsObjRequest);

        ///lanzamientp de la actividad
        Intent intent = new Intent(this,Consulta.class);
        startActivity(intent);
    }

    public void irCalificarServicio(View view) {
        Intent intent = new Intent(this, ListaUsos.class);
        startActivity(intent);
    }

}
