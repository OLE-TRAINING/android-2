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


public class SearchFragmentViewModel extends AndroidViewModel {

    private HomeRepository homeRepository;
    private MutableLiveData<ListMovie> listMovie = new MutableLiveData<>();

    public SearchFragmentViewModel(@NonNull Application application) {
        super(application);

        this.homeRepository = new HomeRepository();

    }

    public void init(String name,String page) {
        homeRepository.getMovieList(name,page,"name").observeForever(new Observer<ListMovie>() {
            @Override
            public void onChanged(@Nullable ListMovie listMovie) {
                getListMovie().setValue(listMovie);
            }
        });
    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
