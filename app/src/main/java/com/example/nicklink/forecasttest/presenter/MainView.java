package com.example.nicklink.forecasttest.presenter;

import android.support.v4.app.Fragment;

import com.example.nicklink.forecasttest.adapter.WeatherAdapter;

/**
 * Created by NickLink on 12.06.2017.
 */

public interface MainView {

    void showLoadingIndicator();
    void hideLoadingIndicator();
    void setAdapter(WeatherAdapter adapter);

    void showSnack(String message);
    void updateAlertIcon(String count, int visible);
    void showFragment(Fragment fragment);
}
