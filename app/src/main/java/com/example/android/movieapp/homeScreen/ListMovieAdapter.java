package com.example.android.movieapp.homeScreen;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

public class ListMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ListMovie movieList;
    private OnOpenDetailMovie openDetailMovie;
    private LayoutInflater inflater;
    private Context context;
    private boolean isLoadingAdded = false;
    String url;

    private final int ITEM = 0;
    private final int LOADING = 1;

    public ListMovieAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void setOpenDetailMovie(OnOpenDetailMovie openDetailMovie){

        this.openDetailMovie = openDetailMovie;
    }

    public void setMovieList(ListMovie listMovie){

        this.movieList = listMovie;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        RecyclerView.ViewHolder viewHolder = null;

        switch (i) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.movie_item_list, viewGroup, false);
                viewHolder = new MovieViewHolder(viewItem,this);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_loading, viewGroup, false);
                viewHolder = new LoadingViewHolder(viewLoading,this);
                break;

        }
        return viewHolder;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {


        switch (getItemViewType(position)) {


            case ITEM:
                final MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;

                url = String.format("https://ole.dev.gateway.zup.me/client-training/v1/movies/%s/image/w500?gw-app-key=593c3280aedd01364c73000d3ac06d76", movieList.getMovie().get(position).getPosterId());

                movieViewHolder.movieTitle.setText(movieList.getMovie().get(position).getTitle());
                movieViewHolder.movieRunTime.setText(movieList.getMovie().get(position).getRuntime());
                movieViewHolder.moviePrice.setText(String.valueOf(movieList.getMovie().get(position).getPrice()));
                movieViewHolder.movieOverView.setText(movieList.getMovie().get(position).getOverview());
                movieViewHolder.movieYear.setText(movieList.getMovie().get(position).getYear());
                movieViewHolder.movieVoteAverage.setText(movieList.getMovie().get(position).getVoteAverage());
                Picasso.with(context).load(url).into(movieViewHolder.imageMovie);
//
//                String genres = String.join(", ", movieList.getMovie().get(position).getGenreNames());
//                movieViewHolder.movieGenres.setText(genres);

                movieViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDetailMovie.openMovieDetail(movieList.getMovie().get(position).getId());
                    }
                });


                break;

            case LOADING:
                LoadingViewHolder loadingVH = (LoadingViewHolder) viewHolder;
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {

        if(movieList==null){
            return 0;
        }

        return movieList.getMovie().size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {

        final ListMovieAdapter mAdapter;
        //       private final TextView movieTitle,movieYear,movieOverView,moviePrice,movieRunTime,movieGenres;
        private final ImageView imageMovie;
        private final TextView movieTitle, movieYear, movieRunTime, movieGenres, movieVoteAverage, moviePrice, movieOverView;
        private final View view;


        public MovieViewHolder(@NonNull View itemView, ListMovieAdapter adapter) {
            super(itemView);


            this.mAdapter = adapter;
            view = itemView;
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieYear = itemView.findViewById(R.id.year_movie);
            movieOverView = itemView.findViewById(R.id.description_movie);
            moviePrice = itemView.findViewById(R.id.movie_price);
            movieRunTime = itemView.findViewById(R.id.duration_movie);
            movieGenres = itemView.findViewById(R.id.type_movie);
            imageMovie = itemView.findViewById(R.id.imagemMovie);
            movieVoteAverage = itemView.findViewById(R.id.note_movie);

        }
    }

    @Override
    public int getItemViewType(int position) {

        return (position == movieList.getMovie().size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    protected class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;


        public LoadingViewHolder(View itemView,ListMovieAdapter adapter) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.progress_bar_loading);

        }

    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieList.getMovie().size() - 1;
        Movie movie = getItem(position);

        if (movie != null) {
            movieList.getMovie().remove(position);
            notifyItemRemoved(position);
        }
    }

    public Movie getItem(int position) {
        return movieList.getMovie().get(position);
    }


    public void add(Movie movie) {
        movieList.getMovie().add(movie);
        notifyItemInserted(movieList.getMovie().size() - 1);
    }

    public void addAll(ListMovie listMovie) {

        for (Movie movie : listMovie.getMovie()) {
            add(movie);
        }
    }


}
