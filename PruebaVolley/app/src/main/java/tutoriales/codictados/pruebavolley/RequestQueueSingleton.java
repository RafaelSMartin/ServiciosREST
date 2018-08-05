package tutoriales.codictados.pruebavolley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class RequestQueueSingleton {

    private static RequestQueue requestQueue;

    private RequestQueueSingleton() {}

    public static RequestQueue getRequestQueue(Context context){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        return requestQueue;

    }
}
