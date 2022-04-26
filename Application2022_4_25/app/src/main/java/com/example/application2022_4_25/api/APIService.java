package com.example.application2022_4_25.api;

import com.example.application2022_4_25.bean.NowWeaherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/v3/weather/now")
    Observable<NowWeaherResponse> getNowWeather(@Query("key") String key, @Query("city") String city);
}
