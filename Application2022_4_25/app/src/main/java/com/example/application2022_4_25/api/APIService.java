package com.example.application2022_4_25.api;

import com.example.application2022_4_25.bean.NowWeaherResponse;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIService {

    @GET("/simpleWeather/query")
    Observable<NowWeaherResponse> getNowWeather(@Query("key") String key,@Query("city") String city);
}
