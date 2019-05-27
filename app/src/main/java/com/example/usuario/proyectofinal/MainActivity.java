package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText contra;
    private String cadena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= (EditText) findViewById(R.id.editText);
        contra= (EditText) findViewById(R.id.editText2);
    }


    //intent que dirige a actividad de inicio
    //public void irInicio(View view){
      //  Intent intent = new Intent(this,Inicio.class);
        //startActivity(intent);
    //}

    public void irRegistro(View view){
        Intent intent = new Intent(this,RegistroUsuario.class);
        startActivity(intent);
    }

    public void pedirIngreso(View view){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url3 ="http://10.0.2.2/usersProyecto.php";

        StringRequest stringRequest = new
                StringRequest(Request.Method.POST, url3,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                try {

                                    //mensaje=response;
                                    cadena=response;
                                    if (cadena.compareToIgnoreCase("")==0) {
                                        Toast.makeText(MainActivity.this,"USUARIO NO ES VALIDO",Toast.LENGTH_LONG).show();
                                    }
                                    else {

                                        Intent intent = new Intent(MainActivity.this, Inicio.class);
                                        startActivity(intent);
                                        Toast.makeText(MainActivity.this,"BIENVENIDO "+cadena,Toast.LENGTH_LONG).show();

                                    }

                                    //Log.d("My App", response.toString());
                                } catch (Throwable t) {
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("email", (username.getText()).toString());
                        params.put("password",(contra.getText()).toString());
                        return params;
                    }
                };
        queue.add(stringRequest);




        //MO PARAR BOLA
        ///////lanzamiento de la actividad

        //extras.putString("EXTRA_nombre",mensaje);
       // intent.putExtras(extras);


    }
}
