package com.example.weather.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherRepoCoord {
    private static OpenWeatherRepoCoord singleton = null;
    private IOpenWeatherCoord API;

    private OpenWeatherRepoCoord() {
        API = createAdapter();
    }

    public static OpenWeatherRepoCoord getSingleton() {
        if (singleton == null) {
            singleton = new OpenWeatherRepoCoord();
        }
        return singleton;
    }

    public IOpenWeatherCoord getAPI() {
        return API;
    }

    private IOpenWeatherCoord createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(IOpenWeatherCoord.class);
    }
}
