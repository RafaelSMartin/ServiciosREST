package volleysample.example.org.model.remote;

import java.util.HashMap;

public abstract class BaseRequestConfiguration {

    public static final String DEVELOP_HOST = "https://desarrollo.bewor.com/";
    public static final String TEST_HOST = "https://pruebas.bewor.com/";

    public static final String COMMUNICATION_API = "apiv1/";
    public static final String TASK_API = "ApiTask/";

    /**
     * Devuelve el tipo de petición a realizar: GET o POST.
     * @return int con indicando el tipo de petición.
     */
    public abstract int getMethod();

    /**
     * Devuelve la URL remota de la petición. Siempre será una concatenación de "[DEVELOP_HOST | TEST_HOST]" + "[COMMUNICATION_API | TASK_API]" + "METODO_DE_API"
     * @return URL remota
     */
    public abstract String getUrl();

    /**
     * Devuelve un conjunto Clave:valor con los parametros para la petición.
     * @return Parametros de la petición remota.
     */
    public abstract HashMap<String, String> getParams ();

}
