package com.example.weatherforecastapp.api;

import com.example.weatherforecastapp.api.response.DailyForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public interface ApiClient {

    @GET("daily")
    Call<DailyForecastResponse> getDailyForecast(
            @Query("appid") final String appId,
            @Query("mode") final String responseBodyType,
            @Query("units") final String measurementUnit,
            @Query("daycount") final int forecastDayCount,
            @Query("lat") final double latitude,
            @Query("lon") final double longitude);

}
