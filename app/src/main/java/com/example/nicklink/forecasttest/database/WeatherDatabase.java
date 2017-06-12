package com.example.nicklink.forecasttest.database;

import com.example.nicklink.forecasttest.domain.Weather;
import com.example.nicklink.forecasttest.domain.WeatherResponse;

/**
 * Created by NickLink on 10.06.2017.
 */

public interface WeatherDatabase {
    void saveWeather(String city, int days, WeatherResponse response);
    void deleteWeather(String city, int days);
    WeatherResponse getWeather(String city, int days);
}
