package com.example.android.movieapp.homeScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.ListMovie;
import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.model.MovieDetail;
import com.squareup.picasso.Picasso;

public class ListMovieAdapterDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEMDETAIL = 0;
    private final int ITEM = 1;
    private final int LOADING = 2;

    private ListMovie movieList;
    private MovieDetail movieDetail;
    private OnOpenDetailMovie openDetailMovie;
    private LayoutInflater inflater;
    private Context context;
    private boolean isLoadingAdded = false;
    private String url;

    public ListMovieAdapterDetail(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setOpenDetailMovie(OnOpenDetailMovie openDetailMovie){

        this.openDetailMovie = openDetailMovie;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        RecyclerView.ViewHolder viewHolder = null;

        switch (i) {

            case ITEMDETAIL:
                View viewItemDetail = inflater.inflate(R.layout.item_movie_detail, viewGroup, false);
                viewHolder = new ItemDetailViewHolderDetail(viewItemDetail, this);
                break;
            case ITEM:
                View viewItem = inflater.inflate(R.layout.movie_item_list, viewGroup, false);
                viewHolder = new MovieViewHolderDetail(viewItem, this);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_loading, viewGroup, false);
                viewHolder = new LoadingViewHolderDetail(viewLoading, this);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {

            case ITEMDETAIL:

                System.out.println("PRINTANDO BANNER MVOIE");
                    System.out.println(movieDetail.getBannerId());
                final ItemDetailViewHolderDetail itemDetailViewHolderDetail = (ItemDetailViewHolderDetail) viewHolder;
                String urldetail = String.format("https://ole.dev.gateway.zup.me/client-training/v1/movies/%s/image/w500?gw-app-key=593c3280aedd01364c73000d3ac06d76", movieDetail.getBannerId());
                String urlPoster = String.format("https://ole.dev.gateway.zup.me/client-training/v1/movies/%s/image/w500?gw-app-key=593c3280aedd01364c73000d3ac06d76", movieDetail.getPosterId());
                itemDetailViewHolderDetail.descriptionDetail.setText(movieDetail.getOverview());
                Picasso.with(context).load(urldetail).fit().centerCrop().into(itemDetailViewHolderDetail.bannerMovieDetail);
                Picasso.with(context).load(urlPoster).into(itemDetailViewHolderDetail.posterMovieDetail);
                itemDetailViewHolderDetail.titleMovieDetail.setText(movieDetail.getTitle());
                itemDetailViewHolderDetail.avarageMovieDetail.setText(movieDetail.getVoteAverage());
                itemDetailViewHolderDetail.durationMovieDetail.setText(movieDetail.getRuntime());
                itemDetailViewHolderDetail.yearMovieDetail.setText(movieDetail.getYear());
                break;

            case ITEM:
                final MovieViewHolderDetail movieViewHolder = (MovieViewHolderDetail) viewHolder;

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
                LoadingViewHolderDetail loadingVH = (LoadingViewHolderDetail) viewHolder;
                loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setMovieList(ListMovie listMovie) {

        this.movieList = listMovie;
        System.out.println("PASSSOU PELO caaaaaaaaaaaaaaaTIAU PASSOUASOASASSA");
        notifyDataSetChanged();
    }

    public void setMovieDetail(MovieDetail movieDetail){

        this.movieDetail = movieDetail;
        System.out.println("PASSSOU PELO PRINTAOAOAOAOA PASSOUASOASASSA");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }

        return movieList.getMovie().size() ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) return ITEMDETAIL;
            return (position == movieList.getMovie().size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    class MovieViewHolderDetail extends RecyclerView.ViewHolder {

        final ListMovieAdapterDetail mAdapter;
        //       private final TextView movieTitle,movieYear,movieOverView,moviePrice,movieRunTime,movieGenres;
        private final ImageView imageMovie;
        private final TextView movieTitle, movieYear, movieRunTime, movieGenres, movieVoteAverage, moviePrice, movieOverView;
        private final View view;


        public MovieViewHolderDetail(@NonNull View itemView, ListMovieAdapterDetail adapter) {
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

    protected class LoadingViewHolderDetail extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        public LoadingViewHolderDetail(View itemView, ListMovieAdapterDetail adapter) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.progress_bar_loading);

        }

    }

    protected class ItemDetailViewHolderDetail extends RecyclerView.ViewHolder {
        private ImageView bannerMovieDetail, posterMovieDetail;
        private CardView cardViewDetail;
        private TextView descriptionDetail, titleMovieDetail, genresMovieDetail, avarageMovieDetail,yearMovieDetail,durationMovieDetail;


        public ItemDetailViewHolderDetail(View itemView, ListMovieAdapterDetail adapter) {
            super(itemView);

            bannerMovieDetail = itemView.findViewById(R.id.banner_movie_detail);
            posterMovieDetail = itemView.findViewById(R.id.poster_movie_detail);
            descriptionDetail = itemView.findViewById(R.id.description_detail);
            titleMovieDetail =  itemView.findViewById(R.id.title_movie_detail);
            avarageMovieDetail = itemView.findViewById(R.id.avarage_detail);
            yearMovieDetail = itemView.findViewById(R.id.year_detail);
            durationMovieDetail = itemView.findViewById(R.id.duration_detail);

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