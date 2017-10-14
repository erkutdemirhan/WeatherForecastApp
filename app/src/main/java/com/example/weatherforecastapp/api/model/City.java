package com.example.weatherforecastapp.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class City {

    private int id;
    private String name;

    @SerializedName("country")
    private String countryCode;

    private int population;

    @SerializedName("coord")
    private Coordinate coordinate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getPopulation() {
        return population;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
