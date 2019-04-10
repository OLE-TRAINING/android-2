package com.example.android.movieapp.ViewModelHome;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.model.MovieDetail;
import com.example.android.movieapp.repository.HomeRepository;

public class MovieDetailViewModel extends AndroidViewModel {

    private HomeRepository homeRepository;
    private MutableLiveData<MovieDetail> movieDetail = new MutableLiveData<>();
    private MutableLiveData<ListMovie> listMovie = new MutableLiveData<>();

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        this.homeRepository = new HomeRepository();
    }

    public void init(String id) {
        System.out.println("ENTRANDO INIT MOVEIDETAILVIEWMODEL");
        homeRepository.getMovieDetail(id).observeForever(new Observer<MovieDetail>() {
            @Override
            public void onChanged(@Nullable MovieDetail movieDetail) {
                getMovieDetail().setValue(movieDetail);
                System.out.println("AQUI VAI SER PRINTADO O FILME DA MOVIDETAIL VIEWMODEL ------------------- * ------------ * ----------");
//            System.out.println(movieDetail.getTitle());
            }
        });
    }

    public void initMovieList(String id, String page) {
        homeRepository.getMovieList(id, page, "similarity").observeForever(new Observer<ListMovie>() {
            @Override
            public void onChanged(@Nullable ListMovie listMovie) {
                getListMovie().setValue(listMovie);
            }
        });
    }

    public MutableLiveData<MovieDetail> getMovieDetail() {
        return movieDetail;
    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
