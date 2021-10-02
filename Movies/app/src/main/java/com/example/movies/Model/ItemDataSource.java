package com.example.movies.Model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movies.MainActivity;
import com.example.movies.ViewMode.Adapter.recyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer,Movie> {

    private static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 20;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        Log.v("main","Inital");
        RetrofitClient.getInstance().getApi().getMoviesAtPage("popularity.desc","db93980ce8f3949194e9c02bf9645258",FIRST_PAGE)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        List<Movie> movies=new ArrayList<Movie>();
                        String body=null;
                        try {
                            body=response.body().string();
                        } catch (IOException e) {
                        }
                        try {
                            JSONObject root= new JSONObject(body);
                            JSONArray jsonArray=root.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String voteAverage=jsonObject.optString("vote_average","-1");
                                String tittle =jsonObject.optString("title","-1");
                                String url_photo =jsonObject.optString("poster_path","-1");

                                movies.add(new Movie(id,Double.valueOf(voteAverage),tittle,url_photo));
                            }

                            callback.onResult(movies,null,FIRST_PAGE+1);

                        } catch (JSONException e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        Log.v("main","before");

        RetrofitClient.getInstance().getApi().getMoviesAtPage("popularity.desc","db93980ce8f3949194e9c02bf9645258",params.key)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        List<Movie> movies=new ArrayList<Movie>();
                        String body=null;
                        try {
                            body=response.body().string();
                        } catch (IOException e) {
                        }
                        try {
                            JSONObject root= new JSONObject(body);
                            JSONArray jsonArray=root.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String voteAverage=jsonObject.optString("vote_average","-1");
                                String tittle =jsonObject.optString("title","-1");
                                String url_photo =jsonObject.optString("poster_path","-1");

                                movies.add(new Movie(id,Double.valueOf(voteAverage),tittle,url_photo));
                            }

                            Integer adjacentKey = (params.key < 500) ? params.key - 1 : null;
                            if (response.body() != null) {

                                //passing the loaded data
                                //and the previous page key
                                callback.onResult(movies, adjacentKey);
                            }


                        } catch (JSONException e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        Log.v("main","after");

        RetrofitClient.getInstance().getApi().getMoviesAtPage("popularity.desc","db93980ce8f3949194e9c02bf9645258",params.key)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        List<Movie> movies=new ArrayList<Movie>();
                        String body=null;
                        try {
                            body=response.body().string();
                        } catch (IOException e) {
                        }
                        try {
                            JSONObject root= new JSONObject(body);
                            JSONArray jsonArray=root.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String voteAverage=jsonObject.optString("vote_average","-1");
                                String tittle =jsonObject.optString("title","-1");
                                String url_photo =jsonObject.optString("poster_path","-1");

                                movies.add(new Movie(id,Double.valueOf(voteAverage),tittle,url_photo));
                            }

                            Integer adjacentKey = (params.key < 500) ? params.key + 1 : null;
                            if (response.body() != null) {

                                //passing the loaded data
                                //and the previous page key
                                callback.onResult(movies, adjacentKey);
                            }


                        } catch (JSONException e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
