package tutoriales.codictados.pruebavolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView json;
    private TextView bitmap;
    private ImageView iv_bitmap;
    private Button jsonRequest;
    private Button imageRequest;
    private RequestQueue requestQueue;
    private final static int JSON_REQUEST = 0;
    private final static int IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json = (TextView) findViewById(R.id.json);
        iv_bitmap = (ImageView) findViewById(R.id.iv_bitmap);
        jsonRequest = (Button) findViewById(R.id.jsonRequest);
        imageRequest = (Button) findViewById(R.id.imageRequest);

        //Obtenemos la instancia única de la cola de peticiones
        requestQueue = RequestQueueSingleton.getRequestQueue(this);

        jsonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ten en cuenta que esta dirección URL es externa a Codictados y tal vez algún día
                //puede dejar de estar operativa.
                String url = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo";
                url = url.replaceAll(" ", "%20");

                //Creamos la petición
                JsonObjectRequest request = new JsonObjectRequest(url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Al recibir la respuesa, la mostraremos en el TextView
                                json.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Si recibimos un error, mostraremos la causa en el TextView
                                json.setText(error.getMessage());
                            }
                        }
                );

                //Le ponemos un tag que servirá para identificarla si la queremos cancelar
                request.setTag(JSON_REQUEST);
                //Al ponerle la política de reintento, le hacemos casting, ya que el metodo recibe
                //un objeto de la clase padre
                request = (JsonObjectRequest) setRetryPolicy(request);
                //Iniciamos la petición añadiéndola a la cola
                requestQueue.add(request);
            }
        });

        imageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ten en cuenta que esta dirección URL es externa a Codictados y tal vez algún día
                //puede dejar de estar operativa.
                String url = "https://s3.amazonaws.com/media-p.slid.es/uploads/kouceylahadji-1/images/174949/json_logo-555px__1_.png";

                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        //Al recibir la imagen, la cargamos en el ImageView
                        iv_bitmap.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Si recibimos un error, lo mostramos en el TextView
                        bitmap.setText(error.getMessage());
                    }
                }
                );

                //Le ponemos un tag que servirá para identificarla si la queremos cancelar
                imageRequest.setTag(IMAGE_REQUEST);
                //Al ponerle la política de reintento, le hacemos casting, ya que el metodo recibe
                //un objeto de la clase padre
                imageRequest = (ImageRequest) setRetryPolicy(imageRequest);
                //Iniciamos la petición poniéndola en la cola
                requestQueue.add(imageRequest);
            }
        });





    }


    //Le pone una política de 4 reintentos con 4 segundos entre cada uno y la devuelve.
    public Request setRetryPolicy(Request request){
        return request.setRetryPolicy(new DefaultRetryPolicy(1000, 4, 1));
    }
}
