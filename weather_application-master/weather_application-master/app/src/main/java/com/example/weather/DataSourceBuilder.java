package com.example.weather;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

class DataSourceBuilder {
    private final List<City> dataSource;
    private final Resources resources;

    DataSourceBuilder(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    List<City> build() {
        String[] cities = resources.getStringArray(R.array.cities);
        String[] lat = resources.getStringArray(R.array.lat);
        String[] lon = resources.getStringArray(R.array.lon);

        for (int i = 0; i < cities.length; i++)
            dataSource.add(new City(cities[i], lat[i], lon[i]));
        return dataSource;
    }
}
