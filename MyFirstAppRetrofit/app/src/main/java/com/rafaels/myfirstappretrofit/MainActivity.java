package com.rafaels.myfirstappretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rafaels.myfirstappretrofit.data.model.LoginPojo;
import com.rafaels.myfirstappretrofit.data.model.remote.APIService;
import com.rafaels.myfirstappretrofit.data.model.remote.ApiUtils;
import com.rafaels.myfirstappretrofit.util.Utils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getApiService();

        String token = numeroRandom();

        sendPost(Utils.email, Utils.password, token);



    }

    private void sendPost(String email, String password, String s) {
        mAPIService.savePost(email, password, s).enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                Log.d("RetroFit1",  call.toString());
//                Log.d("RetroFit2",  response.body().getEmployeeInfo().get(0).getEmployeeName());

            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                Log.e("RetroFitError", "Unable to submit post to API.");
            }
        });
    }

    public String numeroRandom(){
        int numero = (int) (Math.random() * 200) + 1;
        return String.valueOf(numero);
    }



}
