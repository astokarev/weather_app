package com.example.weather;


public interface MainNavigator {
    void startResultFragment(String city);

    void startResultFragmentFromList(String city, String lat, String lon);
}

