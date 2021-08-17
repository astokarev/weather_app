package com.example.weather;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements MainNavigator {

    private CityPreference cityPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityPreference = new CityPreference(this);
        if (savedInstanceState == null)
            initFragment();
    }

    private void initFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main, searchFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void startResultFragment(String city) {
        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        resultFragment.requestRetrofit(city);
        cityPreference.setCity(city);

    }

    @Override
    public void startResultFragmentFromList(String city, String lat, String lon) {
        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        resultFragment.requestRetrofitCoordinates(lat, lon);
        cityPreference.setCity(city);
    }
}

