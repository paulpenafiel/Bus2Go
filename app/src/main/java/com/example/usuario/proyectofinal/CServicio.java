package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CServicio extends AppCompatActivity {

    TextView textViewcop;
    TextView textViewfecha;
    private RadioGroup radioGroupT;
    private RadioGroup radioGroupR;
    private RadioGroup radioGroupL;
    private RadioGroup radioGroupV;
    private RadioButton radioButtonT;
    private RadioButton radioButtonR;
    private RadioButton radioButtonL;
    private RadioButton radioButtonV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cservicio);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String cop = extras.getString("EXTRA_cop");
        String fecha = extras.getString("EXTRA_fecha");
        textViewcop = (TextView) findViewById(R.id.TextViewCop);
        textViewcop.setText(cop);
        textViewfecha = (TextView) findViewById(R.id.TextViewFecha);
        textViewfecha.setText(fecha);
        radioGroupT = (RadioGroup) findViewById(R.id.radioGrouptrato);
        radioGroupR = (RadioGroup) findViewById(R.id.radioGroupparadas);
        radioGroupL = (RadioGroup) findViewById(R.id.radioGrouplimpieza);
        radioGroupV = (RadioGroup) findViewById(R.id.radioGroupvelocidad);

    }

    public void RegistrarCalificacion(View view){

        int selectedIdTrato = radioGroupT.getCheckedRadioButtonId();
        int selectedIdParadas = radioGroupR.getCheckedRadioButtonId();
        int selectedIdLimpeza = radioGroupL.getCheckedRadioButtonId();
        int selectedIdVelocidad = radioGroupV.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButtonT = (RadioButton) findViewById(selectedIdTrato);
        radioButtonR = (RadioButton) findViewById(selectedIdParadas);
        radioButtonL = (RadioButton) findViewById(selectedIdLimpeza);
        radioButtonV = (RadioButton) findViewById(selectedIdVelocidad);

        final String val1=radioButtonT.getText().toString();
        final String val2=radioButtonR.getText().toString();
        final String val3=radioButtonL.getText().toString();
        final String val4=radioButtonV.getText().toString();
        final String fecha= textViewfecha.getText().toString();
        final String cop=textViewcop.getText().toString();
        Toast.makeText(CServicio.this,
                ""+val1+" "+val2+" "+val3+" "+val4+" "+fecha+" "+cop, Toast.LENGTH_SHORT).show();

        ///////////////////////////////////////////////////////////////
        RequestQueue queue = Volley.newRequestQueue(this);
        String url3 = "http://10.0.2.2/newRegistro.php";
        StringRequest stringRequest = new
                StringRequest(Request.Method.POST, url3,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {

                                    String query = new String(response);

                                    Log.d("My App", query.toString());
                                } catch (Throwable t) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CServicio.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fecha", fecha);
                        params.put("coperativa", cop);
                        params.put("tratoUsuario", val1);
                        params.put("respetaParadas", val2);
                        params.put("limpieza", val3);
                        params.put("respetaVelocidad", val4);
                        return params;
                    }
                };
        queue.add(stringRequest);
    }
}
