package com.example.weather;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.weather.rest.OpenWeatherRepo;
import com.example.weather.rest.OpenWeatherRepoCoord;
import com.example.weather.rest.database.DatabaseHelper;
import com.example.weather.rest.database.WeatherTable;
import com.example.weather.rest.network.WeatherRequestRestModel;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultFragment extends Fragment {

    private static final String FONT_FILENAME = "fonts/weather.ttf";
    WeatherRequestRestModel model = new WeatherRequestRestModel();
    private Typeface weatherFont;
    private TextView requiredCity;
    private TextView lastUpdate;
    private TextView cityTemperature;
    private TextView weatherIcon;
    private TextView cityDetails;
    private SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(Objects.requireNonNull(getActivity()).getAssets(), FONT_FILENAME);
        database = new DatabaseHelper(getActivity().getApplicationContext()).getWritableDatabase();
        requestRetrofit(new CityPreference(getActivity()).getCity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_result, container, false);

        initUI(fragmentView);
        weatherIcon.setTypeface(weatherFont);

        Button buttonBack = fragmentView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        });

        return fragmentView;
    }

    private void initUI(View fragmentView) {
        requiredCity = fragmentView.findViewById(R.id.cityName);
        lastUpdate = fragmentView.findViewById(R.id.lastUpdate);
        cityTemperature = fragmentView.findViewById(R.id.cityTemperature);
        cityDetails = fragmentView.findViewById(R.id.details);
        weatherIcon = fragmentView.findViewById(R.id.weatherIcon);
    }

    public void requestRetrofit(String city) {
        OpenWeatherRepo.getSingleton().getAPI().loadWeather(city,
                "762ee61f52313fbd10a4eb54ae4d4de2", "metric")
                .enqueue(new Callback<WeatherRequestRestModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                           @NonNull Response<WeatherRequestRestModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            model = response.body();
                            setPlaceName();
                            setUpdatedText();
                            setCurrentTemp();
                            setDetails();
                            setWeatherIcon(model.weather[0].id, model.sys.sunrise, model.sys.sunset);
                            WeatherTable.addNote(city, model.main.temp, model.wind.speed, model.main.humidity, model.main.pressure, database);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherRequestRestModel> call, @NonNull Throwable t) {
                        requiredCity.setText(R.string.error);
                    }
                });
    }

    public void requestRetrofitCoordinates(String lat, String lon) {
        OpenWeatherRepoCoord.getSingleton().getAPI().loadWeather(lat, lon,
                "762ee61f52313fbd10a4eb54ae4d4de2", "metric")
                .enqueue(new Callback<WeatherRequestRestModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                           @NonNull Response<WeatherRequestRestModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            model = response.body();
                            setPlaceName();
                            setUpdatedText();
                            setCurrentTemp();
                            setDetails();
                            setWeatherIcon(model.weather[0].id, model.sys.sunrise, model.sys.sunset);
                            WeatherTable.addNote(model.name, model.main.temp, model.wind.speed, model.main.humidity, model.main.pressure, database);

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherRequestRestModel> call, @NonNull Throwable t) {
                        requiredCity.setText(R.string.error);
                    }
                });
    }

    private void setPlaceName() {
        String cityText = model.name.toUpperCase() + ", " + model.sys.country;
        requiredCity.setText(cityText);
    }


    private void setUpdatedText() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(model.dt * 1000));
        String updatedText = "Last update: " + updateOn;
        lastUpdate.setText(updatedText);
    }

    private void setCurrentTemp() {
        String currentTextText = model.main.temp + "\u2103";
        cityTemperature.setText(currentTextText);
    }

    private void setDetails() {
        String detailsText = model.weather[0].description.toUpperCase() + "\n" + "\n"
                + "Wind: " + model.wind.speed + " m/s" + "\n"
                + "Humidity: " + model.main.humidity + "%" + "\n"
                + "Pressure: " + model.main.pressure + "hPa";
        cityDetails.setText(detailsText);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = getString(R.string.weather_cloudy);
                    break;
                }
            }
        }
        weatherIcon.setText(icon);
    }
}
