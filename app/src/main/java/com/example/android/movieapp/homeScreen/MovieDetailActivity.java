package com.example.android.movieapp.homeScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.ViewModelHome.MovieDetailViewModel;
import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.MovieDetail;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;

public class MovieDetailActivity extends AppCompatActivity {

    String id;
    TextView titleActionBar;
    ImageView bannerMovie, posterMovie;
    MovieDetailViewModel movieDetailViewModel;
    String text = "<font color=#FFFFFF><b>OT</b></font><font color=#FFFFFF>MOVIES</font>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_movie_detail);
        setSupportActionBar(myToolbar);
        titleActionBar = (TextView) findViewById(R.id.titleActionBar_movie_detail);
        titleActionBar.setText(Html.fromHtml(text));

        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        Intent intent = getIntent();

        id = intent.getStringExtra(MovieListFragment.EXTRA_MESSAGE_OBJECT);

        movieDetailViewModel.init(id);

        movieDetailViewModel.getMovieDetail().observe(this, observerMovieDetail);

    }

    Observer<MovieDetail> observerMovieDetail = new Observer<MovieDetail>() {
        @Override
        public void onChanged(@Nullable MovieDetail movieDetail) {

            bannerMovie = (ImageView) findViewById(R.id.banner_movie_detail);
            posterMovie = (ImageView) findViewById(R.id.poster_movie_detail);
            String url = String.format("https://ole.dev.gateway.zup.me/client-training/v1/movies/%s/image/w500?gw-app-key=593c3280aedd01364c73000d3ac06d76", movieDetail.getBannerId());
            String urlPoster = String.format("https://ole.dev.gateway.zup.me/client-training/v1/movies/%s/image/w500?gw-app-key=593c3280aedd01364c73000d3ac06d76", movieDetail.getPosterId());
            Picasso.with(MovieDetailActivity.this).load(url).fit().centerCrop().into(bannerMovie);
            Picasso.with(MovieDetailActivity.this).load(urlPoster).into(posterMovie);

        }
    };


}
