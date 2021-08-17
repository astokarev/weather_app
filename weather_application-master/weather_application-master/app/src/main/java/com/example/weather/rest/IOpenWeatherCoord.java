package com.example.weather.rest;

import com.example.weather.rest.network.WeatherRequestRestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherCoord {
    @GET("data/2.5/weather")
    Call<WeatherRequestRestModel> loadWeather(@Query("lat") String lat,
                                              @Query("lon") String lon,
                                              @Query("appid") String keyApi,
                                              @Query("units") String units);
}
