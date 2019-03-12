package com.example.android.movieapp.connect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("genres")
    Call<ListGenres> getListGenres();
}
