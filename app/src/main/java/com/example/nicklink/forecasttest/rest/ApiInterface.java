package com.example.nicklink.forecasttest.rest;

import com.example.nicklink.forecasttest.domain.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NickLink on 10.06.2017.
 */

public interface ApiInterface {
    @GET("forecast/daily")
    Call<WeatherResponse> getWeatherResponce(
            @Query("q") String location,
            @Query("appid") String appid,
            @Query("cnt") int cnt,
            @Query("units") String units,
            @Query("lang") String lang);

}
