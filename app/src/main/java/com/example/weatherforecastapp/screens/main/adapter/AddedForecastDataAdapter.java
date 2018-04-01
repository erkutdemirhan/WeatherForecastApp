package com.example.weatherforecastapp.screens.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.api.response.DailyForecastResponse;
import com.example.weatherforecastapp.databinding.ItemSavedForecastBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erkut Demirhan on 01/04/18.
 */

public class AddedForecastDataAdapter extends RecyclerView.Adapter<AddedForecastDataAdapter.AddedForecastDataItemViewHolder> {

    private List<DailyForecastResponse> dataList;

    public AddedForecastDataAdapter() {
        dataList = new ArrayList<>();
    }

    public void updateForecastData(final List<DailyForecastResponse> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public AddedForecastDataItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSavedForecastBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_saved_forecast,
                parent,
                false);
        return new AddedForecastDataItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AddedForecastDataItemViewHolder holder, int position) {
        holder.bindViewHolder(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class AddedForecastDataItemViewHolder extends RecyclerView.ViewHolder {

        private ItemSavedForecastBinding binding;

        public AddedForecastDataItemViewHolder(final ItemSavedForecastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindViewHolder(final DailyForecastResponse dataResponse, final int position) {
            binding.setIndex(position + 1);
            binding.setForecastData(dataResponse.getForecastDataList().get(0));
            binding.setCity(dataResponse.getCityInfo());
        }
    }
}
