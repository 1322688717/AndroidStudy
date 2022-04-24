package com.example.application_4_22;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/v7/weather/now")
    Call<Bean> getBean(@Query("location") String location,@Query("key") String key);
}
