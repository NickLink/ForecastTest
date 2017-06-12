package com.example.nicklink.forecasttest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;
import android.util.Log;

import static com.example.nicklink.forecasttest.database.DatabaseContract.*;

/**
 * Created by NickLink on 10.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = DatabaseHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e(TAG,"DatabaseHelper ->" + " onCreate");
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE "
                + WeatherEntry.TABLE_NAME + " ("
                + WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WeatherEntry.COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + WeatherEntry.COLUMN_CITY + " TEXT NOT NULL, "
                + WeatherEntry.COLUMN_DAYS + " INTEGER NOT NULL, "
                + WeatherEntry.COLUMN_DATA + " TEXT NOT NULL"
                + ");";
        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
