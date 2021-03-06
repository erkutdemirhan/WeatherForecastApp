package com.example.weatherforecastapp.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class Temperature {

    @SerializedName("day")
    private String dayTemperature;

    @SerializedName("temp_min")
    private String min;

    @SerializedName("temp_max")
    private String max;

    @SerializedName("humidity")
    private int humidityPercentage;

    private double pressure;

    public String getDayTemperature() {
        return dayTemperature;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public double getPressure() {
        return pressure;
    }
}
