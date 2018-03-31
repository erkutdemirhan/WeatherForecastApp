package com.example.weatherforecastapp.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.weatherforecastapp.R;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
    }
}
