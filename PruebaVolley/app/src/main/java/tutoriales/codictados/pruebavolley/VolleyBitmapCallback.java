package tutoriales.codictados.pruebavolley;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

public interface VolleyBitmapCallback {

    void onSuccess(Bitmap bitmap);
    void onFailure(VolleyError volleyError);
}
