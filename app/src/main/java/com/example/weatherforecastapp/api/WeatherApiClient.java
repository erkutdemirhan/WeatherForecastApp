package com.example.weatherforecastapp.api;

import com.example.weatherforecastapp.api.response.DailyForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public interface WeatherApiClient {

    @GET("daily")
    Call<DailyForecastResponse> getDailyForecastByLocation(
            @Query("appid") final String appId,
            @Query("mode") final String responseBodyType,
            @Query("units") final String measurementUnit,
            @Query("cnt") final int forecastDayCount,
            @Query("lat") final double latitude,
            @Query("lon") final double longitude);

    @GET("daily")
    Call<DailyForecastResponse> getDailyForecastByCityName(
            @Query("appid") final String appId,
            @Query("mode") final String responseBodyType,
            @Query("units") final String measurementUnit,
            @Query("cnt") final int forecastDayCount,
            @Query("q") final String cityName);

}
