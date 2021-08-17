package com.example.weather.rest.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class WeatherTable {
    private final static String TABLE_NAME = "Weather";
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_CITY = "city";
    private final static String COLUMN_TEMPERATURE = "temperature";
    private final static String COLUMN_WIND = "wind";
    private final static String COLUMN_HUMIDITY = "humidity";
    private final static String COLUMN_PRESSURE = "pressure";


    static void createTable(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CITY + " TEXT,"
                + COLUMN_TEMPERATURE + " TEXT," + COLUMN_WIND + " TEXT,"
                + COLUMN_HUMIDITY + " TEXT," + COLUMN_PRESSURE + " TEXT)");
    }

    public static void addNote(String city, float temperature, float wind,
                               int humidity, float pressure, SQLiteDatabase database) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_TEMPERATURE, temperature);
        values.put(COLUMN_WIND, wind);
        values.put(COLUMN_HUMIDITY, humidity);
        values.put(COLUMN_PRESSURE, pressure);
        database.insert(TABLE_NAME, null, values);
    }

}
