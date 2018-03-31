package com.example.weatherforecastapp.utils;

import android.text.TextUtils;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public class StringUtils {

    private static final String ZIP_CODE_PATTERN = "[A-Z0-9]{3,9}";

    public static boolean isValidZipCode(final String input) {
        if(TextUtils.isEmpty(input)) {
            return false;
        }

        return input.matches(ZIP_CODE_PATTERN);
    }

}
