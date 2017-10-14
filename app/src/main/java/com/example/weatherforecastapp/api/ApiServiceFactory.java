package com.example.weatherforecastapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class ApiServiceFactory {

    public static WeatherApiClient getWeatherApiClient(final String baseUrl) {
        return provideRetrofitForApp(baseUrl).create(WeatherApiClient.class);
    }

    private static Retrofit provideRetrofitForApp(final String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
