package com.example.movies.Model;

import androidx.annotation.Nullable;

public class Movie {
    private int id;

    private double vote_average;

    private String title;

    private String poster_url;

    public Movie(int id, double vote_average, String title, String poster_url) {
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_url = poster_url;
    }

    public int getId() {
        return id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this.id==((Movie) obj).getId()
                && this.vote_average==((Movie) obj).getVote_average()
                &&this.title.equals(((Movie) obj).getTitle())
                &&this.poster_url.equals(((Movie) obj).getPoster_url())
        )
        {
            return true;
        }
        return false;
    }
}
