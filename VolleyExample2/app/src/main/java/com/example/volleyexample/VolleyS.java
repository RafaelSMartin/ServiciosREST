package com.example.volleyexample;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 * El networking con Volley se realiza creando una cola y añadiendo después peticiones a esa cola,
 * y Google nos recomienda que mantengamos una sola cola para toda la aplicación,
 * por lo que haremos un Singleton con este propósito.
 *
 */

public class VolleyS {
    private static VolleyS mVolleyS = null;
    //Este objeto es la cola que usará la aplicación
    private RequestQueue mRequestQueue;

    private VolleyS(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    //Es la cola donde vamos a añadir nuestras conexiones.
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
