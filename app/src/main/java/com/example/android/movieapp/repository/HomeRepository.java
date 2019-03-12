package com.example.android.movieapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ListGenres;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.connect.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

}
