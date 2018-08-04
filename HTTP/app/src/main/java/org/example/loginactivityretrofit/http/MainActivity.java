package org.example.loginactivityretrofit.http;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    private TextView salida;

    private RequestQueue colaPeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = (EditText) findViewById(R.id.EditText01);
        salida = (TextView) findViewById(R.id.TextView01);
        //Permite accesos a la red desde el hilo principal
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.
                Builder().permitNetwork().build());

        colaPeticiones = Volley.newRequestQueue(this);
    }

    public void buscar(View view){
        try {
            String palabras = entrada.getText().toString();
            String resultado = resultadosGoogle(palabras);
            salida.append(palabras + "--" + resultado + "\n");
        } catch (Exception e) {
            salida.append("Error al conectar\n");
            Log.e("HTTP", e.getMessage(), e);
        }
    }

    public void buscar2(View view){
        String palabras = entrada.getText().toString();
        salida.append(palabras + "--");
        new BuscarGoogle().execute(palabras);
    }

    public void buscar3(View view){
        try {
            String palabras = entrada.getText().toString();
            String resultado = resultadosSW(palabras);
            salida.append(palabras + "--" + resultado + "\n");
        } catch (Exception e) {
            salida.append("Error al conectar\n");
            Log.e("HTTP", e.getMessage(), e);
        }
    }

    public void buscar4(View view){
        String palabras = entrada.getText().toString();
        try {
            resultadosGoogleVolley(palabras);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String resultadosGoogle(String palabras) throws Exception {
        String pagina = "", devuelve = "";
        URL url = new URL("http://www.google.es/search?hl=es&q=\""
                + URLEncoder.encode(palabras, "UTF-8") + "\"");
        HttpURLConnection conexion = (HttpURLConnection)
                url.openConnection();
        conexion.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1)");
        if (conexion.getResponseCode()== HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conexion.getInputStream()));
            String linea = reader.readLine();
            while (linea != null) {
                pagina += linea;
                linea = reader.readLine();
            }
            reader.close();
            devuelve = buscaAproximadamente(pagina);
        } else {
            devuelve = "ERROR: " + conexion.getResponseMessage();
        }
        conexion.disconnect();
        return devuelve;
    }

    String buscaAproximadamente(String pagina){
        int ini = pagina.indexOf("Aproximadamente");
        if (ini != -1) {
            int fin = pagina.indexOf(" ", ini + 16);
            return pagina.substring(ini + 16, fin);
        } else {
            return "no encontrado";
        }
    }


    //Clase HTTP Asyncrona
    class BuscarGoogle extends AsyncTask<String, Void, String> {
        private ProgressDialog progreso;
        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(MainActivity.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setMessage("Accediendo a Google...");
            progreso.setCancelable(false); // false: no muestra botón cancelar
            progreso.show();
        }

        @Override
        protected String doInBackground(String... palabras) {
            try {
                return resultadosGoogle(palabras[0]);
            } catch (Exception e) {
                cancel(false); //true: interrumpimos hilo, false: dejamos termine
                Log.e("HTTP", e.getMessage(), e);
                return null;
            }
        }
        @Override
        protected void onPostExecute(String res) {
            progreso.dismiss();
            salida.append(res + "\n");
        }
        @Override
        protected void onCancelled() {
            progreso.dismiss();
            salida.append("Error al conectar\n");
        }
    }

    //Clase de Volley
    //tambien se puede meter getMethod para definir POST o GET
    // o getParams
    void resultadosGoogleVolley(final String palabras) throws Exception {
        StringRequest peticion = new StringRequest(
                Request.Method.GET,
                "http://www.google.es/search?hl=es&q=\""
                        + URLEncoder.encode(palabras, "UTF-8") + "\"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuesta) {
                        String resultado = buscaAproximadamente(respuesta);
                        salida.append(palabras + "--" + resultado + "\n");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        salida.append("Error: " + error.getMessage());
                    }
                }
        ) {

            //para sobreescribir Cabeceras
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<String, String>();
                cabeceras.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1)");
                return cabeceras;
            }
        };
        colaPeticiones.add(peticion);
    }


    String resultadosSW(String palabras) throws Exception {
        URL url = new URL("http://books.google.com/books/feeds/volumes?q=\""
                + URLEncoder.encode(palabras, "UTF-8") + "\"");
        SAXParserFactory fabrica = SAXParserFactory.newInstance();
        SAXParser parser = fabrica.newSAXParser();
        XMLReader lector = parser.getXMLReader();
        ManejadorXML manejadorXML = new ManejadorXML();
        lector.setContentHandler(manejadorXML);
        lector.parse(new InputSource(url.openStream()));
        return manejadorXML.getTotalResults();
    }

    public class ManejadorXML extends DefaultHandler {
        private String totalResults;
        private StringBuilder cadena = new StringBuilder();

        public String getTotalResults() {
            return totalResults;
        }

        @Override
        public void startElement(String uri, String nombreLocal, String
                nombreCualif, Attributes atributos) throws SAXException {
            cadena.setLength(0);
        }

        @Override
        public void characters(char ch[], int comienzo, int lon){
            cadena.append(ch, comienzo, lon);
        }

        @Override
        public void endElement(String uri, String nombreLocal,
                               String nombreCualif) throws SAXException {
            if (nombreLocal.equals("totalResults")) {
                totalResults = cadena.toString();
            }
        }
    }


}