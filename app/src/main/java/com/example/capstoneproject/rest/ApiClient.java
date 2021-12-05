package com.example.capstoneproject.rest;

import com.example.capstoneproject.config.ServerConfig;

import retrofit2.Retrofit;

public class ApiClient {

    private static final String BASE_URL = ServerConfig.API_ENDPOINT;

    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
