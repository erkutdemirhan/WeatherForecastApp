package com.example.weatherforecastapp.screens.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.UserPreferences;
import com.example.weatherforecastapp.WeatherForecastApp;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;
import com.example.weatherforecastapp.repository.Resource;
import com.example.weatherforecastapp.repository.WeatherDataRepository;

import java.util.List;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class MainViewModel extends AndroidViewModel {

    private final WeatherDataRepository weatherDataRepository;

    private final String apiId;
    private final String responseType;
    private final String unit;
    private final int dayCount;

    public MainViewModel(Application application) {
        super(application);
        weatherDataRepository = ((WeatherForecastApp) application).getWeatherDataRepository();
        Context context       = application.getApplicationContext();

        apiId        = context.getString(R.string.weather_forecast_api_id);
        responseType = context.getString(R.string.mode);
        unit         = context.getString(R.string.units);
        dayCount     = context.getResources().getInteger(R.integer.daycount);
    }

    public LiveData<Resource<DailyForecastResponse>> getCurrentLocForecast() {
        return weatherDataRepository.getCurrentLocForecast();
    }

    public LiveData<Resource<List<DailyForecastResponse>>> getSavedWeatherForecastData() {
        return weatherDataRepository.getSavedForecasts();
    }

    public void sendCurrLocForecastRequest(final double latitude, final double longitude) {
        weatherDataRepository.sendWeatherForecastRequestForCurrentLocation(
                apiId, responseType, unit,
                dayCount, latitude, longitude);
    }

    public void sendForecastRequestsWithSavedCityNames() {
        List<String> savedCityNames = UserPreferences.getInstance(getApplication()).getCityNames();
        for (String cityNameStr : savedCityNames) {
            sendForecastRequestWithCityName(cityNameStr);
        }
    }

    public void sendForecastRequestWithCityName(final String cityName) {
        weatherDataRepository.sendWeatherForecastRequestForCityName(
                apiId, responseType, unit, dayCount, cityName);
    }

}
