package com.example.nicklink.forecasttest.database;

import android.provider.BaseColumns;

/**
 * Created by NickLink on 10.06.2017.
 */

public class DatabaseContract {

    public static final class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_DAYS = "days";
        public static final String COLUMN_DATA = "data";
    }
}
