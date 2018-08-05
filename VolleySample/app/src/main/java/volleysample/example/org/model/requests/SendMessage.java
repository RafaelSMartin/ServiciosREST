package volleysample.example.org.model.requests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import volleysample.example.org.model.Model;
import volleysample.example.org.model.remote.BaseRequestConfiguration;
import volleysample.example.org.model.remote.BasicRequest;
import volleysample.example.org.model.remote.DefaultExclusionStrategy;
import volleysample.example.org.model.remote.HttpRequestSingleton;

public class SendMessage {
    private Context context;
    private String url;
    private String request;

    public SendMessage(Context context, String url, String request) {
        this.context = context;
        this.url = url;
        this.request = request;
    }

    public void sendMessageVolley(){
        BaseRequestConfiguration baseRequestConfiguration = new BaseRequestConfiguration() {
            @Override
            public int getMethod() {
                return Request.Method.GET;
            }

            @Override
            public String getUrl() {
                return url;
            }

            @Override
            public HashMap<String, String> getParams() {
                HashMap<String,String> parametros = new HashMap<String, String>();
                parametros.put("request", request);

                return parametros;
            }
        };

        HttpRequestSingleton.getInstance(context).addToRequestQueue(
                new BasicRequest(baseRequestConfiguration, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        GsonBuilder builder = new GsonBuilder();
                        builder.setExclusionStrategies(new DefaultExclusionStrategy());
                        Gson gson = builder.create();

                        String modelo = gson.toJson(response);
                        Model model = gson.fromJson(modelo, Model.class);

//                        if(model != null){
                            Log.d("sendMessage", model.getInfo().getResults().toString()+"");
//                        }

                    }// Fin onResponse
                }, new Response.ErrorListener() {
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
                }, new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));
    }

}
