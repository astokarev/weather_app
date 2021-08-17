package com.example.weather.rest.network;

import com.google.gson.annotations.SerializedName;

public class CoordRestModel {
    @SerializedName("lon")
    public float lon;
    @SerializedName("lat")
    public float lat;
}
