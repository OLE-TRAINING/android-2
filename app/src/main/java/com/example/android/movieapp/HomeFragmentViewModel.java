package com.example.android.movieapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.movieapp.connect.ListGenres;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.connect.User;
import com.example.android.movieapp.homeScreen.HomeFragment;
import com.example.android.movieapp.repository.HomeRepository;
import com.example.android.movieapp.repository.LoginRepository;



public class HomeFragmentViewModel extends AndroidViewModel {

    private HomeRepository homeRepository;
    private MutableLiveData<ListGenres> listGenre = new MutableLiveData<>();

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

    public MutableLiveData<ListGenres> getListGenre() {
        return listGenre;
    }
}
