package com.example.movies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;

import com.example.movies.Model.Movie;
import com.example.movies.Model.RetrofitClient;
import com.example.movies.ViewMode.Adapter.recyclerViewAdapter;
import com.example.movies.ViewMode.ItemViewModel;
import com.example.movies.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View root=binding.getRoot();
        setContentView(root);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        binding.recyclerView.setHasFixedSize(true);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final recyclerViewAdapter adapter= new recyclerViewAdapter();

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        binding.recyclerView.setAdapter(adapter);


    }

}
