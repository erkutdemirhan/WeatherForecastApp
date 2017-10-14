package com.example.weatherforecastapp;

import android.app.Application;

import com.example.weatherforecastapp.api.ApiServiceFactory;
import com.example.weatherforecastapp.api.WeatherApiClient;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class WeatherForecastApp extends Application {

    private WeatherApiClient weatherApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        weatherApiClient = ApiServiceFactory.getWeatherApiClient(getString(R.string.base_url));
    }
}
