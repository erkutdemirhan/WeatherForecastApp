package com.example.weatherforecastapp.screens.main;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.base.BaseFragment;
import com.example.weatherforecastapp.databinding.ActivityMainBinding;
import com.example.weatherforecastapp.location.LocationLiveData;
import com.example.weatherforecastapp.screens.main.fragment.LocationListFragment;
import com.example.weatherforecastapp.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_ID = 1317;

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        binding   = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setMainActivity(MainActivity.this);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        switchToFragment(new LocationListFragment());
        askForLocationPermission();
    }

    private void switchToFragment(final BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment, fragment.getFragmentTag())
                .commit();
    }

    private void askForLocationPermission() {
        final String[] locationPermission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(PermissionUtils.hasPermissions(MainActivity.this, locationPermission)) {
            listenForLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, locationPermission, LOCATION_PERMISSION_REQUEST_ID);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_ID) {
            if(grantResults.length <= 0) {
                return;
            }
            for(int grantResult:grantResults) {
                if(grantResult != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            listenForLocationUpdates();
        }
    }

    private void listenForLocationUpdates() {
        LocationLiveData.getInstance(MainActivity.this).observe(MainActivity.this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                viewModel.sendCurrLocForecastRequest(location.getLatitude(), location.getLongitude());
            }
        });
    }

    public void onAddButtonPressed(View view) {
        Toast.makeText(MainActivity.this, "Add location", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
