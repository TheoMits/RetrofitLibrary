package com.j2.retrofitlibrary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CallsInterface {

    @Headers("Content-Type: application/json")
    @GET("/api/users/2")
    Call<ApiRes> getAllData();
}
