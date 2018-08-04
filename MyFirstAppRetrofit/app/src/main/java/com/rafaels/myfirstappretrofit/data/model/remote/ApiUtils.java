package com.rafaels.myfirstappretrofit.data.model.remote;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "https://pruebas.bewor.com/apiv1/";
    public static final String email = "rsmartin@vocces.com";
    public static final String password = "68459";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
