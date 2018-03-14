package com.example.adou.mydrone.consumers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.adou.mydrone.constants.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hakima on 3/12/18.
 */

public class RestTemplate {
    private static final String TAG = "REST-TEMPLATE";
    private static RestTemplate instance;

    private RestTemplate(){}

    public static RestTemplate getInstance() {
        if(instance == null) {
            instance = new RestTemplate();
        }
        return instance;
    }

    public Consumer buildConsumer(Class<? extends Consumer> clazz) {
        Consumer consumer = new Retrofit.Builder()
                .baseUrl(Endpoints.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clazz);

        //AccountConsumer ac = (AccountConsumer) consumer;
        //ac.activate("ssd").execute().body();
        return consumer;
    }
}
