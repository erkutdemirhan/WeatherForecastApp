package com.example.weatherforecastapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public class UserPreferences {

    private static final String SHARED_PREFS_KEY = "shared_prefs";

    private static final String CITY_NAMES_KEY = "city_names";
    private static final String DELIMITER = ",";

    private static UserPreferences instance = null;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public static synchronized UserPreferences getInstance(Context context) {
        if(instance == null) {
            instance = new UserPreferences(context);
        }
        return instance;
    }

    private UserPreferences(Context context) {
        prefs  = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public boolean addNewCityName(final String cityNameStr) {
        List<String> cityNameList = getCityNames();
        if(cityNameList.contains(cityNameStr)) {
            return false;
        } else {
            cityNameList.add(cityNameStr);
            final String newCityNamesStr = prepareCommaSeparatedCityNames(cityNameList);
            editor.putString(CITY_NAMES_KEY, newCityNamesStr);
            editor.commit();
            return true;
        }
    }

    public List<String> getCityNames() {
        final String cityNamesStr = prefs.getString(CITY_NAMES_KEY, "");
        return getCityNamesFromCommaSeparatedStr(cityNamesStr);
    }

    private List<String> getCityNamesFromCommaSeparatedStr(final String cityNameListStr) {
        final String[] cityNames        = cityNameListStr.split(DELIMITER);
        final List<String> cityNameList = new ArrayList<>();
        if(TextUtils.isEmpty(cityNameListStr)) {
            return cityNameList;
        }
        for (String cityNameStr : cityNames) {
            cityNameList.add(cityNameStr);
        }
        return cityNameList;
    }

    private String prepareCommaSeparatedCityNames(final List<String> cityNameList) {
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        for (String cityNameStr : cityNameList) {
            sb.append(delimiter);
            sb.append(cityNameStr);
            delimiter = DELIMITER;
        }
        return sb.toString();
    }

}
