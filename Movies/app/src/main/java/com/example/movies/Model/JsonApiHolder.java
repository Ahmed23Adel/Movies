package com.example.movies.Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonApiHolder {

    @GET("movie")
    Call<ResponseBody> getMoviesAtPage(@Query("sort_by") String sortBy, @Query("api_key") String apiKey, @Query("page") int page);
}
