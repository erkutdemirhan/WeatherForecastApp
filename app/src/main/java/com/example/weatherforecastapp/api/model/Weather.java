package com.example.weatherforecastapp.api.model;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class Weather {

    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
