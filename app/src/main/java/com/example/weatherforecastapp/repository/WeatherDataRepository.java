package com.example.weatherforecastapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.weatherforecastapp.api.WeatherApiClient;
import com.example.weatherforecastapp.api.model.ForecastData;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class WeatherDataRepository extends BaseRepository {

    private final MutableLiveData<Resource<DailyForecastResponse>> currentLocationForecast = new MutableLiveData<>();
    private final WeatherApiClient apiClient;

    public WeatherDataRepository(WeatherApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<Resource<DailyForecastResponse>> getCurrentLocForecast() {
        return currentLocationForecast;
    }

    public void sendWeatherForecastRequestForCurrentLocation(
            final String apiId, final String responseBodyType,
            final String measurementUnit, final int dayCount,
            final double latitude, final double longitude) {

        currentLocationForecast.setValue(Resource.<DailyForecastResponse>loading());

        Call<DailyForecastResponse> call = apiClient.getDailyForecast(
                apiId, responseBodyType, measurementUnit,
                dayCount, latitude, longitude);

        call.enqueue(new Callback<DailyForecastResponse>() {
            @Override
            public void onResponse(Call<DailyForecastResponse> call, Response<DailyForecastResponse> response) {
                if(response != null && response.isSuccessful()) {
                    currentLocationForecast.setValue(Resource.success(response.body()));
                } else {
                    currentLocationForecast.setValue(Resource.<DailyForecastResponse>error(""));
                }
            }

            @Override
            public void onFailure(Call<DailyForecastResponse> call, Throwable t) {
                currentLocationForecast.setValue(Resource.<DailyForecastResponse>error(""));
            }
        });
    }
}
