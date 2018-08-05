package com.rafaels.simplevolleystringrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rafaels.simplevolleystringrequest.data.model.EmployeeInfo;
import com.rafaels.simplevolleystringrequest.data.model.LoginPojo;
import com.rafaels.simplevolleystringrequest.util.Utils;
import com.rafaels.simplevolleystringrequest.volley.DefaultExclusionStrategy;
import com.rafaels.simplevolleystringrequest.volley.LongTimeoutAndTryRetryPolicy;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue colaPeticiones;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        colaPeticiones = Volley.newRequestQueue(this);

        textView = (TextView)findViewById(R.id.textview);

        try {
            sendVolley();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendVolley() throws Exception{

        StringRequest peticion = new StringRequest(
                Request.Method.POST, Utils.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuesta) {
                        Log.d("VolleySample", respuesta.toString()+"");

                        //Creacion del GSON builder
                        GsonBuilder builder = new GsonBuilder();
                        //Convertir texto a JSON y viceversa
                        builder.setExclusionStrategies(new DefaultExclusionStrategy());
                        //Creacion de objeto gson con builder
                        Gson gson = builder.create();

                        LoginPojo modelo = gson.fromJson(respuesta, LoginPojo.class);

                        if(!modelo.getResponse().getStatus().equals("401")) {
                            Log.d("VolleySample", modelo.getEmployeeInfo().size() + "");

                            String result = "";
                            for (int i = 0; i < modelo.getEmployeeInfo().size(); i++) {
                                String id = modelo.getEmployeeInfo().get(i).getEmployeeId();
                                String nombre = modelo.getEmployeeInfo().get(i).getEmployeeName();
                                String apellido = modelo.getEmployeeInfo().get(i).getEmployeeLastname();
                                String email = modelo.getEmployeeInfo().get(i).getUserEmail();
                                String token = modelo.getEmployeeInfo().get(i).getTokenId();


                                result = (id + "/" + nombre + " /" + apellido + " /" + email + " /" + token + "\n") + result;


                            }

                            textView.setText(result);
//                        textView.setText(modelo.getEmployeeInfo().toString());
                        }
                        textView.setText(modelo.getResponse().getStatus());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof AuthFailureError){
                            Log.e("ErrorResponse", "Error credentials" + error.getMessage());
                        } else if(error instanceof NetworkError){
                            Log.e("ErrorResponse", "Error network" + error.getMessage());
                        } else if (error instanceof NoConnectionError){
                            Log.e("ErrorResponse", "Error no conection" + error.getMessage());
                        } else if (error instanceof ParseError){
                            Log.e("ErrorResponse", "Error no process the response" + error.getMessage());
                        } else if (error instanceof ServerError){
                            Log.e("ErrorResponse", "Error from server" + error.getMessage());
                        } else if (error instanceof TimeoutError){
                            Log.e( "ErrorResponse", "Error out of time" + error.getMessage());
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<String, String>();
                cabeceras.put("Content-Type", "application/x-www-form-urlencoded");
                return cabeceras;
            }
            @Override
            public Map<String,String> getParams() {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("email", Utils.email);
                parametros.put("password", Utils.password);
                parametros.put("token", numeroRandom());
                return parametros;
            }
            @Override
            public int getMethod() { return Method.POST; }
        };
//        colaPeticiones.add(peticion);
        peticion.setRetryPolicy(new LongTimeoutAndTryRetryPolicy(
                LongTimeoutAndTryRetryPolicy.RETRIES_PHONE_ISP));
        App.getInstance().addToRequestQueue(peticion, App.TAG);

    }

    public String numeroRandom(){
        int numero = (int) (Math.random() * 200) + 1;
        Log.d("VolleySample", numero+"");
        return String.valueOf(numero);
    }
}
