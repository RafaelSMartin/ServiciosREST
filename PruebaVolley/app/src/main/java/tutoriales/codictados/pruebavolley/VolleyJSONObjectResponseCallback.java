package tutoriales.codictados.pruebavolley;

import com.android.volley.VolleyError;

import org.json.JSONObject;


public interface VolleyJSONObjectResponseCallback {

     void onSuccess(JSONObject jsonArray);
     void onFailure(VolleyError volleyError);
}
