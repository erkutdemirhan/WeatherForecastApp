package com.example.weatherforecastapp.screens.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.WeatherForecastApp;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;
import com.example.weatherforecastapp.repository.Resource;
import com.example.weatherforecastapp.repository.WeatherDataRepository;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class MainViewModel extends AndroidViewModel {

    private final WeatherDataRepository weatherDataRepository;
    private final Context context;

    public MainViewModel(Application application) {
        super(application);
        weatherDataRepository = ((WeatherForecastApp) application).getWeatherDataRepository();
        context               = application.getApplicationContext();
    }

    public LiveData<Resource<DailyForecastResponse>> getCurrentLocForecast() {
        return weatherDataRepository.getCurrentLocForecast();
    }

    public void sendCurrLocForecastRequest(final double latitude, final double longitude) {
        final String apiId        = context.getString(R.string.weather_forecast_api_id);
        final String responseType = context.getString(R.string.mode);
        final String unit         = context.getString(R.string.units);
        final int dayCount        = context.getResources().getInteger(R.integer.daycount);

        weatherDataRepository.sendWeatherForecastRequestForCurrentLocation(
                apiId, responseType, unit,
                dayCount, latitude, longitude);
    }


}
