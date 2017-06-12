package com.example.nicklink.forecasttest.presenter;

import android.os.Bundle;

/**
 * Created by NickLink on 12.06.2017.
 */

public interface MainPresenter {
    void navigationItemClick(int itemId);
    void onOptionsItemClick(int itemId);
    void saveInstanseState(Bundle outState);
    void restoreInstanseState(Bundle savedInstanceState);
    void setMenuStatus();

    void loadData();
}
