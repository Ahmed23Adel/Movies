package com.example.movies.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {

    private static RetrofitClient mInstance;
    private static String BASE_URL="https://api.themoviedb.org/3/discover/";
    private Retrofit retrofit;

    private RetrofitClient (){
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public JsonApiHolder getApi(){
        return retrofit.create(JsonApiHolder.class);
    }
}