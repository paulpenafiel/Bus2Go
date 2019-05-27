package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoBus extends AppCompatActivity {

    private TextView mTextView;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;


    String coperativa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bus);
        mTextView = (TextView) findViewById(R.id.textView15);
        mTextView1 = (TextView) findViewById(R.id.textView20);
        mTextView2 = (TextView) findViewById(R.id.textView24);
        mTextView3 = (TextView) findViewById(R.id.textView25);
        mTextView4 = (TextView) findViewById(R.id.textView26);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        coperativa = extras.getString("EXTRA_URL");
        //mTextView.setText(coperativa);


        ///////////////////
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url2 ="https://maps.googleapis.com/maps/api/directions/json?origin=estadioolimpicoatahualpa&destination=universidadcentraldelecuador&mode=transit&key=AIzaSyA9mnKY8qy5qT2iSUDXjzjfPokiAOnDlfU";
        String url2=coperativa;
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new
                        Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // List<Contacto>contactos= ddb.getAllContactos();
                                    //for(Contacto con:contactos){
                                    //  ddb.deleteContacto(con);
                                    //}
                                    JSONObject jsonData = new JSONObject(response.toString());
                                    JSONArray jsonRoutes = jsonData.getJSONArray("routes");
                                    JSONObject route = jsonRoutes.getJSONObject(0);
                                    JSONArray legs = route.getJSONArray("legs");
                                    JSONObject leg = legs.getJSONObject(0);
                                    JSONArray steps = leg.getJSONArray("steps");
                                    JSONObject step = steps.getJSONObject(1);
                                    JSONObject td = step.getJSONObject("transit_details");
                                    JSONObject depstop = td.getJSONObject("departure_stop");
                                    JSONObject arrstop = td.getJSONObject("arrival_stop");
                                    JSONObject LINEA = td.getJSONObject("line");
                                    JSONObject tiempoParada = td.getJSONObject("departure_time");
                                    JSONArray agencias = LINEA.getJSONArray("agencies");
                                    JSONObject agencia = agencias.getJSONObject(0);
                                    mTextView.setText(LINEA.getString("name"));
                                    mTextView1.setText(agencia.getString("name"));
                                    mTextView2.setText(depstop.getString("name"));
                                    mTextView3.setText(arrstop.getString("name"));
                                    mTextView4.setText(tiempoParada.getString("text"));
                                }
                                catch (final JSONException e) {
                                }
                            }
                        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
        queue.add(jsObjRequest);
    }

    /////////////////////////////////
    //public void consultINFOBUS(View view){

    //}

    public void registrarUsos(View view){


        String coperativa = mTextView1.getText().toString();

        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String fechahora =hourdateFormat.format(date);
        //validacion para el ingreso de datos
        if(coperativa.length() > 0 && fechahora.length() > 0 ){
            //Llamada al manejador de la base de datos
            DBHBusesTomados ddb = new DBHBusesTomados(this.getBaseContext());
            ddb.addUso(new BusUso(coperativa,fechahora));
            Toast.makeText(this, "registrados con éxito", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
