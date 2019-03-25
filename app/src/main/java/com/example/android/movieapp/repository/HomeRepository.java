package com.example.android.movieapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.model.MovieDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    public LiveData<ListGenres> getGenresList( ) {


        Call<ListGenres> call = new RetrofitConfig().getMovieService().getListGenres();
        final MutableLiveData<ListGenres> data = new MutableLiveData<>();
        call.enqueue(new Callback<ListGenres>() {
            @Override
            public void onResponse(Call<ListGenres> call, Response<ListGenres> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ListGenres> call, Throwable t) {
                Log.e("EMAILService   ", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

    public LiveData<ListMovie> getMovieList( String id,String page) {

        Call<ListMovie> call = new RetrofitConfig().getMovieService().getListMovie("genres",id,"10",page);
        final MutableLiveData<ListMovie> data = new MutableLiveData<>();

        call.enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.e("EMAILService", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

    public LiveData<MovieDetail> getMovieDetail(String id){

        Call<MovieDetail> call = new RetrofitConfig().getMovieService().getDetailMovie(id);
        final MutableLiveData<MovieDetail> data = new MutableLiveData<>();
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail movieDetail = response.body();
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e("EMAILService   ", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

//    public  void getImageMovie( String id) {
//
//        Call<Void> call = new RetrofitConfig().getMovieService().getImageMovie(id,"original");
//        final MutableLiveData<ListMovie> data = new MutableLiveData<>();
//
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//               // data.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.e("EMAILService", "Erro ao buscar o email:" + t.getMessage());
//            }
//        });
//
//
//    }

}
