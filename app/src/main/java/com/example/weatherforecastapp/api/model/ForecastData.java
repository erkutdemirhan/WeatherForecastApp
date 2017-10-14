package com.example.weatherforecastapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class ForecastData {

    @SerializedName("dt")
    private long forecastTimestamp;

    @SerializedName("main")
    private Temperature temperature;

    @SerializedName("weather")
    private List<Weather> weathers;

}
