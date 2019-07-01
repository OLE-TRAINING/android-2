package com.example.android.movieapp.ViewModelHome;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.repository.HomeRepository;


public class HomeFragmentViewModel extends AndroidViewModel {

    private HomeRepository homeRepository;
    private MutableLiveData<ListGenres> listGenre = new MutableLiveData<>();
    private MutableLiveData<ListMovie> listMovie = new MutableLiveData<>();


    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        this.homeRepository = new HomeRepository();
    }

    public void init() {
        homeRepository.getGenresList().observeForever(new Observer<ListGenres>() {
            @Override
            public void onChanged(@Nullable ListGenres listGenres) {
                getListGenre().setValue(listGenres);
            }
        });
    }

    public void initMovieList(String id, String page) {
        homeRepository.getMovieList(id, page,"genres").observeForever(new Observer<ListMovie>() {
            @Override
            public void onChanged(@Nullable ListMovie listMovie) {
                getListMovie().setValue(listMovie);
            }
        });
    }

    public MutableLiveData<ListGenres> getListGenre() {
        return listGenre;
    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
