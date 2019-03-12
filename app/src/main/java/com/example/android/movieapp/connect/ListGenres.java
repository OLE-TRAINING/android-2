package com.example.android.movieapp.connect;

import java.util.List;

public class ListGenres {


    private final List<Genres> genres;

    public ListGenres(List<Genres> listGenres) {
        this.genres = listGenres;
    }


    public List<Genres> getListGenres() {
        return this.genres;
    }


}
