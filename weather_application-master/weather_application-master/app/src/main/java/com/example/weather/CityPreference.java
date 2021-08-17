package com.example.weather;

import android.app.Activity;
import android.content.SharedPreferences;

class CityPreference {

    private static final String KEY = "City";
    private static final String MOSCOW = "Moscow";
    private SharedPreferences preferences;

    CityPreference(Activity activity) {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCity() {
        return preferences.getString(KEY, MOSCOW);
    }

    void setCity(String city) {
        preferences.edit().putString(KEY, city).apply();
    }
}
