package com.example.nicklink.forecasttest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.example.nicklink.forecasttest.database.DatabaseContract.WeatherEntry;
import com.example.nicklink.forecasttest.domain.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by NickLink on 10.06.2017.
 */

public class WeatherDatabaseImpl implements WeatherDatabase {
    public static final String TAG = WeatherDatabaseImpl.class.getSimpleName();
    private Gson gson;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public WeatherDatabaseImpl(Context context) {
        gson = new GsonBuilder().create();
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        Log.e(TAG,"WeatherDatabaseImpl ->" + " Success");
    }

    @Override
    public void saveWeather(String city, int days, WeatherResponse response) {
        ContentValues cv = new ContentValues();
        cv.put(WeatherEntry.COLUMN_CITY, city);
        cv.put(WeatherEntry.COLUMN_DAYS, days);
        cv.put(WeatherEntry.COLUMN_DATA, gson.toJson(response));
        db.beginTransaction();
        try{
            long _id = db.insert(WeatherEntry.TABLE_NAME, null, cv);
            if (_id != -1) {
                db.setTransactionSuccessful();
                Log.e(TAG,"saveWeather ->" + " Success");
            }
        } catch (Exception e){
            Log.e(TAG,"saveWeather ->" + " Exception " + e.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }



    }

    @Override
    public void deleteWeather(String city, int days) {
        String selection = WeatherEntry.COLUMN_CITY + " = ? AND " + WeatherEntry.COLUMN_DAYS + " = ?";
        String[] selectionArgs = {city, String.valueOf(days)};
        db.beginTransaction();
        try {
            if (db.delete(WeatherEntry.TABLE_NAME, selection, selectionArgs) > 0)
                db.setTransactionSuccessful();
            Log.e(TAG,"deleteWeather ->" + " Success");
        } catch (Exception e) {
            Log.e(TAG,"deleteWeather ->" + " Exception " + e.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public WeatherResponse getWeather(String city, int days) {
        Log.e(TAG,"getWeather ->" + city + " " + days);
        Cursor cursor = mCursor(city, days);
        WeatherResponse response = null;
        if (cursor.moveToFirst()) {
            response = gson.fromJson(cursor.getString(cursor.getColumnIndex(WeatherEntry.COLUMN_DATA)), WeatherResponse.class);
            Log.e(TAG,"getWeather response->" + response.toString());
        }
        return response;
    }

    private Cursor mCursor(String city, int days) {
        String selection = WeatherEntry.COLUMN_CITY + " = ? AND " + WeatherEntry.COLUMN_DAYS + " = ?";
        String[] selectionArgs = {city, String.valueOf(days)};
        return db.query(WeatherEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }

}
