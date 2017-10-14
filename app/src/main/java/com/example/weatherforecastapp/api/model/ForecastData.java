package com.example.weatherforecastapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class ForecastData {

    @SerializedName("dt")
    private long forecastTimestamp;

    @SerializedName("temp")
    private Temperature temperature;

    @SerializedName("weather")
    private List<Weather> weathers;


    public long getForecastTimestamp() {
        return forecastTimestamp;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
}
