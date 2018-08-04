package com.rafaels.myfirstappretrofit.data.model.remote;
import com.rafaels.myfirstappretrofit.data.model.LoginPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */



/** FormUrlEncoded
 * Esto indicará que la petición tendrá su tipo MIME (un campo de encabezado que identifica
 * el formato del cuerpo de una petición o respuesta HTTP) establecido a
 * application/x-www-form-urlencoded y también que sus nombres de campo y valores
 * serán codificados en UTF-8 antes de ser codificados en URI
 *
 *
 *  Field("key")
 * debería empatar el nombre que la API espera.
 * Retrofit convierte implícitamente los valores a cadenas de texto usando String.valueOf(Object),
 * y las cadenas son entonces codificadas en forma de URL. Los valores null son ignorados.
 *
 **/

public interface APIService {
    //Queremos hacer una peticion POST
//    @POST("/posts")
//    @FormUrlEncoded
//    Call<LoginPojo> savePost(@Field("employeePath") String employeePath,
//                             @Field("employeePath45") String employeePath45,
//                             @Field("employeeInfo") String employeeInfo,
//                             @Field("partners") String partners,
//                             @Field("teams") String teams,
//                             @Field("members") String members,
//                             @Field("messages") String messages,
//                             @Field("response") String response
//    );
    @POST("setLogin")
    @FormUrlEncoded
    Call<LoginPojo> savePost(@Field("email") String email,
                             @Field("password") String password,
                             @Field("token") String token
    );




}
