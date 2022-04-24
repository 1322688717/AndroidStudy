package com.example.retrofitapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    @GET("/v7/weather/now")
    Call<Bean> getNow(@Query("location") String location ,@Query("key") String key);
}
