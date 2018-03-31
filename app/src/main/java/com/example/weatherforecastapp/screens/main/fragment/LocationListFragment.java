package com.example.weatherforecastapp.screens.main.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;
import com.example.weatherforecastapp.base.BaseFragment;
import com.example.weatherforecastapp.databinding.FragmentLocationListBinding;
import com.example.weatherforecastapp.repository.Resource;
import com.example.weatherforecastapp.screens.main.MainViewModel;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class LocationListFragment extends BaseFragment {

    private static final String TAG = "LocationListFragment";

    private FragmentLocationListBinding binding;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding   = DataBindingUtil.inflate(inflater, R.layout.fragment_location_list, container, false);
        binding.setShowProgress(false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listenForCurrentLocationForecast();
    }

    private void listenForCurrentLocationForecast() {
        viewModel.getCurrentLocForecast().observe(LocationListFragment.this, new Observer<Resource<DailyForecastResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<DailyForecastResponse> dailyForecastResponseResource) {
                if(dailyForecastResponseResource.getState() == Resource.State.SUCCESS) {
                    binding.setShowProgress(false);
                    final DailyForecastResponse response = dailyForecastResponseResource.getData();
                    binding.setCurLocForecastData(response.getForecastDataList().get(0));
                    binding.setCurLocCity(response.getCityInfo());
                    if (binding.currentLocationContainer.getVisibility() == View.GONE) {
                        animateCurrentLocationContainer();
                    }
                } else if(dailyForecastResponseResource.getState() == Resource.State.LOADING) {
                    binding.setShowProgress(true);
                } else if(dailyForecastResponseResource.getState() == Resource.State.ERROR) {
                    binding.setShowProgress(false);
                }
            }
        });
    }

    private void animateCurrentLocationContainer() {
        binding.currentLocationContainer.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_top);
        binding.currentLocationContainer.startAnimation(animation);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }


}
