package com.example.weatherforecastapp.location;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.MainThread;

/**
 * Created by Erkut Demirhan on 15/10/17.
 */

public class LocationLiveData extends LiveData<Location> implements LocationListener {

    private static final long LOCATION_UPDATE_PERIOD = 5 * 1000;
    private static final long LOCATION_MIN_DISTANCE  = 10;

    private static LocationLiveData instance;

    private LocationManager locationManager;
    private Location latestLocation;

    @MainThread
    public static LocationLiveData getInstance(Context context) {
        if(instance == null) {
            instance = new LocationLiveData(context);
        }
        return instance;
    }

    private LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    @SuppressLint("MissingPermission")
    protected void onActive() {
        super.onActive();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                LOCATION_UPDATE_PERIOD,
                LOCATION_MIN_DISTANCE,
                LocationLiveData.this);
    }

    @Override
    @SuppressLint("MissingPermission")
    protected void onInactive() {
        super.onInactive();
        locationManager.removeUpdates(LocationLiveData.this);
    }

    @Override
    public void onLocationChanged(Location location) {
        //setValue(location);

        if(latestLocation == null) {
            latestLocation = location;
            setValue(latestLocation);
        } else {
            if(latestLocation.distanceTo(location) >= LOCATION_MIN_DISTANCE) {
                latestLocation = location;
                setValue(latestLocation);
            }
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}
}
