package com.example.weatherforecastapp.screens.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.base.BaseFragment;
import com.example.weatherforecastapp.databinding.ActivityMainBinding;
import com.example.weatherforecastapp.screens.main.fragment.LocationListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        binding   = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        viewModel.sendCurrLocForecastRequest(44.017f, 28.908f);
        switchToFragment(new LocationListFragment());
    }

    private void switchToFragment(final BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment, fragment.getFragmentTag())
                .commit();
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
