package com.example.weatherforecastapp.screens.main.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.UserPreferences;
import com.example.weatherforecastapp.base.BaseDialog;
import com.example.weatherforecastapp.databinding.DialogAddLocationBinding;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public class AddLocationDialog extends BaseDialog {

    private DialogAddLocationBinding binding;
    private AddLocationListener addLocationListener;

    public AddLocationDialog(@NonNull Context context) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_location, null, false);
        setContentView(binding.getRoot());
        binding.setDialog(AddLocationDialog.this);
    }

    public void onAddButtonPressed(View view) {
        final String cityName          = binding.addLocationEt.getText().toString();
        final String formattedCityName = cityName.trim().toLowerCase();

        if (TextUtils.isEmpty(formattedCityName)) {
            Toast.makeText(getContext(), R.string.warning_empty_city_name, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!UserPreferences.getInstance(getContext()).addNewCityName(formattedCityName)) {
            Toast.makeText(getContext(), R.string.warning_already_added_city_name, Toast.LENGTH_SHORT).show();
            return;
        }

        if (addLocationListener != null) {
            addLocationListener.onCityNameAdded(formattedCityName);
        }

        cancel();
    }

    public void setAddLocationListener(AddLocationListener addLocationListener) {
        this.addLocationListener = addLocationListener;
    }

    public interface AddLocationListener {
        void onCityNameAdded(final String cityName);
    }
}
