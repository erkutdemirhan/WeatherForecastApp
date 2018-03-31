package com.example.weatherforecastapp.screens.main.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.base.BaseDialog;
import com.example.weatherforecastapp.databinding.DialogAddLocationBinding;
import com.example.weatherforecastapp.utils.StringUtils;

/**
 * Created by Erkut Demirhan on 31/03/18.
 */

public class AddLocationDialog extends BaseDialog {

    private DialogAddLocationBinding binding;

    public AddLocationDialog(@NonNull Context context) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_location, null, false);
        setContentView(binding.getRoot());
        binding.setDialog(AddLocationDialog.this);
    }

    public void onAddButtonPressed(View view) {
        final String zipCodeStr = binding.addLocationEt.getText().toString();
        final String formattedZipCodeStr = zipCodeStr.trim().toUpperCase();

        if (TextUtils.isEmpty(formattedZipCodeStr)) {
            Toast.makeText(getContext(), R.string.warning_empty_zip_code, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StringUtils.isValidZipCode(formattedZipCodeStr)) {
            Toast.makeText(getContext(), R.string.warning_invalid_zip_code, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getContext(), "Add location", Toast.LENGTH_SHORT).show();
    }
}
