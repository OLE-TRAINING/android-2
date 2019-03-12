package com.example.android.movieapp.homeScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movieapp.R;

import java.util.LinkedList;

public class ListMovieAdapter extends  RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder> {

    private final LinkedList<MovieItem> movieList;
    private LayoutInflater inflater;

    public ListMovieAdapter(Context context, LinkedList<MovieItem> movieList) {
        inflater = LayoutInflater.from(context);
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.movie_item_list,viewGroup, false);
        return new MovieViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final ListMovieAdapter mAdapter;

    public MovieViewHolder(@NonNull View itemView, ListMovieAdapter adapter) {
        super(itemView);

        this.mAdapter = adapter;

    }
}


}
