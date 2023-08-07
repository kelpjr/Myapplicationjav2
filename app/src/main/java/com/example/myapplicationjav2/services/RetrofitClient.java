package com.example.myapplicationjav2.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private ConsolidatedInterfaceAPIs api;
    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConsolidatedInterfaceAPIs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ConsolidatedInterfaceAPIs.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ConsolidatedInterfaceAPIs getAPIs(){
        return api;
    }
}
