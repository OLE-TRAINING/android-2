package com.example.android.movieapp.homeScreen;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.movieapp.ViewModelHome.HomeFragmentViewModel;
import com.example.android.movieapp.R;
import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.ListMovie;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements OnOpenDetailMovie{


    private int id;
    int page = 1;
    TextView text;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private ProgressBar progressBar;


    private boolean isLoading = false;

    private HomeFragmentViewModel homeFragmentViewModel;

    private final LinkedList<MovieItem> movieList = new LinkedList<>();


    private RecyclerView mRecyclerView;
    private ListMovieAdapter mAdapter;
    public static final String EXTRA_MESSAGE_OBJECT =
            "ID";


    public static MovieListFragment newInstance(int id) {

        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);

        fragment.setArguments(bundle);

        return fragment;
    }

    Observer<ListGenres> observerValidEmail = new Observer<ListGenres>() {
        @Override
        public void onChanged(@Nullable ListGenres listGenres) {
            int i =0;
            while(i<listGenres.getListGenres().size()){

            }
        }
    };



    Observer<ListMovie> observerMovieList= new Observer<ListMovie>() {
        @Override
        public void onChanged(@Nullable ListMovie movieList) {

            if (page==1){
                mAdapter.setMovieList(movieList);
                mAdapter.addLoadingFooter();
            }

            else if(page>1) {
                mAdapter.removeLoadingFooter();
                mAdapter.addAll(movieList);
                mAdapter.addLoadingFooter();
            }

//

            isLoading = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            id = bundle.getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.type_home_fragment, container, false);
        readBundle(getArguments());
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        homeFragmentViewModel.initMovieList(String.valueOf(id),"1");
        progressBar = view.findViewById(R.id.progress_type_home);


        mAdapter = new ListMovieAdapter(view.getContext());

        mRecyclerView = view.findViewById(R.id.recyclerview);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        homeFragmentViewModel.getListMovie().observe(this, observerMovieList);
        mAdapter.setOpenDetailMovie(MovieListFragment.this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    progressBar.setVisibility(View.VISIBLE);
                    page++;
                    System.out.println("CHEGOU NO FINAL NA LISTAAA ------------*!");
                    homeFragmentViewModel.initMovieList(String.valueOf(id),String.valueOf(page));
                }

                @Override
                public int getTotalPageCount() {
                    return 10;
                }

                @Override
                public boolean isLastPage() {
                    return false;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });


        return view;
    }

    public void setView(View view){
        this.view = view;
    }

    public View getView(){
        return this.view;
    }

    @Override
    public void openMovieDetail(String id) {

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE_OBJECT, id);

        startActivity(intent);

    }

    public void loadMoreItems(){

    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}