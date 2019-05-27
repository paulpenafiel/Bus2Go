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

public class RegistroUsuario extends AppCompatActivity {

    private EditText email;
    private EditText contra;
    private EditText nombre;
    private EditText confir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nombre= (EditText) findViewById(R.id.editTextNOMBRE);
        email= (EditText) findViewById(R.id.editTextEMAIL);
        contra= (EditText) findViewById(R.id.editTextPASS);
        confir=(EditText) findViewById(R.id.editTextPASSC);
    }

    public void RegistrarNuevo(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url3 = "http://10.0.2.2/newuserProyecto.php";
        StringRequest stringRequest = new
                StringRequest(Request.Method.POST, url3,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {

                                    String query = new String(response);

                                    Log.d("My App", query.toString());
                                    Toast.makeText(RegistroUsuario.this, "USUARIO CREADO CON EXITO", Toast.LENGTH_SHORT).show();
                                } catch (Throwable t) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegistroUsuario.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", (nombre.getText()).toString());
                        params.put("email", (email.getText()).toString());
                        params.put("password", (contra.getText()).toString());
                        return params;
                    }
                };
        queue.add(stringRequest);
        Toast.makeText(this, "USUARIO CREADO CON EXITO", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
