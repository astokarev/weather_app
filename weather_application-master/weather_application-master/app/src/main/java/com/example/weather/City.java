package com.example.weather;

public class City {

    private final String name;
    private final String lat;
    private final String lon;


    City(String name, String lat, String lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    String getLat() {
        return lat;
    }

    String getLon() {
        return lon;
    }
}

