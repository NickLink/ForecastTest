package com.example.nicklink.forecasttest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.nicklink.forecasttest.database.WeatherDatabase;
import com.example.nicklink.forecasttest.database.WeatherDatabaseImpl;
import com.example.nicklink.forecasttest.domain.WeatherResponse;
import com.example.nicklink.forecasttest.rest.ApiClient;
import com.example.nicklink.forecasttest.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by User on 04.05.2017.
 */

public class RetainDataFragment extends Fragment {
    private String TAG = RetainDataFragment.class.getSimpleName();

    private WeatherResponse weatherResponse;
    private WeatherDatabase database;
    private BehaviorSubject<WeatherResponse> observableResponse;

    public static final String CITY = "Vinnitsa";
    public static final String APPID = "5fe568445b7050f344a61cbaa83a1e5a";
    public static final int DAYS = 14;
    public static final String UNITS = "metric";
    public static final String LANG = "ua";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this Fragment so that it will not be destroyed when an orientation
        // change happens and we can keep our AsyncTask running
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        topRatedPresenter.loadData(getActivity());
    }

    public void loadData(WeatherDatabase database, String city, int days) {
        this.database = database;
        observableResponse = BehaviorSubject.create();
        weatherResponse = getListFromDatabase(city, days);
        if (weatherResponse != null) {
//            listener.getData(getListFromDatabase());
            Log.e(TAG, "-> getListFromDatabase() != null");
            observableResponse.onNext(weatherResponse);
        } else {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<WeatherResponse> call = apiInterface.getWeatherResponce(CITY, APPID, DAYS, UNITS, LANG);
            call.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    Log.e(TAG, "-> onResponse");
                    weatherResponse = response.body();
                    Log.e(TAG, "Success " + weatherResponse.toString());
                    saveInDatabase(CITY, DAYS, weatherResponse);
                    observableResponse.onNext(weatherResponse);
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.e(TAG, "Error " + t.toString());
                }
            });

        }
    }

    private WeatherResponse getListFromDatabase(String city, int days) {
        if (database != null)
            return database.getWeather(city, days);
        else return null;
    }

    private void saveInDatabase(String city, int days, WeatherResponse response) {
        database.saveWeather(city, days, response);
    }

    public void deleteFromDatabase(String city, int days) {
        database.deleteWeather(city, days);
    }

    public Observable<WeatherResponse> getObservableResponse(WeatherDatabase database, String city, int days) {
        if (observableResponse == null) {
            loadData(database, city, days);
        }
        return observableResponse;
    }

    public void onDestroyF() {
        observableResponse.subscribe().unsubscribe();

    }
}
