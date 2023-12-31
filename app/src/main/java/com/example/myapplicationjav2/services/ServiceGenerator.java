package com.example.myapplicationjav2.services;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String SERVER_IP = "11.0.0.2";
    private static final String BASE_URL = "http://"+SERVER_IP+":5000/api/";
//    private static final String BASE_URL = "http://10.10.8.79:6000/api/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .addConverterFactory(MoshiConverterFactory.create())
            ;

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);




    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()    .connectTimeout(60, TimeUnit.SECONDS) // Increase the connect timeout
                    .readTimeout(60, TimeUnit.SECONDS) // Increase the read timeout
                    .writeTimeout(60, TimeUnit.SECONDS); // Increase the write timeout;




    public static <S> S createService(
            Class<S> serviceClass) {
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }
}