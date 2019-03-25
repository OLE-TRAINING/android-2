package com.example.android.movieapp.connect;

import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.model.MovieDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("genres")
    Call<ListGenres> getListGenres();

    @GET("movies")
    Call<ListMovie> getListMovie(@Query("filter") String filter,
                                 @Query("filter_id") String filterID,
                                 @Query("amount")String amount,
                                 @Query("page") String page);

    @GET("movies/{id}/image/{size}")
    Call<Void> getImageMovie(@Path("id") String id, @Path("size") String size);

    @GET("movies/{id}/detail")
    Call<MovieDetail> getDetailMovie(@Path("id") String id);
}
