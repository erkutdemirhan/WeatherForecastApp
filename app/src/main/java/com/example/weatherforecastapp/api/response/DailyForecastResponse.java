package com.example.weatherforecastapp.api.response;

import com.example.weatherforecastapp.api.model.City;
import com.example.weatherforecastapp.api.model.ForecastData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class DailyForecastResponse extends BaseResponse {

    @SerializedName("list")
    private List<ForecastData> forecastDataList;

    @SerializedName("city")
    private City cityInfo;

    public List<ForecastData> getForecastDataList() {
        return forecastDataList;
    }

    public City getCityInfo() {
        return cityInfo;
    }
}
