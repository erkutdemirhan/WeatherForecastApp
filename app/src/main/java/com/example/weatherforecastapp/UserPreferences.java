package com.example.weatherforecastapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public class UserPreferences {

    private static final String SHARED_PREFS_KEY = "shared_prefs";

    private static final String ZIP_CODES_KEY = "zip_codes";
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

    public boolean addNewZipCode(final String zipCodeStr) {
        List<String> zipCodeList = getZipCodes();
        if(zipCodeList.contains(zipCodeStr)) {
            return false;
        } else {
            zipCodeList.add(zipCodeStr);
            final String newZipCodesStr = prepareCommaSeparatedZipCodes(zipCodeList);
            editor.putString(ZIP_CODES_KEY, newZipCodesStr);
            editor.commit();
            return true;
        }
    }

    public List<String> getZipCodes() {
        final String zipCodesStr = prefs.getString(ZIP_CODES_KEY, "");
        return getZipCodesFromCommaSeparatedStr(zipCodesStr);
    }

    private List<String> getZipCodesFromCommaSeparatedStr(final String zipCodeListStr) {
        final String[] zipCodes        = zipCodeListStr.split(DELIMITER);
        final List<String> zipCodeList = new ArrayList<>();
        for (String zipCodeStr : zipCodes) {
            zipCodeList.add(zipCodeStr);
        }
        return zipCodeList;
    }

    private String prepareCommaSeparatedZipCodes(final List<String> zipCodeList) {
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        for (String zipCodeStr : zipCodeList) {
            sb.append(delimiter);
            sb.append(zipCodeStr);
            delimiter = DELIMITER;
        }
        return sb.toString();
    }

}
