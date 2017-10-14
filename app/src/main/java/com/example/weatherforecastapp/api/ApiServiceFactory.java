package com.example.weatherforecastapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Erkut Demirhan on 14/10/17.
 */

public class ApiServiceFactory {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/";

    public static ApiClient getApiClient() {
        return provideRetrofitForApp().create(ApiClient.class);
    }

    private static Retrofit provideRetrofitForApp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
