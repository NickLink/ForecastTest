package com.example.nicklink.forecasttest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NickLink on 13.06.2017.
 */

public class StringUtils {
    public static String getDateFromMillis(long millis){
        String date_string = "";
        try{
            SimpleDateFormat f = new SimpleDateFormat("dd-MM"); //SimpleDateFormat("yyyy-MM-dd");
            Date date = (new Date(millis * 1000));
            date_string = f.format(date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return date_string;
    }
}
