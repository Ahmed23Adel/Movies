package com.example.movies.ViewMode.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.Model.Movie;
import com.example.movies.R;

import java.util.List;

public class recyclerViewAdapter extends PagedListAdapter<Movie,recyclerViewAdapter.MovieHolder> {

    List<Movie> movies;
    public recyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK=new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);

        return new MovieHolder(view);
    }






    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Movie movie=getItem(position);
        holder.title.setText(movie.getTitle());
        /*Glide.with(holder.image_view_poster.getContext())
                .load(movie.getPoster_url())
                .into(holder.image_view_poster);*/
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{


        ImageView image_view_poster;
        TextView title;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            image_view_poster=itemView.findViewById(R.id.image_view_poster);
            title=itemView.findViewById(R.id.title);
        }
    }
}
