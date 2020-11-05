package com.j2.retrofitlibrary;


import java.util.ArrayList;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Manager {

    public void getData(final ManagerCompleteListener managerCompleteListener) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallsInterface callsInterface = retrofit.create(CallsInterface.class);
        Call<ApiRes> call = callsInterface.getAllData();
        call.enqueue(new Callback<ApiRes>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ApiRes> call, Response<ApiRes> response) {
                managerCompleteListener.onComplete(response.body(), response.code());
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ApiRes> call, Throwable t) {
                managerCompleteListener.onComplete(null, -1);
            }
        });
    }

}

