package com.example.weather.rest.network;

import com.google.gson.annotations.SerializedName;

public class WeatherRestModel {
    @SerializedName("id")
    public int id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;
}
