package com.example.weatherforecastapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.weatherforecastapp.api.WeatherApiClient;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class WeatherDataRepository extends BaseRepository {

    private final MutableLiveData<Resource<DailyForecastResponse>> currentLocationForecast     = new MutableLiveData<>();
    private final MutableLiveData<Resource<List<DailyForecastResponse>>> savedLocationForecast = new MutableLiveData<>();

    private final WeatherApiClient apiClient;

    public WeatherDataRepository(WeatherApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<Resource<DailyForecastResponse>> getCurrentLocForecast() {
        return currentLocationForecast;
    }

    public LiveData<Resource<List<DailyForecastResponse>>> getSavedForecasts() {
        return savedLocationForecast;
    }

    public void sendWeatherForecastRequestForCurrentLocation(
            final String apiId, final String responseBodyType,
            final String measurementUnit, final int dayCount,
            final double latitude, final double longitude) {

        currentLocationForecast.setValue(Resource.<DailyForecastResponse>loading());

        Call<DailyForecastResponse> call = apiClient.getDailyForecastByLocation(
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

    public void sendWeatherForecastRequestForCityName(
            final String apiId, final String responseBodyType,
            final String measurementUnit, final int dayCount,
            final String cityName) {

        savedLocationForecast.setValue(Resource.<List<DailyForecastResponse>>loading());

        Call<DailyForecastResponse> call = apiClient.getDailyForecastByCityName(apiId, responseBodyType,
                measurementUnit, dayCount, cityName);

        call.enqueue(new Callback<DailyForecastResponse>() {
            @Override
            public void onResponse(Call<DailyForecastResponse> call, Response<DailyForecastResponse> response) {
                if(response != null || response.isSuccessful()) {
                    List<DailyForecastResponse> data = savedLocationForecast.getValue().getData();
                    if(data == null) {
                        data = new ArrayList<DailyForecastResponse>();
                    }
                    data.add(response.body());
                    savedLocationForecast.setValue(Resource.success(data));
                } else {
                    savedLocationForecast.setValue(Resource.<List<DailyForecastResponse>>error(""));
                }
            }

            @Override
            public void onFailure(Call<DailyForecastResponse> call, Throwable t) {
                savedLocationForecast.setValue(Resource.<List<DailyForecastResponse>>error(""));
            }
        });
    }
}
