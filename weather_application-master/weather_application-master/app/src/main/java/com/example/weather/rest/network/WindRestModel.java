package com.example.weather.rest.network;

import com.google.gson.annotations.SerializedName;

public class WindRestModel {
    @SerializedName("speed")
    public float speed;
    @SerializedName("deg")
    public float deg;
}
