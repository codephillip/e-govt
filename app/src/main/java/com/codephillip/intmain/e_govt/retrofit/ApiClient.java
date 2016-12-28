package com.codephillip.intmain.e_govt.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "https://my-uganda-api.herokuapp.com";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(String url) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
