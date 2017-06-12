package com.example.nicklink.forecasttest.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nicklink.forecasttest.MainActivity;
import com.example.nicklink.forecasttest.R;
import com.example.nicklink.forecasttest.adapter.WeatherAdapter;
import com.example.nicklink.forecasttest.database.WeatherDatabase;
import com.example.nicklink.forecasttest.database.WeatherDatabaseImpl;
import com.example.nicklink.forecasttest.domain.WeatherItem;
import com.example.nicklink.forecasttest.domain.WeatherResponse;
import com.example.nicklink.forecasttest.fragments.RetainDataFragment;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by NickLink on 12.06.2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private WeatherDatabase database;
    private String TAG = MainPresenterImpl.class.getSimpleName();
    Context context;
    private MainView mainView;
    MainActivity activity;
    RetainDataFragment dataFragment;
    public static final String CITY = "Vinnitsa";
    public static final int DAYS = 14;

    public MainPresenterImpl(MainView view) {
        mainView = view;
        activity = (MainActivity) mainView;
        database = new WeatherDatabaseImpl(activity);
        dataFragment = (RetainDataFragment) activity.getDataFragment();
    }

    @Override
    public void navigationItemClick(int itemId) {

    }

    @Override
    public void onOptionsItemClick(int itemId) {

    }

    @Override
    public void saveInstanseState(Bundle outState) {

    }

    @Override
    public void restoreInstanseState(Bundle savedInstanceState) {

    }

    @Override
    public void setMenuStatus() {

    }

    @Override
    public void loadData() {
        mainView.showLoadingIndicator();
        if(dataFragment !=null)
            dataFragment.getObservableResponse(database, CITY, DAYS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherResponse>() {
                        @Override
                        public void onCompleted() {
                            Log.e(TAG, " -> onCompleted ");
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            mainView.hideLoadingIndicator();
                            mainView.showSnack("Loading error -> " + throwable.getLocalizedMessage());
                        }

                        @Override
                        public void onNext(WeatherResponse response) {
                            List<WeatherItem> itemList = response.getList();
                            mainView.setAdapter(new WeatherAdapter(itemList, R.layout.list_item, activity));
                            mainView.hideLoadingIndicator();
                        }
                    });
    }
}
